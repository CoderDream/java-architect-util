package com.coderdream.demos.web.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


/**
 * <pre>
 * 作者：洋桃指鹿
 * 链接：https://www.zhihu.com/question/585988860/answer/2931182707
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * </pre>
 *
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class GithubXpathPageProcessor implements PageProcessor {

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
        .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
        .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
        .setCharset("UTF-8");

    private static final int voteNum = 1000;

    @Override
    public void process(Page page) {
        List<String> names = page.getHtml().xpath("//h1/strong/a/text()").all();
        List<String> authors = page.getHtml().xpath("//span[@class='author']/a/text()").all();
        List<String> descriptions = page.getHtml().xpath("//p[@class='description']/text()").all();
        List<String> stars = page.getHtml().xpath("//a[@class='social-count js-social-count']/text()").all();

        for (int i = 0; i < names.size(); i++) {
            System.out.println("Name: " + names.get(i));
            System.out.println("Author: " + authors.get(i));
            System.out.println("Description: " + descriptions.get(i));
            System.out.println("Stars: " + stars.get(i));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ZhihuPageProcessor()).
            addUrl("https://github.com/trending?l=java").
//            addPipeline(new FilePipeline("D:\\webmagic\\")).
//            thread(5).
    run();
    }
}

//public class GithubXpathPageProcessor extends Spider implements PageProcessor {
//
//    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
//
//    public GithubXpathPageProcessor() {
//        super(new MyProcessor());
//    }
//
//    /**
//     * process the page, extract urls to fetch, extract the data and store
//     *
//     * @param page page
//     */
//    @Override
//    public void process(Page page) {
//
//    }
//
//    public Site getSite() {
//        return site;
//    }
//
//    private static class MyProcessor implements PageProcessor {
//
//        private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
//
//        public void process(Page page) {
//            List<String> names = page.getHtml().xpath("//h1/strong/a/text()").all();
//            List<String> authors = page.getHtml().xpath("//span[@class='author']/a/text()").all();
//            List<String> descriptions = page.getHtml().xpath("//p[@class='description']/text()").all();
//            List<String> stars = page.getHtml().xpath("//a[@class='social-count js-social-count']/text()").all();
//
//            for (int i = 0; i < names.size(); i++) {
//                System.out.println("Name: " + names.get(i));
//                System.out.println("Author: " + authors.get(i));
//                System.out.println("Description: " + descriptions.get(i));
//                System.out.println("Stars: " + stars.get(i));
//            }
//        }
//
//        public Site getSite() {
//            return site;
//        }
//    }
//
//    public static void main(String[] args) {
//        Spider spider = new GithubXpathPageProcessor();
//        spider.run("https://github.com/trending?l=java");
//    }
//}
