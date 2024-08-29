package com.coderdream.demos.web;


import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;


/**
 * @ClassName JobProcessor
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/4 12:43
 * @Version 1.0
 */
public class JobProcessor implements PageProcessor {

    public void process(Page page) {
        System.out.println(page.getHtml());
        // CSS选择器
        page.putField("author", page.getHtml().css("div.b-wrap>a").all());

        // XPath选择器
        page.putField("div", page.getHtml().xpath("//div[@id=app]/div/div/div[@class=b-wrap]/div/div[@id" +
            "=primaryPageTab]/ul/li/a/span/text()"));
        // 正则表达式
        page.putField("div3", page.getHtml().css("div.b-wrap>a").regex(".*年轻人.*").all());

        // 处理结果API 返回一条数据
        page.putField("div4", page.getHtml().css("div.b-wrap>a").regex(".*年轻人.*").get());
        page.putField("div5", page.getHtml().css("div.b-wrap>a").regex(".*年轻人.*").toString());

        // 获取链接 全部
        page.addTargetRequests(page.getHtml().css("div.b-wrap").links().all());
        // 获取上方查询出的url里面的元素
        page.putField("url",page.getHtml().css("div.nav-search from input").all());

        // 抓取链接
        page.addTargetRequest("https://jobs.51job.com/wuhan-jhq/156793542.html?s=sou_sou_soulb&t=0");

        //         Html html = page.getHtml();
        //        List<Selectable> projectList = html.xpath(
        //            "//div[@class='ui two cards']" + "//a[@class='popular-project-title']" + "//text()").nodes();
        //        System.err.println(projectList);
        //        for (Selectable str : projectList) {
        //            System.out.println(str);
        //        } body > div.tCompanyPage > div > div.tHeader.tHjob > div > div.cn > h1

//        Html html = page.getHtml();
//        List<Selectable> projectList = html.xpath(
//            "//html/body/div[2]/div/div[2]/div/div[1]/h1").nodes();
//        System.err.println(projectList);
//        for (Selectable str : projectList) {
//            System.out.println(str);
//        }
    }

    private Site site = Site.me()
        .setCharset("utf8")    // 设置编码
        .setTimeOut(10000)   // 设置超时时间，单位是ms毫秒
        .setRetrySleepTime(3000)  // 设置重试的间隔时间
        .setSleepTime(3);      // 设置重试次数

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // Spider.create(new JobProcessor())
        // 		//初始访问url地址
        // 		.addUrl("https://www.bilibili.com/").run();

        Spider spider = Spider.create(new JobProcessor())
            .addUrl("https://jobs.51job.com/wuhan-jhq/156793542.html?s=sou_sou_soulb&t=0")  //设置爬取数据的页面
            //.addPipeline(new FilePipeline("C:\\Users\\tree\\Desktop\\result"))  // 保存到文件中
            .thread(5)  // 开启5个线程
            .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));//设置布隆去重过滤器，指定最多对1000万数据进行去重操作

        // Scheduler scheduler = spider.getScheduler();

        //执行爬虫
        spider.run();
    }
}
