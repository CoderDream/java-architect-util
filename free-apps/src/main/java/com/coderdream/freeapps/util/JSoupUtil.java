package com.coderdream.freeapps.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.coderdream.freeapps.handler.DailyPriceHandler;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.AppInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class JSoupUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JSoupUtil.class);
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
        appId = "id1443533088";
        appId = "id1536924612"; // 美区限免
        appId = "id529433904"; // 找不到描述
        appId = "id1071186450";
        appId = "id1454412797"; // del_flag 为空
        appId = "id1443533088"; // title相同
        appId = "id1445387613"; // 1.3K
        appId = "id1560807339"; // 无截图
        App app = JSoupUtil.crawlerApp(appId, null);
        System.out.println(app);
    }

    public static App crawlerApp(App app) {
        return crawlerApp(app.getAppId(), app.getUsFlag());
    }

    public static App crawlerApp(String appId, Integer usFlag) {
        App app = new App();
        String urlCn = Constants.URL_CN_BASE + appId + Constants.URL_PLATFORM_IPHONE;
        app.setUrlCn(urlCn);
        String urlUs = Constants.URL_US_BASE + appId + Constants.URL_PLATFORM_IPHONE;
        app.setUrlUs(urlUs);
        app.setAppId(appId);
        Document document;
        try {
            // 国区
            if (usFlag == null || usFlag == 0) {
                document = JSoupUtil.getDocument(urlCn);
                if (document == null) {
                    document = JSoupUtil.getDocument(urlUs);

                    if (document == null) {
                        logger.error("urlCn：" + appId + " 已下架！");
                        app.setRemark(appId + " 已下架！");
                        app.setDelFlag(1); // 设为删除
                        return app;
                    }
                    usFlag = 1;
                    app.setUsFlag(1); // 美区
                } else {
                    usFlag = 0;
                    app.setUsFlag(0); // 国区
                }
            }
            // 美区
            else {
                document = JSoupUtil.getDocument(urlUs);
                if (document == null) {
                    logger.error("urlUs：" + appId + " 已下架！");
                    app.setRemark(appId + " 已下架！");
                    app.setDelFlag(1); // 设为删除
                    return app;
                } else {
                    usFlag = 1;
                    app.setUsFlag(1); // 美区
                }
            }
            // 标签（应用名称）
            String title = getTitleByClass(document, APP_TITLE_CLASS);
            app.setTitle(title.trim());
            // 副标签
            String subTitle = getContentByClass(document, APP_SUBTITLE_CLASS);
            app.setSubTitle(subTitle.trim());
            // 专为设计
            String designedFor = getContentByClass(document, DESIGNED_FOR_CLASS);
            app.setDesignedFor(designedFor.trim());
            // 评分区
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

            // 价格标签（含是否内购）
            String priceStr = getContentByClass(document, PRICE_CLASS);
            String priceInAppPurchaseStr = getContentByClass(document, PRICE_IN_APP_PURCHASE_CLASS);
            if (StrUtil.isNotEmpty(priceInAppPurchaseStr)) {
                priceStr += Constants.MIDDLE_POINT + priceInAppPurchaseStr; // •
                app.setInPurchaseFlag(1); // 有应用内购买
            }
            app.setPriceStr(priceStr);
            // 详情
            AppInfo appInfo = getAppInfoByClass(document, INFORMATION_LIST_ITEM_DEFINITION_CLASS);
            System.out.println(appInfo);
            if (StrUtil.isNotEmpty(appInfo.getSupplier())) {
                app.setSupplier(appInfo.getSupplier());
            } else {
                logger.error("应用详情获取失败");
            }
            app.setAppSizeStr(appInfo.getSize());
            app.setCategory(appInfo.getCategory());
            app.setAge(appInfo.getAge());
//        app.setPriceStr(appInfo.getPrice()); 不能用这里的价格信息，因为不包含是否应用内购
            String priceStrAppInfo = appInfo.getPrice();
            if (StrUtil.isNotEmpty(priceStrAppInfo)) {
                String realPrice = AppStringUtils.filterPriceStr(priceStrAppInfo);

                try {
                    // 设置国区、美区价格
                    if (StrUtil.isNotEmpty(realPrice) && !realPrice.equals("Free") && !realPrice.equals("免费")) {
                        if (usFlag != null && usFlag == 1) {
                            app.setPriceUs(BigDecimal.valueOf(Double.valueOf(realPrice)));
                        } else {
                            app.setPriceCn(Integer.valueOf(realPrice));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            } else {
                logger.error("价格信息为空");
            }
            // 语言
            app.setLanguage(appInfo.getLanguage());
            // 版权
            app.setCopyright(appInfo.getCopyright());
            // 兼容性
            Map<String, String> compatibility = appInfo.getCompatibility();
            if (compatibility != null) {
                String json = JSON.toJSONString(compatibility);//map转String
                JSONObject jsonObject = JSON.parseObject(json);//String转json
                app.setCompatibility(jsonObject);
            }

            Map<String, String> appInPurchase = appInfo.getAppInPurchase();
            if (appInPurchase != null) {
                // 参考：https://blog.csdn.net/CatEatApple/article/details/83926237
                String jsonAppInPurchase = JSON.toJSONString(appInPurchase);//map转String
                JSONObject jsonObjectAppInPurchase = JSON.parseObject(jsonAppInPurchase);//String转json
                app.setAppInPurchase(jsonObjectAppInPurchase);
                Map<String, String> params = JSONObject.parseObject(jsonObjectAppInPurchase.toJSONString(),
                        new TypeReference<Map<String, String>>() {
                        });
                Double appInPurchaseTotal = new Double(0);
                for (String value : params.values()) {
                    String realPriceStr = AppStringUtils.filterPriceStr(value);
                    if (StrUtil.isNotEmpty(realPriceStr)) {
                        appInPurchaseTotal += Double.valueOf(realPriceStr);
                    }
                }
                // 应用内购限免
                if (appInPurchaseTotal == 0.0) {
                    app.setInPurchaseFreeFlag(1);
                } else {
                    app.setInPurchaseFreeFlag(0);
                }
            }

            String ratingStr = getContentByClass(document, WE_STAR_RATING_CLASS);
            app.setRatingStr(ratingStr);
            // 处理评分及投票人数
            if (StrUtil.isNotEmpty(ratingStr) && ratingStr.split(Constants.MIDDLE_POINT).length == 2) {
                String ratingTemp = ratingStr.split(Constants.MIDDLE_POINT)[0];
                String rateAmountTemp = ratingStr.split(Constants.MIDDLE_POINT)[1];
                rateAmountTemp = rateAmountTemp.replaceAll("个评分", "");
                rateAmountTemp = rateAmountTemp.replaceAll("Ratings", "");
                rateAmountTemp = rateAmountTemp.replaceAll("Rating", "");
                if (StrUtil.isNotEmpty(ratingTemp)) {
                    if (ratingTemp.lastIndexOf("K") != -1) {
                        ratingTemp = ratingTemp.replaceAll("K", "");
                        app.setRating(BigDecimal.valueOf(Double.valueOf(ratingTemp) * 1000));
                    } else if (ratingTemp.lastIndexOf("万") != -1) {
                        ratingTemp = ratingTemp.replaceAll("万", "");
                        app.setRating(BigDecimal.valueOf(Double.valueOf(ratingTemp) * 10000));
                    } else {
                        app.setRating(BigDecimal.valueOf(Double.valueOf(ratingTemp)));
                    }
                }
                if (StrUtil.isNotEmpty(rateAmountTemp)) {
                    if (rateAmountTemp.lastIndexOf("万") != -1) {
                        rateAmountTemp = rateAmountTemp.replaceAll("万", "");
                        Double d = Double.valueOf(rateAmountTemp);
                        d *= 10000;
                        app.setRateAmount(d.intValue());
                    } else if (rateAmountTemp.lastIndexOf("K") != -1) {
                        rateAmountTemp = rateAmountTemp.replaceAll("K", "");
                        Double d = Double.valueOf(rateAmountTemp);
                        d *= 1000;
                        app.setRateAmount(d.intValue());
                    } else {
                        app.setRateAmount(Integer.valueOf(AppStringUtils.filterPriceStr(rateAmountTemp.trim())));
                    }
                }
            }
            // 应用描述
            String description = getContentByClass(document, SECTION_DESCRIPTION_CLASS);
            // 设置国区、美区描述
            if (usFlag != null && usFlag == 1) {
                app.setDescriptionUs(description);
            } else {
                app.setDescriptionCn(description);
            }
            // 截图
            List<String> screenshotsList = getSnapshotListByClass(document, SCREENSHOTS_LIST_CLASS);
            if(CollectionUtils.isEmpty(screenshotsList)) {
                String urlRaw;
                if(app.getUsFlag() == 1) {
                    urlRaw = Constants.URL_US_BASE + appId;
                } else {
                    urlRaw = Constants.URL_CN_BASE + appId;
                }
                document = JSoupUtil.getDocument(urlRaw);
                screenshotsList = getSnapshotListByClass(document, SCREENSHOTS_LIST_CLASS);
            }

            Map<String, List<String>> stringListMap = new LinkedHashMap<>();
            stringListMap.put(appId, screenshotsList);
            if (stringListMap != null) {
                String jsonSnapshotUrl = JSON.toJSONString(stringListMap);//map转String
                JSONObject jsonObjectSnapshotUrl = JSON.parseObject(jsonSnapshotUrl);//String转json
                app.setSnapshotUrl(jsonObjectSnapshotUrl);
            }
            app.setDelFlag(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
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
                    stringList.add(elementRakingLevel3.text().trim());
                }
            }

            if (elementRating != null && elementRating.childNodeSize() > 1) {
                Element elementRatingLevel2 = (Element) elementRating.childNode(1);
                if (elementRatingLevel2.childNodeSize() > 1) {
                    Element elementRatingLevel3 = (Element) elementRatingLevel2.childNode(1);
                    if (elementRatingLevel3.childNodeSize() == 5) {
                        Element elementRatingLevel4 = (Element) elementRatingLevel3.childNode(3);
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
                        stringList.add(elementPriceLevel3.text().trim());
                    }
                } else {
                    logger.error("##### elementPrice " + o.toString());
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

        Elements elementsNext = elements.next();
        int index = 0;

        //获取迭代器
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            String text = element.text();
            String tagName = element.tagName();
            switch (index) {
                case 0:
                    if ("dd".equals(tagName)) {
                        appInfo.setSupplier(text);
                    }
                    break;
                case 1:
                    if ("dd".equals(tagName)) {
                        appInfo.setSize(text);
                    }
                    break;
                case 2:
                    if ("dd".equals(tagName)) {
                        appInfo.setCategory(text);
                    }
                    break;
                case 3:
                    if ("dd".equals(tagName)) {
                        int element4Size = element.childNodeSize();
                        Element element4Temp;
                        Element elementKey;
                        Element elementValue;
                        Map<String, String> compatibility = new LinkedHashMap<>();
                        for (int i4 = 0; i4 < element4Size - 1; i4++) {
                            i4++;
                            element4Temp = (Element) element.childNode(i4);
                            if (element4Temp.childNodeSize() == 5) {
                                elementKey = (Element) element4Temp.childNode(1);
                                elementValue = (Element) element4Temp.childNode(3);
                                compatibility.put(elementKey.text(), elementValue.text());
                            }
                        }
                        appInfo.setCompatibility(compatibility);
                    }
                    break;
                case 4:
                    if ("dd".equals(tagName)) {
                        appInfo.setLanguage(text);
                    }
                    break;
                case 5:
                    if ("dd".equals(tagName)) {
                        appInfo.setAge(text);
                    }
                    break;
                case 6:
                    if ("dd".equals(tagName)) {
                        appInfo.setCopyright(text);
                    }
                    break;
                case 7:
                    if ("dd".equals(tagName)) {
                        appInfo.setPrice(text);
                    }
                    break;
                case 8:
                    if ("dd".equals(tagName)) {
                        Element elementValue;
                        Element elementKey;
                        if (element.childNodeSize() > 1) {
                            Node temp = element.childNode(1);
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
                    break;
                default:
                    break;
            }
            if ("dd".equals(tagName)) {
                index++;
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
                if (element5.childNode(1) instanceof Element) {
                    Node titleNode05 = element5.childNode(1).childNode(0);
                    String language = titleNode05.toString();
                    appInfo.setLanguage(language);
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
            if (elementSnapshotLevel1 != null && elementSnapshotLevel1.childNodeSize() > 1) {
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
                                    //获取迭代器
                                    Iterator it = attributes41.iterator();
                                    while (it.hasNext()) {
                                        Attribute element = (Attribute) it.next();

                                        String value = element.getValue();
                                        int index = value.indexOf(Constants.SNAPSHOT_JPG_SUFFIX);
                                        if(index == -1) {
                                            index = value.indexOf(Constants.SNAPSHOT_JPG_2_SUFFIX);
                                        }
                                        if (index != -1) {
                                            // 找到从index开始往回的第一个http位置
                                            String beginPart = value.substring(0, index);
                                            int indexHttp = beginPart.lastIndexOf("http");
                                            String url = value.substring(indexHttp, index + 3);
                                            stringList.add(url);
                                        }
                                        index = value.indexOf(Constants.SNAPSHOT_PNG_SUFFIX);
                                        if(index == -1) {
                                            index = value.indexOf(Constants.SNAPSHOT_PNG_2_SUFFIX);
                                        }
                                        if (index != -1) {
                                            String beginPart = value.substring(0, index);
                                            int indexHttp = beginPart.lastIndexOf("http");
                                            String url = value.substring(indexHttp, index + 3);
                                            stringList.add(url);
                                        }
                                    }
                                    Object element43 = elementLevel3.childNode(3);
                                    if (element43 instanceof Element) {
                                        Element elementLevel43 = (Element) element43;
                                        Attributes attributes43 = elementLevel43.attributes();
                                        //获取迭代器
                                        it = attributes43.iterator();
                                        while (it.hasNext()) {
                                            Attribute element = (Attribute) it.next();
                                            String value = element.getValue();
                                            int index = value.indexOf(Constants.SNAPSHOT_JPG_SUFFIX);
                                            if(index == -1) {
                                                index = value.indexOf(Constants.SNAPSHOT_JPG_2_SUFFIX);
                                            }
                                            if (index != -1) {
                                                String beginPart = value.substring(0, index);
                                                int indexHttp = beginPart.lastIndexOf("http");
                                                String url = value.substring(indexHttp, index + 3);
                                                stringList.add(url);
                                            }
                                            index = value.indexOf(Constants.SNAPSHOT_PNG_SUFFIX);
                                            if(index == -1) {
                                                index = value.indexOf(Constants.SNAPSHOT_PNG_2_SUFFIX);
                                            }
                                            if (index != -1) {
                                                String beginPart = value.substring(0, index);
                                                int indexHttp = beginPart.lastIndexOf("http");
                                                String url = value.substring(indexHttp, index + 3);
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
