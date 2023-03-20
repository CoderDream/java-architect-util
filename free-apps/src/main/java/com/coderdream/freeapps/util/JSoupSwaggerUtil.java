package com.coderdream.freeapps.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.AppInfo;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

public class JSoupSwaggerUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JSoupSwaggerUtil.class);

    public final static String DETAIL_MENU_CLASS = "detailMenu";

    public final static String MENU_LI_CLASS = "menuLi";
    public final static String DETAIL_CONTAINER = "nav nav-list";// "bycdao-left left";

    public final static String BASE_URL = "http://172.16.104.131:32355/doc.html";

//    public static final String URL =

    public static void main(String[] args) throws IOException {
        JSoupSwaggerUtil.crawlerCode();
    }

    public static void crawlerCode() {
        Document document;
        try {
            document = Jsoup.parse(new File("D:\\swagger01.html"),
                "utf-8");// JSoupSwaggerUtil.getDocument(BASE_URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<SwaggerInfo> swaggerInfoList = getSwaggerInfoListByClassCode(document, DETAIL_MENU_CLASS);

        if (!CollectionUtils.isEmpty(swaggerInfoList)) {
            for (SwaggerInfo swaggerInfo : swaggerInfoList) {
                System.out.println(swaggerInfo.getTitle() + "\t" + swaggerInfo.getApiAmount() + "\t"
                    + swaggerInfo.getOperate() + "\t"
                    + swaggerInfo.getMethod() + "\t" + swaggerInfo.getApi() + "\t");
            }
        }
    }

    public static void crawlerData() {
        Document document;
        try {
            document = Jsoup.parse(new File("D:\\swagger02.html"),
                "utf-8");// JSoupSwaggerUtil.getDocument(BASE_URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<SwaggerInfo> swaggerInfoList = getSwaggerInfoListByClassData(document, DETAIL_MENU_CLASS);

        if (!CollectionUtils.isEmpty(swaggerInfoList)) {
            for (SwaggerInfo swaggerInfo : swaggerInfoList) {
                System.out.println(swaggerInfo.getTitle() + "\t" + swaggerInfo.getApiAmount() + "\t"
                    + swaggerInfo.getOperate() + "\t"
                    + swaggerInfo.getMethod() + "\t" + swaggerInfo.getApi() + "\t");
            }
        }
    }


    public static List<SwaggerInfo> getSwaggerInfoListByClassData(Document document, String className) {
        List<SwaggerInfo> swaggerInfoList = new ArrayList<>();
        SwaggerInfo swaggerInfo;
        Elements elements = document.getElementsByClass(className);

        Iterator it = elements.iterator();
        int index = 0;
        while (it.hasNext()) {
            Element element = (Element) it.next();
            index++;
            if (index > 3) {
                if (element.childNodeSize() == 4) {
                    Element mainElement1 = element.child(0);
                    Element mainElement102 = mainElement1.child(1);
                    TextNode mainElement1031 = (TextNode) mainElement102.childNode(0);
                    String title = mainElement1031.text();
                    if (StrUtil.isNotEmpty(title)) {
                        title = title.trim();
                    }
                    Element mainElement1032 = (Element) mainElement102.childNode(1);
                    String apiAmount = mainElement1032.text();
                    if (StrUtil.isNotEmpty(apiAmount)) {
                        apiAmount = apiAmount.trim();
                    }

                    Element subElement = element.child(1);
                    int subSize = subElement.childNodeSize();
                    Element item;
                    if (subSize > 0) {
                        for (int i = 0; i < subSize; i++) {
                            if (subElement.childNode(i) instanceof Element) {
                                item = (Element) subElement.childNode(i);
//                                Element mainElement2 = item.child(0);
                                if (item.childNode(1) instanceof Element) {
                                    Element mainElement2 = (Element) item.childNode(1);
                                    if (mainElement2.childNodeSize() == 0) {
                                        System.out.println("##### ERROR ####");
                                        continue; // TODO
                                    }
                                    Element mainElement202 = mainElement2.child(0);
                                    Element mainElement2030 = mainElement202.child(0);
                                    Element mainElement2031 = mainElement202.child(1);
                                    String method = mainElement2030.text();
                                    if (StrUtil.isNotEmpty(method)) {
                                        method = method.trim();
                                    }
                                    String operate = mainElement2031.text();
                                    if (StrUtil.isNotEmpty(operate)) {
                                        operate = operate.trim();
                                    }

                                    Attributes attributes = item.attributes();
                                    String hashUrl = attributes.get("data-hashurl");
                                    swaggerInfo = new SwaggerInfo();
                                    swaggerInfo.setTitle(title);
                                    swaggerInfo.setApiAmount(apiAmount);
                                    swaggerInfo.setOperate(operate);
                                    swaggerInfo.setMethod(method);
                                    swaggerInfo.setHashUrl(hashUrl);
                                    if (StrUtil.isNotEmpty(hashUrl)) {
                                        int slashIndex = hashUrl.lastIndexOf("/");
                                        int usingIndex = hashUrl.lastIndexOf("Using");
                                        String api = hashUrl.substring(slashIndex, usingIndex);
                                        swaggerInfo.setApi(api);
                                    }
//                                    System.out.println(swaggerInfo);
                                    swaggerInfoList.add(swaggerInfo);
                                }
                                if (item.childNode(2) instanceof Element) {
                                    Element mainElement2 = (Element) item.childNode(2);
                                    if (mainElement2.childNodeSize() == 0) {
                                        System.out.println("##### ERROR ####");
                                        continue; // TODO
                                    }
                                    Element mainElement202 = mainElement2.child(0);
                                    Element mainElement2030 = mainElement202.child(0);
                                    Element mainElement2031 = mainElement202.child(1);
                                    String method = mainElement2030.text();
                                    if (StrUtil.isNotEmpty(method)) {
                                        method = method.trim();
                                    }
                                    String operate = mainElement2031.text();
                                    if (StrUtil.isNotEmpty(operate)) {
                                        operate = operate.trim();
                                    }

                                    Attributes attributes = item.attributes();
                                    String hashUrl = attributes.get("data-hashurl");
                                    swaggerInfo = new SwaggerInfo();
                                    swaggerInfo.setTitle(title);
                                    swaggerInfo.setApiAmount(apiAmount);
                                    swaggerInfo.setOperate(operate);
                                    swaggerInfo.setMethod(method);
                                    swaggerInfo.setHashUrl(hashUrl);
                                    if (StrUtil.isNotEmpty(hashUrl)) {
                                        int slashIndex = hashUrl.lastIndexOf("/");
                                        int usingIndex = hashUrl.lastIndexOf("Using");
                                        String api = hashUrl.substring(slashIndex, usingIndex);
                                        swaggerInfo.setApi(api);
                                    }
//                                    System.out.println(swaggerInfo);
                                    swaggerInfoList.add(swaggerInfo);
                                }

                            }
                        }
                    }
                }
//                System.out.println(element.text());
            }
            //遍历中。。。。。。
        }

        return swaggerInfoList;
    }

    public static List<SwaggerInfo> getSwaggerInfoListByClassCode(Document document,
        String className) {
        List<SwaggerInfo> swaggerInfoList = new ArrayList<>();
        SwaggerInfo swaggerInfo;
        Elements elements = document.getElementsByClass(className);

        Iterator it = elements.iterator();
        int index = 0;
        while (it.hasNext()) {
            Element element = (Element) it.next();
            index++;
            if (index > 3) {
                if (element.childNodeSize() == 2) {
                    Element mainElement1 = element.child(0);
                    Element mainElement102 = mainElement1.child(1);
                    TextNode mainElement103 = (TextNode) mainElement102.childNode(0);
                    String title = mainElement103.text();
                    if (StrUtil.isNotEmpty(title)) {
                        title = title.trim();
                    }
                    Element mainElement1032 = (Element) mainElement102.childNode(1);
                    String apiAmount = mainElement1032.text();
                    if (StrUtil.isNotEmpty(apiAmount)) {
                        apiAmount = apiAmount.trim();
                    }
                    Element subElement = element.child(1);
                    int subSize = subElement.childNodeSize();
                    Element item;
                    if (subSize > 0) {
                        for (int i = 0; i < subSize; i++) {
                            item = subElement.child(i);
                            if(item.childNodeSize() == 1) {
                                if (item.childNode(0) instanceof Element) {
                                    Element mainElement2 = (Element) item.childNode(0);
                                    if (mainElement2.childNodeSize() == 0) {
                                        System.out.println("##### ERROR ####");
                                        continue; // TODO
                                    }
                                    Element mainElement202 = mainElement2.child(0);
                                    Element mainElement2030 = mainElement202.child(0);
                                    Element mainElement2031 = mainElement202.child(1);
                                    String method = mainElement2030.text();
                                    if (StrUtil.isNotEmpty(method)) {
                                        method = method.trim();
                                    }
                                    String operate = mainElement2031.text();
                                    if (StrUtil.isNotEmpty(operate)) {
                                        operate = operate.trim();
                                    }

                                    Attributes attributes = item.attributes();
                                    String hashUrl = attributes.get("data-hashurl");
                                    swaggerInfo = new SwaggerInfo();
                                    swaggerInfo.setTitle(title);
                                    swaggerInfo.setApiAmount(apiAmount);
                                    swaggerInfo.setOperate(operate);
                                    swaggerInfo.setMethod(method);
                                    swaggerInfo.setHashUrl(hashUrl);
                                    if (StrUtil.isNotEmpty(hashUrl)) {
                                        int slashIndex = hashUrl.lastIndexOf("/");
                                        int usingIndex = hashUrl.lastIndexOf("Using");
                                        String api = hashUrl.substring(slashIndex, usingIndex);
                                        swaggerInfo.setApi(api);
                                    }
//                                    System.out.println(swaggerInfo);
                                    swaggerInfoList.add(swaggerInfo);
                                }
                            }
                            System.out.println("##### item.childNodeSize() " + item.childNodeSize());
                            if(item.childNodeSize() == 2) {
                                if (item.childNode(1) instanceof Element) {
                                    Element mainElement2 = (Element) item.childNode(1);
                                    if (mainElement2.childNodeSize() == 0) {
                                        System.out.println("##### ERROR ####");
                                        continue; // TODO
                                    }
                                    Element mainElement202 = mainElement2.child(0);
                                    Element mainElement2030 = mainElement202.child(0);
                                    Element mainElement2031 = mainElement202.child(1);
                                    String method = mainElement2030.text();
                                    if (StrUtil.isNotEmpty(method)) {
                                        method = method.trim();
                                    }
                                    String operate = mainElement2031.text();
                                    if (StrUtil.isNotEmpty(operate)) {
                                        operate = operate.trim();
                                    }

                                    Attributes attributes = item.attributes();
                                    String hashUrl = attributes.get("data-hashurl");
                                    swaggerInfo = new SwaggerInfo();
                                    swaggerInfo.setTitle(title);
                                    swaggerInfo.setApiAmount(apiAmount);
                                    swaggerInfo.setOperate(operate);
                                    swaggerInfo.setMethod(method);
                                    swaggerInfo.setHashUrl(hashUrl);
                                    if (StrUtil.isNotEmpty(hashUrl)) {
                                        int slashIndex = hashUrl.lastIndexOf("/");
                                        int usingIndex = hashUrl.lastIndexOf("Using");
                                        String api = hashUrl.substring(slashIndex, usingIndex);
                                        swaggerInfo.setApi(api);
                                    }
//                                    System.out.println(swaggerInfo);
                                    swaggerInfoList.add(swaggerInfo);
                                }
                            }

                        }
                    }
                }
                System.out.println(element.text());
            }
            //遍历中。。。。。。
        }

        return swaggerInfoList;
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
     * 获取应用内购买信息 jsoup-Elements的遍历（使用Iterator迭代器）
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
     * 获取应用内购买信息 jsoup-Elements的遍历（使用Iterator迭代器）
     * https://blog.csdn.net/shengfn/article/details/53584339
     *
     * @param document
     * @param className
     * @return
     */
    public static List<String> getAppInPurchaseContentListByClass(Document document,
        String className) {
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
     * jsoup-Elements的遍历（使用Iterator迭代器） https://blog.csdn.net/shengfn/article/details/53584339
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
     * jsoup-Elements的遍历（使用Iterator迭代器） https://blog.csdn.net/shengfn/article/details/53584339
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
                                        if (index == -1) {
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
                                        if (index == -1) {
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
                                            int index = value.indexOf(
                                                Constants.SNAPSHOT_JPG_SUFFIX);
                                            if (index == -1) {
                                                index = value.indexOf(
                                                    Constants.SNAPSHOT_JPG_2_SUFFIX);
                                            }
                                            if (index != -1) {
                                                String beginPart = value.substring(0, index);
                                                int indexHttp = beginPart.lastIndexOf("http");
                                                String url = value.substring(indexHttp, index + 3);
                                                stringList.add(url);
                                            }
                                            index = value.indexOf(Constants.SNAPSHOT_PNG_SUFFIX);
                                            if (index == -1) {
                                                index = value.indexOf(
                                                    Constants.SNAPSHOT_PNG_2_SUFFIX);
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
     * jsoup-Elements的遍历（使用Iterator迭代器） https://blog.csdn.net/shengfn/article/details/53584339
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

@Data
class SwaggerInfo {

    private String title;
    private String apiAmount;
    private String operate;
    private String method;
    private String api;
    private String hashUrl;
}
