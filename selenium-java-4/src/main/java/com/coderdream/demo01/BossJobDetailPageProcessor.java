package com.coderdream.demo01;

import com.coderdream.Constants;
import com.coderdream.WorkInf;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author CoderDream
 */
public class BossJobDetailPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
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

            System.out.println("#####" + workInf);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        List<String> urlList = Arrays.asList(
//            "d6a1a2d7d23b9dc20XJy3tm6EFM~",
//            "b9803e1bd00e943b1X143ti1EVRV",
//            "36ca47c9288ead781HR90t27F1RS",
//            "146f7209ae784b311Xxy09q8GFVW",
//            "1aec69f5a57661281HF43ty7FlRX",
//            "f7e6b547b48f56fd1nNz3dS6GVBQ",
//            "098ebfabe9f5781f1XR43N26Fls~",
//            "599e92756ce2f3771nNz3Nm9FlVU",
//            "8e961e0e5e6787a61XV52tS9F1ZZ",
//            "9b2495346732490e1HZ73dy1FFVS",
            "dfa662062efb5a0b1Hd42NW0GVBR");

        for (String url : urlList) {
            url = "https://www.zhipin.com/job_detail/" + url + ".html";
            System.out.println(url);
            Spider.create(new BossJobDetailPageProcessor()).addUrl(url)
                .addPipeline(new ConsolePipeline()).run();
        }

//        Spider.create(new BossJobDetailPageProcessor()).addUrl(urlList)
//            .addPipeline(new ConsolePipeline()).run();

    }
}
