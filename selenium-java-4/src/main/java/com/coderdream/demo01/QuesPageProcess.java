package com.coderdream.demo01;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/*1.实现PageProcessor接口*/
public class QuesPageProcess implements PageProcessor{
    /*2.设置抓取网站的相关配置*/
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public Site getSite() {
        // TODO Auto-generated method stub
        /*3.返回site*/
        return site;
    }

    /*5.爬取逻辑*/
    @Override
    public void process(Page page) {
        // TODO Auto-generated method stub
        page.putField("html", page.getHtml());
        String pageHtml = page.getResultItems().get("html").toString();
        System.err.println(pageHtml);
    }

    /*4.爬取*/
    public static void main(String[] args) {
        /*添加爬取的url链接，开启5个线程爬取*/
        Spider spider = Spider.create(new QuesPageProcess())
            .addUrl("https://www.tiku.com/tiku")
            .thread(5);
        /*爬虫启动*/
        spider.run();
    }
}

