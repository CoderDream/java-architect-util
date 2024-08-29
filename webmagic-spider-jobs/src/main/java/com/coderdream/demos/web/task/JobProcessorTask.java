package com.coderdream.demos.web.task;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderdream.demos.web.pojo.JobInfoEntity;
import com.coderdream.demos.web.util.MathSalary;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName JobProcessorTask
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/5 20:54
 * @Version 1.0
 */
@Component
public class JobProcessorTask implements PageProcessor {

    private static final int PAGE = 1;
    private static final String SHANG_URL = "https://search.51job.com/list/060000,000000,0000,32%252c01,9,99,java,2,";
    private static final String XIA_URL =
        ".html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=03&jobterm" + "=99&companysize=99" +
            "&ord_field=0" + "&dibiaoid=0&line=&welfare=";
    private static String total_page = "";
    @Autowired
    private SpringDataPipeline springDataPipeline;

    @Override
    public void process(Page page) {
        //解析页面，获取招聘信息详情的url地址
        // String html = page.getHtml().toString();
        // System.out.println(html);
        JSONObject jsonObject = null;
        List<Request> targetRequests = page.getTargetRequests();
        // 如果没有任务，才进行添加任务，进入详情页面
        if (targetRequests.size() == 0) {
            // 在主页上面读取数据 ，很麻烦的方式
            Document document = page.getHtml().getDocument();
            Element script = document.getElementsByTag("script").get(8);
            String data = script.data();
            String job = "";
            if (data.indexOf("{\"top_ads\"") != -1) {
                job = data.substring(data.indexOf("{\"top_ads\""), data.length());
                // 将字符串转换为JSON对象
                jsonObject = JSONObject.parseObject(job);
                // 获取JSON对象
                JSONArray engine_search_result = (JSONArray) jsonObject.get("engine_search_result");
                for (int i = 0; i < engine_search_result.size(); i++) {
                    JSONObject list = (JSONObject) engine_search_result.get(i);

                    // 这里的获取，JSON数据不要，因为后头的处理，是进入详细页面获取相应的数据，而这里的数据仅仅只是准备第一页的所有连接，
                    // 只有for循环和下面的代码全部处理完毕，webmagic才会进行下一次请求，而后每次请求五个线程，分批次读取详细页面的数据
                    // 获取公司名称
                    // String companyind_text = list.get("company_name").toString();
                    // // 获取公司联系方式 -- 详情页面
                    // // 公司信息  -- 详情页面
                    // // 职位名称
                    // String job_name = list.get("job_name").toString();
                    // // 工作地点
                    // String workarea_text = list.get("workarea_text").toString();
                    // // 职位信息 -- 详情页面
                    // // 获取薪资
                    // Integer[] providesalary_texts = MathSalary.getSalary(list.get("providesalary_text").toString());
                    // // 薪资范围，最小 -- 详情页面
                    // // 薪资范围，最大 -- 详情页面
                    // // 关键技术点 -- 详情页面
                    // // 职位最近发布时间
                    // String issuedate = list.get("issuedate").toString();

                    // 招聘信息详情页
                    String job_href = list.get("job_href").toString();
                    // 把获取到的url地址放到任务队列中
                    page.addTargetRequest(job_href);
                    page.setRequest(targetRequests.get(i));
                }
            } else {
                // 在子页面读取数据
                saveJobInfo(page);
            }

            // 获取下一页的url
            if (total_page.length() <= 0) {
                total_page = jsonObject.get("total_page").toString();
            } else {
                // 不处理
            }
            int xia_page = 0;
            if (PAGE < Integer.valueOf(total_page)) {
                xia_page = PAGE + 1;
                String URL = SHANG_URL + xia_page + XIA_URL;
                // 把url放到任务队列中
                page.addTargetRequest(URL);
            } else {
                // 不处理
            }
        } else {
            // 任务等待执行

        }

    }

    // 解析页面，获取招聘详情信息，保存数据
    private void saveJobInfo(Page page) {
        // 创建招聘详情对象
        JobInfoEntity jobInfoEntity = new JobInfoEntity();

        // 解析页面
        Html html = page.getHtml();
        // 获取公司名称
        jobInfoEntity.setCompanyName(html.css("div.cn p.cname a","text").toString());
        // 获取公司联系方式 -- 详情页面
        String text = Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text();
        if (text.length()>0){
            jobInfoEntity.setCompanyAddr(text.substring(0,text.length()-2));
        }else {
            // 不处理
        }
        // 公司信息  -- 详情页面
        jobInfoEntity.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        // 职位名称
        jobInfoEntity.setJobName(html.css("div.cn h1","text").toString());
        // 工作地点
        jobInfoEntity.setJobAddr(html.css("div.cn p.ltype","text").regex(".*区").toString());
        // 职位信息 -- 详情页面
        jobInfoEntity.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        // 关键技术点
        String technology = Jsoup.parse(html.css("div.job_msg").toString()).text();
        // 正则匹配
        Pattern compile = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = compile.matcher(technology);
        ArrayList al=new ArrayList();
        while (matcher.find()){
            al.add(matcher.group(0));
        }
        // 去重
        HashSet hs=new HashSet(al);
        al.clear();
        al.addAll(hs);
        String str = al.toString();
        jobInfoEntity.setTechnology(str);
        // 获取薪资
        Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());
        // 薪资范围，最小
        jobInfoEntity.setSalaryMax(salary[0]);
        // 薪资范围，最大
        jobInfoEntity.setSalaryMin(salary[1]);
        // 职位最近发布时间
        String time = Jsoup.parse(html.css("div.cn p.ltype").regex(".*发布").toString()).text();
        if (time.length()>0){
            jobInfoEntity.setTime(time.substring(time.lastIndexOf("|")+1,time.length()-2));
        }else {
            // 不处理
        }
        // 招聘信息详情页
        jobInfoEntity.setUrl(page.getUrl().toString());

        page.putField("jobInfo", jobInfoEntity);
    }


    @Override
    public Site getSite() {
        Site site = Site.me().setCharset("gbk")//设置编码
            .setTimeOut(10 * 1000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setRetryTimes(3);//设置重试的次数
        return site;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public void process() {
        String URL = SHANG_URL + PAGE + XIA_URL;
        Spider.create(new JobProcessorTask()).addUrl(URL).
            setScheduler(new QueueScheduler()
                .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
            .thread(5)
            .addPipeline(this.springDataPipeline)
            .run();
    }
}
