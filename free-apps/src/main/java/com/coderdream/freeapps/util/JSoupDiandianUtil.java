package com.coderdream.freeapps.util;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

public class JSoupDiandianUtil {


    @Resource
    private RestTemplate restTemplate;

    /**
     * freeBtn
     */
    public final static String FREE_BTN_CLASS = "freeBtn";

    /**
     * 应用类别
     */

    public final static String APP_CATEGORY_CLASS = "product-header__subtitle app-header__subtitle";
    /**
     * 应用价格
     */
    public final static String PRICE_CLASS = "app-header__list__item--price";

    /**
     * 应用用户评分详情
     */
    public final static String ITEM_USER_RATING_CLASS = "product-header__list__item app-header__list__item--user-rating";

    /**
     * 应用用户评分
     */
    public final static String WE_STAR_RATING_CLASS = "we-star-rating";

    /**
     * 应用用户评分
     */
    public final static String WE_RATING_COUNT_STAR_RATING_COUNT_CLASS = "we-rating-count star-rating__count";


    /**
     * 应用截图 we-artwork--screenshot-platform-iphone  we-screenshot-viewer__screenshots
     */
    public final static String WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS = "we-artwork--screenshot-platform-iphone";

    /**
     * 用户简介
     */
    public final static String SECTION_DESCRIPTION_CLASS = "section__description";

    public final static String CONTENT_SCREEN_CLASS = "content-screen";

    // show-text dd-max-ellipsis

//    public final static String TOTAL_CLASS = "show-text dd-max-ellipsis";


    public final static String TOTAL_CLASS = "dd-table-wrap dd-table-border";

    public static void main(String[] args) throws IOException {
        String url = "https://www.cnblogs.com/NieXiaoHui/p/14741844.html";
        url = "https://apps.apple.com/cn/app/id791684332";
        url = "https://mergeek.com/free/apps?ref=menu&auth=1&mode=woa";

        url = "https://app.diandian.com/rank/ios/1-4-0-75-0?time=1682784000000&device=1";

        // 总榜
        url = "https://app.diandian.com/rank/ios/1-4-0-75-3?time=1682784000000&device=1&timetype=";

//        url = "show-text dd-max-ellipsis";
        //
//        url = "https://mp.weixin.qq.com/s/XMKVpHfGHhPQQ4MCbkDi-w";
//        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Jsoup").get();
//        Document document = JSoupDiandianUtil.getDocument(url);
//        List<String> contentList = getContentListByClass(document, TOTAL_CLASS);
//        for (String content : contentList) {
//            System.out.println(content);
//        }
        queryDocument(null);
    }

    public static void queryDocument(String documentName) {
//        org.apache.http.impl.client.HttpClientBuilder httpClientBuilder;
//        org.apache.http.impl.client.HttpClientBuilder.dnsResolver d;
        WebClient browser = new WebClient();
        browser.getOptions().setCssEnabled(false);
        browser.getOptions().setJavaScriptEnabled(true);
        browser.getOptions().setThrowExceptionOnScriptError(false);
        documentName = "北斗";
        String url = "http://oar.nstl.gov.cn/Paper/Search?searchKey=" + documentName + "&x=0&y=0";

//        url = "https://app.diandian.com/rank/ios/1-4-0-75-3?time=1682784000000&device=1&timetype=";
        try {
            HtmlPage htmlPage = browser.getPage(url);
            browser.waitForBackgroundJavaScript(3000);
            Document document = Jsoup.parse(htmlPage.asXml());
//            Element paper = document.getElementById(TOTAL_CLASS);

            Element paper = document.getElementById("paper");
//            System.out.println(paper);
            url = "https://app.diandian.com/rank/ios/1-4-0-75-3";
            htmlPage = browser.getPage(url);
            browser.waitForBackgroundJavaScript(3000);
            document = Jsoup.parse(htmlPage.asXml());
            paper = document.getElementById(TOTAL_CLASS);

            System.out.println(paper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContentByClass(Document document, String className) {
        Element element = document.getElementsByClass(className).first();
//        System.out.println(element.text());
        return element.text();
    }

    public static List<String> getContentListByClass(Document document, String className) {
//        Elements elementList = document.getElementsByClass(className);
//        Element element = document.getElementsByClass(className).first();
//        System.out.println("first: " +  element.text());
//        Element element;
//        int index = 0;
//        do {
//            index++;
////            Elements elements = elementList.first();
//            element = elementList.first();
//            System.out.println(element.toString());
//            System.out.println("index: " + index + ": " + element.text());
//        } while (elementList.next() != null);

        List<String> stringList = new ArrayList<>();
        Elements elements = document.getElementsByClass(className);

        //获取迭代器
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
//            Node titleNode = element.child(0).childNode(0).childNode(0);
//            Node titleNode1 = element.child(1).childNode(0);
//            stringList.add(titleNode.toString());
//            stringList.add(titleNode1.toString());
            System.out.println(element.toString());
            stringList.add(element.toString());
            //遍历中。。。。。。
        }
        return stringList;

//        System.out.println(element.text());
//        return new ArrayList<>();
    }

    public static Document getDocument(String url) {
        Connection conn = Jsoup.connect(url);
//        conn.userAgent("custom user agent");
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
            // handle error
        }
        return document;
    }
}
