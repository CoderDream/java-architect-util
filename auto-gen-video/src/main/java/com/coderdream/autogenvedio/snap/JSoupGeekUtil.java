package com.coderdream.autogenvedio.snap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSoupGeekUtil {

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


    public static void main(String[] args) throws IOException {
        String url = "https://www.cnblogs.com/NieXiaoHui/p/14741844.html";
        url = "https://apps.apple.com/cn/app/id791684332";
        url = "https://mergeek.com/free/apps?ref=menu&auth=1&mode=woa";
//        url = "https://mp.weixin.qq.com/s/XMKVpHfGHhPQQ4MCbkDi-w";
//        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Jsoup").get();
        Document document = JSoupGeekUtil.getDocument(url);
//        Element firstHeading = document.getElementsByClass("").first();
//        System.out.println(firstHeading.text());
        // inline-list__item inline-list__item--bulleted app-header__list__item--price
        System.out.println(getContentListByClass(document, FREE_BTN_CLASS));
//        System.out.println(getContentByClass(document, APP_CATEGORY_CLASS));
//        System.out.println(getContentByClass(document, PRICE_CLASS));
////        System.out.println(getContentByClass(document, WE_STAR_RATING_CLASS));
//        System.out.println(getContentByClass(document, WE_RATING_COUNT_STAR_RATING_COUNT_CLASS));
//        System.out.println(getContentByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
//        System.out.println(getContentListByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
//        System.out.println(getContentByClass(document, SECTION_DESCRIPTION_CLASS));
    }

    public static String getContentByClass(Document document, String className){
        Element element = document.getElementsByClass(className).first();
//        System.out.println(element.text());
        return element.text();
    }

    public static List<String> getContentListByClass(Document document, String className){
        Elements elementList = document.getElementsByClass(className);
//        Element element = document.getElementsByClass(className).first();
//        System.out.println("first: " +  element.text());
        Element element;
        int index = 0;
        do {
            index++;
//            Elements elements = elementList.first();
            element = elementList.first();
            System.out.println(element.toString());
            System.out.println("index: " + index + ": " + element.text());
        } while (elementList.next() != null);

        System.out.println(element.text());
        return new ArrayList<>();
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
