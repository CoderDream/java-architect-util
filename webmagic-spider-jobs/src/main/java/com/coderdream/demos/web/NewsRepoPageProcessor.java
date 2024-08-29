package com.coderdream.demos.web;


import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;


/**
 * https://www.bilibili.com/video/BV1194y1b7Z9
 *
 * @ClassName JobProcessor
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/4 12:43
 * @Version 1.0
 */
public class NewsRepoPageProcessor implements PageProcessor {

    public void process(Page page) {
        // 页面下载成功了
//        System.err.println(page.getHtml());

        // 页面解析
//        String title = page.getHtml().xpath("//div[@class='hidden']/div/a/text()").toString();
//        System.err.println(title);

        List<String> titles = page.getHtml().xpath("//div[@class='hidden']/div/a/text()").all();
        System.err.println(titles);

        // 添加属性
        page.putField("titles", titles);

        // 标题
        // CSS选择器
//        page.putField("author", page.getHtml().css("div.b-wrap>a").all());
//
//        // XPath选择器
//        page.putField("div", page.getHtml().xpath("//div[@id=app]/div/div/div[@class=b-wrap]/div/div[@id" +
//            "=primaryPageTab]/ul/li/a/span/text()"));
//        // 正则表达式
//        page.putField("div3", page.getHtml().css("div.b-wrap>a").regex(".*年轻人.*").all());
//
//        // 处理结果API 返回一条数据
//        page.putField("div4", page.getHtml().css("div.b-wrap>a").regex(".*年轻人.*").get());
//        page.putField("div5", page.getHtml().css("div.b-wrap>a").regex(".*年轻人.*").toString());
//
//        // 获取链接 全部
//        page.addTargetRequests(page.getHtml().css("div.b-wrap").links().all());
//        // 获取上方查询出的url里面的元素
//        page.putField("url",page.getHtml().css("div.nav-search from input").all());
//
//        // 抓取链接
//        page.addTargetRequest("https://jobs.51job.com/wuhan-jhq/156793542.html?s=sou_sou_soulb&t=0");

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
        Spider.create(new NewsRepoPageProcessor())
            .addUrl("https://news.163.com/world/")//添加解析的网址
            .addPipeline(new Pipeline() {
                @Override
                public void process(ResultItems resultItems, Task task) {
                    List<String> titles = resultItems.get("titles");
                    for (String title: titles) {
                        System.out.println(title);

                    }

                    // 持久化到数据库
                }
            })
            .thread(5)//开启5个线程去访问
            .run();//运行
    }
}
