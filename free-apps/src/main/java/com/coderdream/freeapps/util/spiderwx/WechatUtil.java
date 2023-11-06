package com.coderdream.freeapps.util.spiderwx;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.util.other.CutImageUtils;
import com.coderdream.freeapps.util.other.DownloadUtil;
import com.coderdream.freeapps.util.other.JSoupSwaggerUtil;
import com.coderdream.freeapps.util.other.StringUtils;
import com.coderdream.freeapps.util.other.UrlUtils;
import com.coderdream.freeapps.util.qr.QRCodeEvents;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

public class WechatUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JSoupSwaggerUtil.class);

    public final static String DETAIL_MENU_CLASS = "detailMenu";

    public final static String MENU_LI_CLASS = "menuLi";
    public final static String DETAIL_CONTAINER = "nav nav-list";// "bycdao-left left";

    public final static String BASE_URL = "http://172.16.104.131:32355/doc.html";

    //    public final static String YESTERDAY_CLASS = "rich_media_content js_underline_content               autoTypeSetting24psection";
    public final static String YESTERDAY_CLASS = "rich_media_content";
//    public static final String URL =

    public final static String IMG_CONTENT_CLASS = "rich_media_wrp";
    public final static String PRIMARY_INNER_CLASS = "rich_media_area_primary_inner";

    public static void main(String[] args) throws IOException {
        String url = "https://mp.weixin.qq.com/s/f927osOgrZImWvWLzuOSpg";
        url = "https://mp.weixin.qq.com/s/W5LfEjr-LdfsjGTOdeQnag";
        url = "https://mp.weixin.qq.com/s/X4SW7tGOoUHNgRW8nkTKzQ"; //
//        url = "https://mp.weixin.qq.com/s/KhgIFi6wsQo_IqNO3XpV9Q"; // 20230108
        url = "https://mp.weixin.qq.com/s/gKsRlq6cmmwsH_hFEtRygg"; // 20221225
        url = "https://mp.weixin.qq.com/s/JgJxBeWh7fUCOaAqPZnqvQ"; // 20221219
        int size = 10;
//        url = "https://mp.weixin.qq.com/s?__biz=MzIwMDc4MjgyOA==&mid=2247564742&idx=1&sn=916d71656bf3bac0c4f059f0fad9763f&chksm=96f45b45a183d253c71fd358f81fc3c349c7c01d4325a36aa8275f9515f16ae1edd816c1a4dc&scene=21#wechat_redirect";

        // 2023-03-12 美区限免
//        url = "https://mp.weixin.qq.com/s?__biz=MzIwMDc4MjgyOA==&mid=2247566507&idx=1&sn=bc028a189de797cc364ff073eeecb358&chksm=96f46228a183eb3e59b2801042ec89ab8d5c8369655b2c0e660241d46bcf2c7210692c1283d6&scene=21#wechat_redirect";
//        url = "https://mp.weixin.qq.com/s?__biz=MzIwMDc4MjgyOA==&mid=2247565566&idx=2&sn=2cccb675dca19f0a2778eb9c9d028019&chksm=96f4667da183ef6b0aa7a8063f48dafa212a5531c1de39cd0cc9dd3510f19fc3b81c45384944&scene=21#wechat_redirect";

        // 2023-02-06 有【免费】
//        url = "https://mp.weixin.qq.com/s?__biz=MzIwMDc4MjgyOA==&mid=2247565120&idx=1&sn=e39821b779c0f9c7d40f6ed5bad6507f&chksm=96f459c3a183d0d590376061dc1a53791a7865f3061524c40a78bcaf65ccea7f2e33e0fa9596&scene=21#wechat_redirect";
        // 2023-01-27
//        url = "https://mp.weixin.qq.com/s?__biz=MzIwMDc4MjgyOA==&mid=2247564811&idx=1&sn=47f74d75f7a2e30c36623072cb919d47&chksm=96f45888a183d19e7b43f338a84f475b0fd2c70b612f289a3b8a5bb699869d0138f874857a15&scene=21#wechat_redirect";

        List<FreeHistory> freeHistoryList = new ArrayList<>();

//        String dateStr = getPublishTime(url);
//        System.out.println(dateStr);
        // 递归调用获取前一天，直到找不到为止
//        JSoupWechatUtil.getFreeHistoryList(url, freeHistoryList);
        // 获取当天
        WechatUtil.crawlerCode(url, freeHistoryList);

        if (!CollectionUtils.isEmpty(freeHistoryList)) {
            for (FreeHistory freeHistory : freeHistoryList) {
                System.out.println(freeHistory);
            }
        }
    }

    /**
     * 内容中获取文章发文时间
     *
     * @return "yyyy-MM-dd"格式的字符串
     */
    public static String getPublishTime(Document document) {
        String targetLine = null, timeStampStr = null;
        Elements scripts = document.select("script");
        for (Element script : scripts) {
            String html = script.html();
            // 需要获取的节点，当前的html字符串包含需要的时间戳
            if (html.contains("document.getElementById(\"publish_time\")")) {
                targetLine = html;
            }
        }
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(targetLine);
        while (matcher.find()) {
            timeStampStr = matcher.group(); //获取到的时间戳
        }
        Long timestamp = Long.parseLong(timeStampStr) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    public static void getFreeHistoryList(String url, List<FreeHistory> freeHistoryList) {
        do {
            url = WechatUtil.crawlerCode(url, freeHistoryList);
            if (StrUtil.isEmpty(url) && !CollectionUtils.isEmpty(freeHistoryList)) {
                if(StrUtil.isEmpty(freeHistoryList.get(freeHistoryList.size() - 1).getYesterdayUrl())) {
                    logger.error("#### 数据不合法：" + freeHistoryList.get(freeHistoryList.size() - 1));
                }
                url = freeHistoryList.get(freeHistoryList.size() - 1).getYesterdayUrl();
                logger.error("url: " + url + "\t" + new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(
                    freeHistoryList.get(freeHistoryList.size() - 1).getFreeDate()));
            }
        } while (StrUtil.isNotEmpty(url));
    }

    public static String crawlerCode(String url, List<FreeHistory> historyUrlList) {
        Document document = getDocument(url);
        return getFreeHistoryListByClassCode(document, YESTERDAY_CLASS, historyUrlList);
    }

    public static String getFreeHistoryListByClassCode(Document document, String className,
        List<FreeHistory> historyUrlList) {
        String href = "";
        FreeHistory freeHistory;
        /**
         * 限免日期
         */
        String freeDateStr = getPublishTime(document);
        Date freeDate = null;
        try {
            freeDate = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(freeDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Elements elements = document.getElementsByClass(className);

        if (elements.size() > 0) {
            Element elementLevel01 = elements.get(0);
            if (elementLevel01 != null && elementLevel01.childNodeSize() > 2) {
                Element elementLevel02 = elementLevel01.child(2);
                int childNodeSize = elementLevel02.childNodeSize();
                switch (childNodeSize) {
                    case 1:
                        // 处理上一页的url
                        href = getHrefSizeOne(href, elementLevel02);
                        break;
                    case 2:
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        // 处理上一页的url
                        href = getHref(href, elementLevel02);
                        break;
                    default:

                        break;

                }

                int size = elementLevel01.childNodeSize();
                int appIndex = 0;
                for (int i = 5; i < size; i++) {
                    Node childNode = elementLevel01.childNode(i);
                    if (childNode instanceof Element) {
                        Element childElement = (Element) elementLevel01.childNode(i);
                        String content;
                        // 应用Id
                        String appId = "";
                        // 应用名称
                        String title = "";
                        // 应用价格
                        String priceStr = "";
                        // 应用平台
                        String capabilityStr = "";
                        // 应用简介
                        String description = "";
                        // 二维码地址
                        String qrUrl = "";
                        // 应用地址
                        String appUrl = "";
                        // 限免应用序号
                        String freeAppNo = "";
                        // 限免应用序号
                        String index = "";
                        // 应用截图
                        String snapshot = "";
                        int priceBeginIndex;
                        int priceEndIndex;
                        int descBeginIndex;
                        int descEndIndex;
                        // 过滤行  嗨！
                        // macOS降价
                        if (childElement.tag() != null
                            && childElement.tag().getName().equals("section")
                            && -1 == childElement.text().lastIndexOf("部分内容来源于网络")
                            && -1 == childElement.text().lastIndexOf("美区限免")
                            && -1 == childElement.text().lastIndexOf("macOS降价")
                            && -1 == childElement.text().lastIndexOf("嗨！")) {

                            freeHistory = new FreeHistory();
                            freeHistory.setDataSource("wechat"); // 数据来源

//                            System.out.println(i + "####" + childElement.text());
                            freeHistory.setFreeDate(freeDate);
//                            logger.info("freeDate: " + freeDate);
                            appIndex++;
                            freeAppNo = freeDateStr + "_" + String.format("%02d", appIndex);
                            freeHistory.setAppIndex(freeAppNo);
                            content = childElement.text();
                            // 有免费，直接找免费
                            if (-1 != content.indexOf("【免费】")) {
                                priceBeginIndex = content.indexOf("【免费】");
                                priceEndIndex = content.indexOf("【免费】");
                                // 直接置为0
                                priceStr = "0";
                            } else {
                                priceBeginIndex = content.indexOf("【¥");
                                priceEndIndex = content.indexOf("➜");
                                try {
                                    if (priceBeginIndex != -1 && priceEndIndex != -1) {
                                        priceStr = content.substring(priceBeginIndex + 2, priceEndIndex);
                                    }
                                } catch (Exception e) {
                                    logger.error(priceBeginIndex + "\t" + priceEndIndex);
                                    logger.error("error: " + content);
                                    e.printStackTrace();
                                }
                            }

                            if (priceBeginIndex != -1) {
                                title = content.substring(0, priceBeginIndex);
                            }
                            descBeginIndex = content.indexOf("简介：");
                            descEndIndex = content.indexOf("▼长按");
                            if (priceEndIndex != -1 && descBeginIndex != -1) {
                                capabilityStr = content.substring(priceEndIndex + 4, descBeginIndex);
                            }
                            if (descBeginIndex != -1 && descEndIndex != -1) {
                                description = content.substring(descBeginIndex + 3, descEndIndex);
                            }
                            freeHistory.setTitle(title.trim());
                            freeHistory.setPriceStr(priceStr);
                            freeHistory.setCapabilityStr(capabilityStr.trim());
                            freeHistory.setDescription(description.trim());
//                            freeHistory.setHref(href);
                            freeHistory.setYesterdayUrl(href);
                            Elements imgElements = childElement.getElementsByClass("rich_pages"); //  wxw-img
//                            System.out.println(imgElements);
                            if (imgElements != null && imgElements.size() > 0) {
                                int imgSize = imgElements.size();
                                switch (imgSize) {
                                    case 1:
                                        Element imgElement = imgElements.get(0);
                                        if (imgElement.attributes() != null) {
//                                            System.out.println(imgElement.attributes().get("data-src"));
                                            snapshot = imgElement.attributes().get("data-src");
                                        }
                                        break;
                                    case 2:
                                        Element imgElement2 = imgElements.get(0);
                                        if (imgElement2.attributes() != null) {
                                            // System.out.println(imgElement2.attributes().get("data-src"));
                                            snapshot = imgElement2.attributes().get("data-src");
                                        }
                                        Element imgElement3 = imgElements.get(1);
                                        if (imgElement3.attributes() != null) {
                                            //  System.out.println(imgElement3.attributes().get("data-src"));
                                            snapshot = imgElement3.attributes().get("data-src");
                                        }
                                        break;
                                }
                            }
                            freeHistory.setSnapshot(snapshot);
//                            qrUrl = "";
                            if (StrUtil.isNotEmpty(snapshot) && StrUtil.isNotEmpty(freeDateStr)
                                && StrUtil.isNotEmpty(freeAppNo)) {
                                qrUrl = getAppUrl(freeHistory.getSnapshot(), freeDateStr, freeAppNo);
                            }
                            freeHistory.setQrUrl(qrUrl);
                            if (StrUtil.isNotEmpty(qrUrl)) {
                                if (qrUrl.lastIndexOf("https://apps.apple.com") != -1) {
                                    appUrl = qrUrl;
                                } else {
                                    appUrl = UrlUtils.tranAppUrl(qrUrl);
                                }
                                freeHistory.setAppUrl(appUrl);
                                if (StrUtil.isNotEmpty(appUrl)) {
                                    appId = StringUtils.parseAppId(appUrl);
                                    if (StrUtil.isEmpty(appId)) {
                                        logger.error("### 应用ID非法！qrUrl：" + qrUrl);
                                        continue;
                                    }
                                } else {
                                    logger.error("### qrUrl非法：" + qrUrl);
                                }

                                freeHistory.setAppId(appId);
                                historyUrlList.add(freeHistory);
                            }
//                            System.out.println(freeHistory);
                        }
                    }
                }
            }
        }

        return href;
    }

    private static String getHrefSizeOne(String href, Element elementLevel02) {
        if (elementLevel02 != null && elementLevel02.childNodeSize() == 1) {
            Element elementLevel03 = elementLevel02.child(0);
            if (elementLevel03 != null && elementLevel03.childNodeSize() > 0) {
                Element elementLevel04 = elementLevel03.child(0);
                if (elementLevel04 != null && elementLevel04.childNodeSize() > 0) {
                    Element elementLevel05 = elementLevel04.child(0);
                    if (elementLevel05 != null && elementLevel05.childNodeSize() > 0) {
                        Element elementLevel06 = elementLevel05.child(0);
                        if (elementLevel06 != null && elementLevel06.childNodeSize() > 0) {
//                                    System.out.println(elementLevel05.wholeText());
//                                    System.out.println(elementLevel06.wholeText());
                            Element elementLevel07 = elementLevel06.child(0);
                            if (elementLevel07 != null && elementLevel07.childNodeSize() > 0) {
//                                    System.out.println(elementLevel05.wholeText());
//                                    System.out.println(elementLevel06.wholeText());
//                                Element elementLevel07 = elementLevel06.child(0);
                                Attributes attributes = elementLevel07.attributes();
                                href = attributes.get("href");
//                                    logger.info("href: " + href);
                                if (StrUtil.isNotEmpty(href)) {

                                }
//                                    if(elementLevel07 != null && elementLevel07.childNodeSize() > 0) {
//                                        Element elementLevel08 = elementLevel07.child(0);
//                                    }
                            }
//                                    if(elementLevel07 != null && elementLevel07.childNodeSize() > 0) {
//                                        Element elementLevel08 = elementLevel07.child(0);
//                                    }
                        }
                    }

                }
            }
        }
        return href;
    }

    private static String getHref(String href, Element elementLevel02) {
        if (elementLevel02 != null && elementLevel02.childNodeSize() > 2) {
            Element elementLevel03 = elementLevel02.child(0);
            if (elementLevel03 != null && elementLevel03.childNodeSize() > 0) {
                Element elementLevel04 = elementLevel03.child(0);
                if (elementLevel04 != null && elementLevel04.childNodeSize() > 0) {
                    Element elementLevel05 = elementLevel04.child(0);
                    if (elementLevel05 != null && elementLevel05.childNodeSize() > 0) {
                        Element elementLevel06 = elementLevel05.child(0);
                        if (elementLevel06 != null && elementLevel06.childNodeSize() > 0) {
//                                    System.out.println(elementLevel05.wholeText());
//                                    System.out.println(elementLevel06.wholeText());
//                            Element elementLevel07 = elementLevel06.child(0);
                            Attributes attributes = elementLevel06.attributes();
                            href = attributes.get("href");
//                                    logger.info("href: " + href);
                            if (StrUtil.isNotEmpty(href)) {

                            }
//                                    if(elementLevel07 != null && elementLevel07.childNodeSize() > 0) {
//                                        Element elementLevel08 = elementLevel07.child(0);
//                                    }
                        }
                    }

                }
            }
        }
        return href;
    }

    public static String getAppUrl(String snapshot, String freeDate, String appIndex) {
        String path = freeDate;
        String picName = appIndex + ".png";
        String picQrName = appIndex + "_qr.png";
//        if (picName.equals("2022-12-25_05.png")) {
//            logger.error("#####");
//        }

        File picFile = new File(freeDate + "/" + picName);
        if (!picFile.exists()) {
            // 下载应用图片
            DownloadUtil.downloadPicture(snapshot, path, picName);
        }
        // 截取QR图片
        File srcImageFile = new File(path + "/" + picName);
        File destImageFile = new File(path + "/" + picQrName);
        CutImageUtils.cutForQr(srcImageFile, destImageFile);
        // 识别QR图片
        String qrUrl = QRCodeEvents.parseQRCode(path + "/" + picQrName);
        if (StrUtil.isEmpty(qrUrl)) {
            CutImageUtils.cutForQrV2(srcImageFile, destImageFile);
            // 识别QR图片
            qrUrl = QRCodeEvents.parseQRCode(path + "/" + picQrName);
        }

        System.out.println(qrUrl);
        return qrUrl;
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
     * 获取应用内购买信息 jsoup-Elements的遍历（使用Iterator迭代器） https://blog.csdn.net/shengfn/article/details/53584339
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
     * 获取应用内购买信息 jsoup-Elements的遍历（使用Iterator迭代器） https://blog.csdn.net/shengfn/article/details/53584339
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

