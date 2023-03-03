package com.coderdream.freeapps.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.AppInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

public class JSoupUtil {

    /**
     * 应用名称 product-header__title app-header__title
     */
    public final static String APP_TITLE_CLASS = "product-header__title app-header__title";

    /**
     * 应用副标题
     */
    public final static String APP_SUBTITLE_CLASS = "product-header__subtitle app-header__subtitle";

    /**
     * 专为设计
     */
    public final static String DESIGNED_FOR_CLASS = "app-header__designed-for";

    /**
     * 评分及排名
     */
    public final static String MOBILE_COMPACT_CLASS = "inline-list inline-list--mobile-compact";

    /**
     * 应用类别
     */

    public final static String APP_CATEGORY_CLASS = "product-header__subtitle app-header__subtitle";
    /**
     * 应用价格
     */
    public final static String PRICE_CLASS = "app-header__list__item--price";


    /**
     * 应用价格（内购）
     */
    public final static String PRICE_IN_APP_PURCHASE_CLASS = "inline-list__item inline-list__item--bulleted app-header__list__item--in-app-purchase";


    /**
     * 内购详情
     */
    public final static String PRICE_LIST_WITH_NUMBERS_ITEM_CLASS = "list-with-numbers__item";

    /**
     * 应用大小
     */
    public final static String INFORMATION_LIST_ITEM_DEFINITION_CLASS = "information-list__item__definition";

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

    /**
     * 截图列表
     */
    public final static String SCREENSHOTS_LIST_CLASS = "we-screenshot-viewer__screenshots-list";

//    public static final String URL =

    public static void main(String[] args) throws IOException {
//        String url = "https://www.cnblogs.com/NieXiaoHui/p/14741844.html";
//        url = "https://apps.apple.com/cn/app/id791684332";
////        url = "https://mp.weixin.qq.com/s/XMKVpHfGHhPQQ4MCbkDi-w";
////        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Jsoup").get();
//        Document document = JSoupUtil.getDocument(url);
////        Element firstHeading = document.getElementsByClass("").first();
////        System.out.println(firstHeading.text());
//        // inline-list__item inline-list__item--bulleted app-header__list__item--price
//        System.out.println(getContentByClass(document, APP_NAME_CLASS));
//        System.out.println(getContentByClass(document, APP_CATEGORY_CLASS));
//        System.out.println(getContentByClass(document, PRICE_CLASS));
//        System.out.println(getContentByClass(document, WE_STAR_RATING_CLASS));
//        System.out.println(getContentByClass(document, WE_RATING_COUNT_STAR_RATING_COUNT_CLASS));
////        System.out.println(getContentByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
////        System.out.println(getContentListByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
//        System.out.println(getContentByClass(document, SECTION_DESCRIPTION_CLASS));
//        https://apps.apple.com/cn/app/id441878713
        String appId = "id1443533088"; //
        appId = "id441878713";
        appId = "id460661291";
        appId = "id1514091454";
        appId = "id1315296783";
        appId = "id1589860768";
        App app = JSoupUtil.crawlerApp(appId);
        System.out.println(app);
    }

    public static App crawlerApp(String appId) {
        App app = new App();
        String urlCn = Constants.URL_CN_BASE + appId + Constants.URL_PLATFORM_IPHONE;
        app.setUrlCn(urlCn);
        String urlUs = Constants.URL_US_BASE + appId + Constants.URL_PLATFORM_IPHONE;
        app.setUrlUs(urlUs);
        app.setAppId(appId);

        Document document = JSoupUtil.getDocument(urlCn);
        if (document == null) {
            System.out.println("urlCn" + appId + " is empty");
            document = JSoupUtil.getDocument(urlUs);
            if (document == null) {
                System.out.println("urlCn" + appId + " is empty");
                app.setRemark(appId + " 已下架！");
                app.setDelFlag(1); // 设为删除
                return app;
            }
        }
//        Element firstHeading = document.getElementsByClass("").first();
//        System.out.println(firstHeading.text());
        // inline-list__item inline-list__item--bulleted app-header__list__item--price
        String title = getTitleByClass(document, APP_TITLE_CLASS);
        app.setTitle(title.trim());
        // 副标签
        String subTitle = getContentByClass(document, APP_SUBTITLE_CLASS);
        app.setSubTitle(subTitle.trim());
        //
        String designedFor = getContentByClass(document, DESIGNED_FOR_CLASS);
        app.setDesignedFor(designedFor.trim());
        //
        List<String> mobileCompactList = getMobileCompactListByClass(document, MOBILE_COMPACT_CLASS);
        if (!CollectionUtils.isEmpty(mobileCompactList)) {
            int mobileCompactListSize = mobileCompactList.size();
            // 评分+价格
            if (mobileCompactListSize == 2) {
                app.setRatingStr(mobileCompactList.get(0));
                app.setPriceStr(mobileCompactList.get(1));
            }
            // 排名+评分+价格
            if (mobileCompactListSize == 3) {
                app.setRanking(mobileCompactList.get(0));
                app.setRatingStr(mobileCompactList.get(1));
                app.setPriceStr(mobileCompactList.get(2));
            }
        }


//        String category = getContentByClass(document, APP_CATEGORY_CLASS);
//        app.setCategory(category);
//        String priceStr = getContentByClass(document, PRICE_CLASS);
//        String priceInAppPurchaseStr = getContentByClass(document, PRICE_IN_APP_PURCHASE_CLASS);
//        if (StrUtil.isNotEmpty(priceInAppPurchaseStr)) {
//            priceStr += Constants.MIDDLE_POINT + priceInAppPurchaseStr; // ••
//            String listWithNumbersItem = getContentByClass(document, PRICE_LIST_WITH_NUMBERS_ITEM_CLASS);
////            System.out.println(listWithNumbersItem);
//
//
//            List<String> listWithNumbersItems = getAppInPurchaseContentListByClass(document, PRICE_LIST_WITH_NUMBERS_ITEM_CLASS);
//            System.out.println(listWithNumbersItems);
//        }
//        app.setPriceStr(priceStr);
        //
//        String appSizeStr = getContentByClass(document, INFORMATION_LIST_ITEM_DEFINITION_CLASS);
//        app.setAppSizeStr(appSizeStr);

        AppInfo appInfo = getAppInfoByClass(document, INFORMATION_LIST_ITEM_DEFINITION_CLASS);
        System.out.println(appInfo);
        app.setSupplier(appInfo.getSupplier());
        app.setAppSizeStr(appInfo.getSize());
        app.setCategory(appInfo.getCategory());
        app.setAge(appInfo.getAge());
        app.setPriceStr(appInfo.getPrice());
        app.setLanguage(appInfo.getLanguage());
        app.setCopyright(appInfo.getCopyright());
        Map<String, String> compatibility = appInfo.getCompatibility();
        if (compatibility != null) {
//            JSONObject jsonObjectCamp =JSONObject.parseObject(compatibility);
//            app.setCompatibility(jsonObjectCamp);

            String json = JSON.toJSONString(compatibility);//map转String
            JSONObject jsonObject = JSON.parseObject(json);//String转json
            app.setCompatibility(jsonObject);

//            JSONObject obj = new JSONObject();
//            {
//                obj.put("key1", "value1");
//                obj.put("key2", "value2");
//                obj.put("key3", "value3");
//            }
//            Map<String, String> params = JSONObject.parseObject(obj.toJSONString(), new TypeReference<Map<String, String>>(){});
//            System.out.println(params);
//
//            输出：{key3=value3, key2=value2, key1=value1}
//————————————————
//            版权声明：本文为CSDN博主「CatEatApple」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//            原文链接：https://blog.csdn.net/CatEatApple/article/details/83926237
        }

        Map<String, String> appInPurchase = appInfo.getAppInPurchase();
        if (appInPurchase != null) {
//            JSONObject jsonObjectCamp =JSONObject.parseObject(compatibility);
//            app.setCompatibility(jsonObjectCamp);

            String jsonAppInPurchase = JSON.toJSONString(appInPurchase);//map转String
            JSONObject jsonObjectAppInPurchase = JSON.parseObject(jsonAppInPurchase);//String转json
            app.setAppInPurchase(jsonObjectAppInPurchase);
        }


        String ratingStr = getContentByClass(document, WE_STAR_RATING_CLASS);
        app.setRatingStr(ratingStr);
        System.out.println(getContentByClass(document, WE_RATING_COUNT_STAR_RATING_COUNT_CLASS));
//        System.out.println(getContentByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
//        System.out.println(getContentListByClass(document, WE_SCREENSHOT_VIEWER_SCREENSHOTS_CLASS));
        String description = getContentByClass(document, SECTION_DESCRIPTION_CLASS);
        app.setDescriptionCn(description);


        List<String> screenshotsList = getSnapshotListByClass(document, SCREENSHOTS_LIST_CLASS);
        Map<String, List<String>> stringListMap = new LinkedHashMap<>();
        stringListMap.put(appId, screenshotsList);
        if (stringListMap != null) {
            String jsonSnapshotUrl = JSON.toJSONString(stringListMap);//map转String
            JSONObject jsonObjectSnapshotUrl = JSON.parseObject(jsonSnapshotUrl);//String转json
            app.setSnapshotUrl(jsonObjectSnapshotUrl);
        }

        return app;
    }

    public static String getTitleByClass(Document document, String className) {

        Elements elements = document.getElementsByClass(className);
        if (elements.size() > 0) {
            Element element = elements.get(0);
            if (element.childNodeSize() > 0) {
                TextNode textNode = (TextNode) element.childNode(0);
                return textNode.text();
            }
        }

        return "";
    }

    public static String getContentByClass(Document document, String className) {
        Element element = document.getElementsByClass(className).first();
        if (element != null) {
//            Element elementNext = element.ne
//        System.out.println(element.text());
            return element.text();
        }

        return "";
    }

    /**
     * 获取应用内购买信息
     * jsoup-Elements的遍历（使用Iterator迭代器）
     * https://blog.csdn.net/shengfn/article/details/53584339
     *
     * @param document
     * @param className
     * @return
     */
    public static List<String> getMobileCompactListByClass(Document document, String className) {
        List<String> stringList = new ArrayList<>();
        Elements elements = document.getElementsByClass(className);
        // 排名
        Element elementRaking = null;
        // 评分
        Element elementRating = null;
        // 价格
        Element elementPrice = null;
        if (elements != null) {
            if (elements.size() == 2) {
                // 评分
                elementRating = elements.get(0);
                // 价格
                elementPrice = elements.get(1);
            }
            if (elements.size() == 3) {
                // 排名
                elementRaking = elements.get(0);
                // 评分
                elementRating = elements.get(1);
                // 价格
                elementPrice = elements.get(2);
            }

            if (elementRaking != null && elementRaking.childNodeSize() > 1) {
                Element elementRakingLevel2 = (Element) elementRaking.childNode(1);
                if (elementRakingLevel2.childNodeSize() > 0) {
                    TextNode elementRakingLevel3 = (TextNode) elementRakingLevel2.childNode(0);
//                    System.out.println(elementRakingLevel3.text());
                    stringList.add(elementRakingLevel3.text().trim());
                }
            }

            if (elementRating != null && elementRating.childNodeSize() > 1) {
                Element elementRatingLevel2 = (Element) elementRating.childNode(1);
                if (elementRatingLevel2.childNodeSize() > 1) {
                    Element elementRatingLevel3 = (Element) elementRatingLevel2.childNode(1);
                    if (elementRatingLevel3.childNodeSize() == 5) {
                        Element elementRatingLevel4 = (Element) elementRatingLevel3.childNode(3);
//                        System.out.println(elementRatingLevel4.text());
                        stringList.add(elementRatingLevel4.text().trim());
                    }
                }
            }

            if (elementPrice != null && elementPrice.childNodeSize() > 1) {
                Object o = elementPrice.childNode(1);
                if (o instanceof Element) {
                    Element elementPriceLevel2 = (Element) o;
                    if (elementPriceLevel2.childNodeSize() > 0) {
                        TextNode elementPriceLevel3 = (TextNode) elementPriceLevel2.childNode(0);
//                    System.out.println(elementPriceLevel3.text());
                        stringList.add(elementPriceLevel3.text().trim());
                    }
                } else {
                    System.out.println("##### elementPrice " + o.toString());
                }
            }
        }

        return stringList;
    }

    /**
     * 获取应用内购买信息
     * jsoup-Elements的遍历（使用Iterator迭代器）
     * https://blog.csdn.net/shengfn/article/details/53584339
     *
     * @param document
     * @param className
     * @return
     */
    public static List<String> getAppInPurchaseContentListByClass(Document document, String className) {
        List<String> stringList = new ArrayList<>();
        Elements elements = document.getElementsByClass(className);

        //获取迭代器
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            Node titleNode = element.child(0).childNode(0).childNode(0);
            Node titleNode1 = element.child(1).childNode(0);
            stringList.add(titleNode.toString());
            stringList.add(titleNode1.toString());
            //遍历中。。。。。。
        }
        return stringList;
    }


    /**
     * jsoup-Elements的遍历（使用Iterator迭代器）
     * https://blog.csdn.net/shengfn/article/details/53584339
     * AppInfo
     *
     * @param document
     * @param className
     * @return
     */
    public static AppInfo getAppInfoByClass(Document document, String className) {
        AppInfo appInfo = new AppInfo();
        Elements elements = document.getElementsByClass(className);

        if (elements != null) {
            if (elements.size() == 8) {
                parseElementEight(appInfo, elements);
            }

            if (elements.size() == 9) {
                parseElementEight(appInfo, elements);
                Element elementValue;
                Element elementKey;
                Element element9 = elements.get(8);
                if (element9.childNodeSize() > 1) {
                    Node temp = element9.childNode(1);
                    if (temp.childNodeSize() > 1) {
                        Element element9Data = (Element) temp.childNode(1);
                        int element9Size = element9Data.childNodeSize();
                        Element element9Temp;
                        Map<String, String> appInPurchase = new LinkedHashMap<>();
                        for (int i9 = 0; i9 < element9Size - 2; i9++) {
                            i9++;
                            element9Temp = (Element) element9Data.childNode(i9);
                            if (element9Temp.childNodeSize() == 5) {
                                elementKey = (Element) element9Temp.childNode(1);
                                elementValue = (Element) element9Temp.childNode(3);
                                appInPurchase.put(elementKey.text(), elementValue.text());
                            }
                        }
                        appInfo.setAppInPurchase(appInPurchase);
                    }
                }
            }
        }
        return appInfo;
    }

    private static void parseElementEight(AppInfo appInfo, Elements elements) {
        Element element1 = elements.get(0);
        TextNode titleNode01 = (TextNode) element1.childNode(0);
        appInfo.setSupplier(titleNode01.text());

        Element element2 = elements.get(1);
        TextNode titleNode02 = (TextNode) element2.childNode(0);
        appInfo.setSize(titleNode02.text());

        Element element3 = elements.get(2);
        TextNode titleNode03 = (TextNode) element3.childNode(1).childNode(0);
        String category = titleNode03.text();
        appInfo.setCategory(category.trim());

        Element element4 = elements.get(3);
        int element4Size = element4.childNodeSize();
        Element element4Temp;
        Element elementKey;
        Element elementValue;
        Map<String, String> compatibility = new LinkedHashMap<>();
        for (int i4 = 0; i4 < element4Size - 1; i4++) {
            i4++;
            element4Temp = (Element) element4.childNode(i4);
            if (element4Temp.childNodeSize() == 5) {
                elementKey = (Element) element4Temp.childNode(1);
                elementValue = (Element) element4Temp.childNode(3);
                compatibility.put(elementKey.text(), elementValue.text());
            }
        }

        appInfo.setCompatibility(compatibility);

        Element element5 = elements.get(4);
        if (element5 != null) {
            if (element5.childNodeSize() > 1) {
                if(element5.childNode(1) instanceof Element) {
                    Node titleNode05 = element5.childNode(1).childNode(0);
                    String language = titleNode05.toString();
                    appInfo.setLanguage(language);
                }
            }
        }

        Element element6 = elements.get(5);
        if (element6 != null) {
            TextNode titleNode06 = (TextNode) element6.childNode(0);
            String age = titleNode06.text();
            appInfo.setAge(age.trim());
        }

        Element element7 = elements.get(6);
        if (element7 != null) {
            if (element7.childNodeSize() > 0) {
                if( element7.childNode(0) instanceof TextNode) {
                    TextNode titleNode07 = (TextNode) element7.childNode(0);
                    String copyright = titleNode07.text();
                    appInfo.setCopyright(copyright);
                }
            }
        }

        Element element8 = elements.get(7);
        if (element8 != null) {
            if(element8.childNodeSize() > 0) {
                if( element8.childNode(0) instanceof TextNode) {
                    TextNode titleNode08 = (TextNode) element8.childNode(0);
                    String price = titleNode08.text();
                    appInfo.setPrice(price);
                }
            }
        }
    }


    /**
     * jsoup-Elements的遍历（使用Iterator迭代器）
     * https://blog.csdn.net/shengfn/article/details/53584339
     * AppInfo
     *
     * @param document
     * @param className
     * @return
     */
    public static List<String> getSnapshotListByClass(Document document, String className) {
        List<String> stringList = new ArrayList<>();
        Elements elements = document.getElementsByClass(className);

        if (elements != null) {
            Element elementSnapshotLevel1 = elements.get(0);
            // 价格

            if (elementSnapshotLevel1 != null && elementSnapshotLevel1.childNodeSize() > 1) {
//                Element elementRatingLevel2 = (Element) elementSnapshotLevel1.childNode(1);

                if (elementSnapshotLevel1.childNodeSize() > 5) {
                    int size = elementSnapshotLevel1.childNodeSize();
                    for (int i = 2; i < size - 1; i++) {
                        i++;
                        Object objectLevel1 = elementSnapshotLevel1.childNode(i);
                        if (objectLevel1 instanceof Element) {
                            Element elementLevel2 = (Element) objectLevel1;
                            if (elementLevel2.childNodeSize() == 3) {

                                Element elementLevel3 = (Element) elementLevel2.childNode(1);
                                if (elementLevel3.childNodeSize() > 4) {

                                    Element elementLevel41 = (Element) elementLevel3.childNode(1);

                                    Attributes attributes41 = elementLevel41.attributes();
//                                attributes41.iterator();
                                    //获取迭代器
                                    Iterator it = attributes41.iterator();
                                    while (it.hasNext()) {
                                        Attribute element = (Attribute) it.next();
//                                    Element element = (Element) it.next();
////            Node titleNode = element.child(0).childNode(0);
//                                    Node titleNode = element.childNode(0);
//                                    stringList.add(titleNode.toString());
                                        //遍历中。。。。。。
//                                        System.out.println(element.getKey());
//                                        System.out.println(element.getValue());
                                        String value = element.getValue();
                                        int index = value.indexOf(Constants.SNAPSHOT_JPG_SUFFIX);
                                        if (index != -1) {
                                            String beginPart = value.substring(0, index);
                                            int indexHttp = beginPart.lastIndexOf("http");
                                            String url = value.substring(indexHttp, index + 3);
//                                            System.out.println(url);
                                            stringList.add(url);
                                        }
                                        index = value.indexOf(Constants.SNAPSHOT_PNG_SUFFIX);
                                        if (index != -1) {
                                            String beginPart = value.substring(0, index);
                                            int indexHttp = beginPart.lastIndexOf("http");
                                            String url = value.substring(indexHttp, index + 3);
//                                            System.out.println(url);
                                            stringList.add(url);
                                        }
                                    }
//                                System.out.println(attributes.toString());
                                    //String[]
                                    Object element42 = elementLevel3.childNode(2);
                                    Object element43 = elementLevel3.childNode(3);
//                                Object element44 = elementLevel3.childNode(4);
//                                Object element45 = elementLevel3.childNode(5);


//                                Element elementLevel42 = (Element) elementLevel3.childNode(2);
                                    if (element43 instanceof Element) {
                                        Element elementLevel43 = (Element) element43;
//                                Element elementLevel44 = (Element) elementLevel3.childNode(4);
//                                    Element elementLevel45 = (Element) elementLevel3.childNode(5);

                                        Attributes attributes43 = elementLevel43.attributes();
//                                attributes42.iterator();
                                        //获取迭代器
                                        it = attributes43.iterator();
                                        while (it.hasNext()) {
                                            Attribute element = (Attribute) it.next();
//                                    Element element = (Element) it.next();
////            Node titleNode = element.child(0).childNode(0);
//                                    Node titleNode = element.childNode(0);
//                                    stringList.add(titleNode.toString());
                                            //遍历中。。。。。。
//                                            System.out.println(element.getKey());
//                                            System.out.println(element.getValue());
                                            String value = element.getValue();
                                            int index = value.indexOf(Constants.SNAPSHOT_JPG_SUFFIX);
                                            if (index != -1) {
                                                String beginPart = value.substring(0, index);
                                                int indexHttp = beginPart.lastIndexOf("http");
                                                String url = value.substring(indexHttp, index + 3);
//                                                System.out.println(url);
                                                stringList.add(url);
                                            }
                                            index = value.indexOf(Constants.SNAPSHOT_PNG_SUFFIX);
                                            if (index != -1) {
                                                String beginPart = value.substring(0, index);
                                                int indexHttp = beginPart.lastIndexOf("http");
                                                String url = value.substring(indexHttp, index + 3);
//                                                System.out.println(url);
                                                stringList.add(url);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        for (String str: stringList) {
//            System.out.println("url:  ###" + str);
//        }

        return stringList;
    }

    /**
     * jsoup-Elements的遍历（使用Iterator迭代器）
     * https://blog.csdn.net/shengfn/article/details/53584339
     * AppInfo
     *
     * @param document
     * @param className
     * @return
     */
    public static List<String> getContentListByClass(Document document, String className) {
        List<String> stringList = new ArrayList<>();
        Elements elements = document.getElementsByClass(className);

        //获取迭代器
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
//            Node titleNode = element.child(0).childNode(0);
            Node titleNode = element.childNode(0);
            stringList.add(titleNode.toString());
            //遍历中。。。。。。
        }
        return stringList;
    }

    public static List<String> getContentListByClassV2(Document document, String className) {
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
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            // handle error
            return null;
        }
        return document;
    }
}
