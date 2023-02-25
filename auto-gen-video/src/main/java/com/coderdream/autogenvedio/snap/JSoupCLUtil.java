package com.coderdream.autogenvedio.snap;

import cn.hutool.core.util.StrUtil;
import com.coderdream.autogenvedio.entity.PageInfoEntity;
import com.coderdream.autogenvedio.util.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 在Jsoup.jar中集成代理ip的设置功能，方便用户使用，
 * 例：Jsoup.connect("http://1212.ip138.com/ic.asp").proxy("test.proxy.mayidaili.com", 8123, null).header("Proxy-Authorization", authHeader).get();
 */
public class JSoupCLUtil {


    public final static String TR_CLASS = "tr3 t_one tac";

    /**
     * 应用名称
     */
    public final static String TAL_CLASS = "tal";

    public final static String PC_CONTENT_CLASS = "tpc_content"; // tpc_content  pc_content do_not_catch

    public final static String POST_DATE_CLASS = "f12";

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

        String baseUrl = "https://cl.6962z.xyz/";
        String url = "https://www.cnblogs.com/NieXiaoHui/p/14741844.html";
        url = baseUrl + "thread0806.php?fid=7&search=571588&page=1";
        url = baseUrl + "thread0806.php?fid=7&search=571588&page=2";
//        url = "https://mp.weixin.qq.com/s/XMKVpHfGHhPQQ4MCbkDi-w";
//        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Jsoup").get();


        Document document = JSoupCLUtil.getDocument(url);
//        Element firstHeading = document.getElementsByClass("").first();
//        System.out.println(firstHeading.text());
        // inline-list__item inline-list__item--bulleted app-header__list__item--price
//        System.out.println(getHrefByClass(document, TAL_CLASS));
//        System.out.println(getContentByClass(document, APP_CATEGORY_CLASS));
//        System.out.println(getContentByClass(document, PRICE_CLASS));
////        System.out.println(getContentByClass(document, WE_STAR_RATING_CLASS));
//        System.out.println(getContentByClass(document, WE_RATING_COUNT_STAR_RATING_COUNT_CLASS));
//        System.out.println(getContentByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
//        System.out.println(getContentListByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
//        System.out.println(getContentByClass(document, SECTION_DESCRIPTION_CLASS));
//        url = "https://cl.6803x.xyz/htm_data/2206/7/5153315.html";

//        url = "https://mp.weixin.qq.com/s/XMKVpHfGHhPQQ4MCbkDi-w";
//        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Jsoup").get();
        document = JSoupCLUtil.getDocument(url);
//        Element firstHeading = document.getElementsByClass("").first();
//        System.out.println(firstHeading.text());
        // inline-list__item inline-list__item--bulleted app-header__list__item--price
//        System.out.println(getHrefByClass(document, TAL_CLASS));
//
//        List<PageInfoEntity> pageInfoEntityList = JSoupCLUtil.getPageInfoEntityByClass(document, TAL_CLASS);
//        for (PageInfoEntity pageInfoEntity : pageInfoEntityList) {
//            url = baseUrl + pageInfoEntity.getPageUrl();
//            System.out.println(url);
////            document = JSoupCLUtil.getDocument(url);
////            Element firstHeading = document.getElementsByClass(PC_CONTENT_CLASS).first();
////            System.out.println(firstHeading.text());
//        }

        System.out.println(url);
//        List<PageInfoEntity> pageInfoEntityList = JSoupCLUtil.getPageInfoEntityByClassV1(document, TR_CLASS);
//        List<PageInfoEntity> pageInfoEntityList = FileUtils.readFile();
//        String fileName;
//        for (PageInfoEntity pageInfoEntity : pageInfoEntityList) {
//            url = baseUrl + pageInfoEntity.getPageUrl();
////            System.out.println(url);
//            Integer period = new Random().nextInt(8) + 3;
//            try {
//                Thread.sleep(1000 * period);   // 休眠3秒
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(pageInfoEntity);
//            document = JSoupCLUtil.getDocument(url);
//            Element firstHeading = document.getElementsByClass(PC_CONTENT_CLASS).first();
////            System.out.println(firstHeading.text());
//            fileName = pageInfoEntity.getPostDate() + ".txt";
//            FileUtils.writeToFile(fileName, firstHeading.text());
//        }
    }

    public static String getContentByClass(Document document, String className) {
        Element element = document.getElementsByClass(className).first();
//        System.out.println(element.text());
        return element.text();
    }


    public static List<PageInfoEntity> getPageInfoEntityByClass(Document document, String className) {
        List<PageInfoEntity> pageInfoEntityList = new ArrayList<>();
        PageInfoEntity pageInfoEntity = new PageInfoEntity();
        //根据class获取到 页面的 元素内容
        Elements tables = document.getElementsByClass(className);
        //根据td标签来划分
        Elements td = tables.select("td");
        int index1;
        int index2;
        int index3;
        for (int j = 2; j < td.size(); j++) {
            String text0 = td.get(j).text();
            System.out.println(j + "\ttext: " + text0);
            //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
            String url0 = td.get(j).select("a").attr("href");
            System.out.println(j + "\turl: " + url0);


            index1 = j + 2;
            if (index1 % 5 == 0) {
                pageInfoEntity = new PageInfoEntity();
            }
            //获取到标签中的内容
            index2 = j + 1;
            if (index2 % 5 == 0) {
                String text = td.get(j).text();
                pageInfoEntity.setPageTitle(text);
                System.out.println(j + "\ttext: " + text);
                //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
                String url = td.get(j).select("a").attr("href");
                System.out.println(j + "\turl: " + url);
                pageInfoEntity.setPageUrl(url);
            }

            //获取到标签中的内容
            index3 = j + 2;
            if (index3 % 5 == 0) {
                String text = td.get(j).text();
//                System.out.println("postDate: " + text);
                if (text.length() > 5) {
                    pageInfoEntity.setPostDate(text.substring(5));
                }
//                System.out.println(j + "\ttext: " + text);
//                //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
//                String url = td.get(j).select("a").attr("href");
//                System.out.println(j + "\turl: " + url);
//                pageInfoEntity.setPageUrl(url);
            }

            if (StrUtil.isNotEmpty(pageInfoEntity.getPageTitle())
                    && Character.isDigit(pageInfoEntity.getPageTitle().charAt(0))
                    && pageInfoEntity.getPostDate() != null
                    && index3 % 5 == 0) {
                pageInfoEntityList.add(pageInfoEntity);
            }
        }

//        Element element = document.getElementsByClass(className).first();
//        System.out.println(element.text());
//        return element.text();

        return pageInfoEntityList;
    }

    public static List<PageInfoEntity> getPageInfoEntityByClassV1(Document document, String className) {
        List<PageInfoEntity> pageInfoEntityList = new ArrayList<>();
        PageInfoEntity pageInfoEntity = new PageInfoEntity();
        //根据class获取到 页面的 元素内容
        Elements tables = document.getElementsByClass(className);
        //根据td标签来划分
        Elements td = tables.select("td");
        int index1;
        int index2;
        int index3;
        for (int j = 0; j < td.size(); j++) {
            index1 = j + 1;
            if (index1 % 5 == 0) {
                pageInfoEntity = new PageInfoEntity();
            }
            //获取到标签中的内容
            index2 = j + 4;
            if (index2 % 5 == 0) {
                String text = td.get(j).text();
                pageInfoEntity.setPageTitle(text);
                System.out.println(j + "\ttext: " + text);
                //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
                String url = td.get(j).select("a").attr("href");
                System.out.println(j + "\turl: " + url);
                pageInfoEntity.setPageUrl(url);
            }

            //获取到标签中的内容
            index3 = j + 3;
            if (index3 % 5 == 0) {
                String text = td.get(j).text();
//                System.out.println("postDate: " + text);
                if (text.length() > 5) {
                    pageInfoEntity.setPostDate(text.substring(5));
                }
//                System.out.println(j + "\ttext: " + text);
//                //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
//                String url = td.get(j).select("a").attr("href");
//                System.out.println(j + "\turl: " + url);
//                pageInfoEntity.setPageUrl(url);
            }

            if (StrUtil.isNotEmpty(pageInfoEntity.getPageTitle())
                    && Character.isDigit(pageInfoEntity.getPageTitle().charAt(0))
                    && pageInfoEntity.getPostDate() != null
                    && index3 % 5 == 0) {
                pageInfoEntityList.add(pageInfoEntity);
            }
        }

//        Element element = document.getElementsByClass(className).first();
//        System.out.println(element.text());
//        return element.text();

        return pageInfoEntityList;
    }

    public static List<PageInfoEntity> getHrefByClass(Document document, String className) {
        List<PageInfoEntity> pageInfoEntityList = new ArrayList<>();
        PageInfoEntity pageInfoEntity;
        //根据class获取到 页面的 元素内容
        Elements tables = document.getElementsByClass(className);
        //根据td标签来划分
        Elements td = tables.select("td");
        for (int j = 0; j < td.size(); j++) {
            pageInfoEntity = new PageInfoEntity();
            //获取到标签中的内容
            String text = td.get(j).text();
            pageInfoEntity.setPageTitle(text);
            System.out.println(text);
            //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
            String url = td.get(j).select("a").attr("href");
            System.out.println(url);
            pageInfoEntity.setPageUrl(url);
            pageInfoEntityList.add(pageInfoEntity);
        }

//        Element element = document.getElementsByClass(className).first();
//        System.out.println(element.text());
//        return element.text();

        return pageInfoEntityList;
    }

    public static List<String> getContentListByClass(Document document, String className) {
        Elements elementList = document.getElementsByClass(className);
//        Element element = document.getElementsByClass(className).first();
//        System.out.println("first: " +  element.text());
        Element element;
        int index = 0;
        do {
            index++;
//            Elements elements = elementList.first();
            element = elementList.first();
            System.out.println("index: " + index + ": " + element.text());
        } while (elementList.next() != null);
        System.out.println(element.text());
        return new ArrayList<>();
    }

    public static Document getDocument(String url) {
        Connection conn = Jsoup.connect(url);
//        conn.userAgent("custom user agent");

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 3294));

//        Jsoup.connect("https://spring.io/blog")
//                .proxy(proxy)
//                .get();
        Document document = null;
        try {
//            document = conn.proxy(proxy).get();

            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
            // handle error
        }
        return document;
    }
}
