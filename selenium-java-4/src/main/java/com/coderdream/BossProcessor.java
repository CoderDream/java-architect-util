package com.coderdream;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author CoderDream
 */
public class BossProcessor implements PageProcessor {

    public static AtomicInteger pageNum = new AtomicInteger(2);

    @Override
    public void process(Page page) {
        //去抓取职位列表
        List<Selectable> nodes = page.getHtml().css("li.job-card-wrapper").nodes();

        if (nodes != null && nodes.size() > 0 || page.getUrl().get().contains("geek/job")) {
            //有值就是工作列表页
            //遍历所有的列表项，拿超链接
            for (Selectable node : nodes) {
                String s = node.css("a.job-card-left").links().get();
                page.addTargetRequest(s);

            }
            page.addTargetRequest(
                Constants.BASE_URL + "&page=" + pageNum.getAndIncrement());

        } else {
            //工作详情页  处理我们想要的信息，我这里都用了CSS选择器
            Selectable biggest = page.getHtml().css("div#wrap");
            WorkInf workInf = new WorkInf();
            Selectable primary = biggest.css("div.info-primary");
            String workName = primary.css("div.name h1").get();
            String salary = primary.css("div.name span").get();
            String year = primary.css("p span.text-experiece").get();
            String graduate = primary.css("p span.text-desc.text-degree").get();
            Selectable jobDetail = biggest.css("div.job-detail");
            String workContent = jobDetail.css("div.job-detail-section div.job-sec-text").get();
            String HrTime = jobDetail.css("h2.name span").get();
            Selectable jobSider = biggest.css("div.job-sider");
            String companyName = jobSider.css("div.sider-company a[ka=job-detail-company_custompage]").get();
            String workAddress = jobDetail.css("div.location-address").get();

            workInf.setWorkName(Jsoup.parse(workName).text());
            workInf.setWorkSalary(Jsoup.parse(salary).text());
            workInf.setWorkYear(Jsoup.parse(year).text());
            workInf.setGraduate(Jsoup.parse(graduate).text());
            workInf.setWorkContent(Jsoup.parse(workContent).text());
            workInf.setHRTime(Jsoup.parse(HrTime).text());
            workInf.setCompanyName(Jsoup.parse(companyName).text());
            workInf.setWorkAddress(Jsoup.parse(workAddress).text());
            workInf.setUrl(page.getUrl().get());

            page.putField("workInf", workInf);
        }

    }

    //可以对爬虫进行一些配置
    private Site site = Site.me()
        // 单位是秒
        .setCharset("UTF-8")//编码
        .setSleepTime(3000)//抓取间隔时间,可以解决一些反爬限制
        .setTimeOut(1000 * 10)//超时时间
        .setRetrySleepTime(3000)//重试时间
        .setRetryTimes(3);//重试次数

    @Override
    public Site getSite() {
        return site;
    }
}
