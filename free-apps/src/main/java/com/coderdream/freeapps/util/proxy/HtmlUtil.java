package com.coderdream.freeapps.util.proxy;


import com.coderdream.freeapps.model.BbcPageInfoEntity;
import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.util.bbc.BbcConstants;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.DownloadUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * <a href="/learningenglish/english/features/6-minute-english_2023/ep-230907"><img
 * src="https://ichef.bbci.co.uk/images/ic/624xn/p0gbt487.jpg" id="1_p0gbt487" data-type="image" data-pid="p0gbt487"
 * data-title="" data-description="" srcset="https://ichef.bbci.co.uk/images/ic/624xn/p0gbt487.jpg
 * 624w,https://ichef.bbci.co.uk/images/ic/1248xn/p0gbt487.jpg 1248w" width="624" alt=""/></a>
 *
 * @author CoderDream
 */
public class HtmlUtil {

    public static void main(String[] args) {
//        List<BbcPageInfoEntity> bbcPageInfoEntityListSrc = getListBbcPageInfoListByHtml();


//        List<BbcPageInfoEntity> bbcPageInfoEntityList = getListBbcPageInfoListByHtml();
//        for (BbcPageInfoEntity bbcPageInfoEntity : bbcPageInfoEntityList) {
//            System.out.println(bbcPageInfoEntity);
//        }

//        List<BbcPageInfoEntity> listBbcPageInfoList = HtmlUtil.getListBbcPageInfoListByNet();
//        for (BbcPageInfoEntity bbcPageInfoEntity : listBbcPageInfoList) {
//            System.out.println(bbcPageInfoEntity);
//        }

//        List<BbcPageInfoEntity> bbcPageInfoEntityList = HtmlUtil.getBbcPageInfoDetailByHtml();

//        HtmlUtil.downloadImg(bbcPageInfoEntityList);

//        List<DownloadInfoEntity> downloadInfoEntityList = HtmlUtil.getDownloadImgInfo();
//        String path = "D:\\14_LearnEnglish\\6MinuteEnglish\\2023\\230309\\";
//        String filename = "230309.html";
//        List<BbcPageInfoEntity> bbcPageInfoDetailByHtml = getBbcPageInfoDetailByHtml(path, filename);

        List<DownloadInfoEntity> downloadHtmlInfo = HtmlUtil.getDownloadHtmlInfo();
        System.out.println(downloadHtmlInfo.size());
//        System.out.println(downloadHtmlInfo.get(0));
        for (DownloadInfoEntity downloadInfoEntity : downloadHtmlInfo) {
            System.out.println(downloadInfoEntity); // 190131
        }
        System.out.println(downloadHtmlInfo.size());
    }

    public static List<BbcPageInfoEntity> getListBbcPageInfoListByHtml() {
        Set<String> hrefSet = new HashSet<>();
        List<BbcPageInfoEntity> result = new ArrayList<>();
        BbcPageInfoEntity bbcPageInfoEntity;
        // 输出文本至屏幕
        String fileNameCn = BbcConstants.ROOT_FOLDER_NAME + "6-minute-english.html";
        //
        List<String> stringList = CdFileUtils.readFileContent(fileNameCn);
        String textTopic = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        Document docDesc = Jsoup.parse(textTopic);
        // 根据标签ID 获取标签内容
        Elements elements = docDesc.getElementsByClass("course-content-item active");
        int size = elements.size();
        String href;
//        String ep;
        for (int i = 0; i < size; i++) {
            Element element = elements.get(i);
            Elements children = element.children();
            if (children.size() > 1) {
                Element elementImg = children.get(1);
//                System.out.println(elementImg.text());

                Elements childrenImg = elementImg.children();
                if (childrenImg.size() == 1) {
                    bbcPageInfoEntity = new BbcPageInfoEntity();
                    Element elementImgA = childrenImg.get(0);
                    Attributes attributes = elementImgA.attributes();
                    href = attributes.get("href");
                    hrefSet.add(href);
//                    System.out.println(href);//

//                    System.out.println(attributes.size());
//                    href = attributes.toString();
//                    ep = href.substring(href.lastIndexOf("-") + 1);

//                    System.out.println("href:\t" + href);
//                    System.out.println("ep:\t" + ep);

                    Elements childrenImgB = elementImgA.children();

                    Attributes attributes1 = childrenImgB.get(0).attributes();
                    String src = attributes1.get("src");
//                    System.out.println(src);//data-pid
                    String data_pid = attributes1.get("data-pid");
//                    System.out.println(data_pid);//

                    bbcPageInfoEntity.setHref(href.trim());
                    bbcPageInfoEntity.setDataPid(data_pid);
                    result.add(bbcPageInfoEntity);
                }
            }
        }

        System.out.println(result.size() + "\t|\t" + hrefSet.size());

        return result;
    }


    public static List<BbcPageInfoEntity> getBbcPageInfoDetailByHtml(String path, String filename) {

        List<String> textList = new ArrayList<>();

        List<BbcPageInfoEntity> result = new ArrayList<>();
        BbcPageInfoEntity bbcPageInfoEntity;
        //输出文本至屏幕
//        String fileNameCn = BbcConstants.ROOT_FOLDER_NAME + "ep-231102.html";
        //
        String fileNameCn = path + filename;
        List<String> stringList = CdFileUtils.readFileContent(fileNameCn);
        String textTopic = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        Document docDesc = Jsoup.parse(textTopic);

        // 根据标签ID 获取标签内容
        Elements elementsTitle = docDesc.getElementsByClass(
            "widget widget-heading clear-left"); // widget widget-richtext 6
        if (elementsTitle.size() == 2) {
            Element elementTitle = elementsTitle.get(1);
            String text = elementTitle.text();
            System.out.println(text);
            textList.add(text);
        }

        // 根据标签ID 获取标签内容
        Elements elements = docDesc.getElementsByClass("text"); // widget widget-richtext 6

//        Elements elements = docDesc.getElementsByClass("widget widget-richtext 6"); // widget widget-richtext 6

        int size = elements.size();
        String tempText;

        Element elementTempL0;
        Element elementTempL1;
        Element elementTempL2;
        TextNode textNodeL0;
        TextNode textNodeL1;
        TextNode textNodeL2;
        Node node0;
        Node node1;
        Node node2;
        Node node3;
        // 遍历所有的text样式名的元素
        for (int i = 0; i < size; i++) {
//            System.out.println(elements.get(i).childNodeSize());
            elementTempL0 = elements.get(i);
            // 如果子元素大于1才处理
            if (elementTempL0.childNodeSize() > 1) {

                // 获取所有子元素
//                Elements childrenL1 = elementTempL0.children();
                // 遍历所有子元素
                for (int j = 0; j < elementTempL0.childNodeSize(); j++) {
                    node1 = elementTempL0.childNode(j);

                    if (node1 instanceof Element) {
                        // 获取子元素
                        elementTempL1 = (Element) node1;
                        // 如果子元素的子元素大于1
                        if (elementTempL1.childNodeSize() > 1) {

//                        Elements childrenL2 = elementTempL1.children();
                            for (int k = 0; k < elementTempL1.childNodeSize(); k++) {
                                node2 = elementTempL1.childNode(k);

                                if (node2 instanceof Element) {
                                    // 获取子元素
                                    elementTempL2 = (Element) node2;
                                    // 如果子元素的子元素大于1
                                    if (elementTempL2.children().size() > 1) {
                                        Elements childrenL3 = elementTempL2.children();
                                        for (int l = 0; l < childrenL3.size(); l++) {
                                            tempText = childrenL3.get(l).text();
                                            System.out.println(l + "\t4" + tempText);
                                            textList.add(tempText);
                                        }
                                    }
                                    // 否则直接输出
                                    else {
                                        // tag = br
                                        if (elementTempL2.childNodeSize() != 0) {
                                            String tagName = elementTempL2.tagName();
                                            System.out.println(tagName);
                                            tempText = elementTempL2.text();
                                            System.out.println(k + "\t3" + tempText);
                                            if ("sentient,".equals(tempText) || "sentient".equals(tempText)) {
                                                System.out.println("##########-0");
                                            }

                                            textList.add(tempText);
                                        }
                                    }
                                } else if (node2 instanceof TextNode) {
                                    // 获取子元素
                                    textNodeL1 = (TextNode) node2;
                                    tempText = textNodeL1.text(); // umami
                                    if ("sentient,".equals(tempText)) {
                                        System.out.println("##########-2");
                                    }
                                    System.out.println(k + "\t3" + tempText);
                                    textList.add(tempText);
                                }
                            }
                        }
                        // 否则直接输出
                        else {
                            tempText = elementTempL1.text();
                            if ("Where did Inky the octopus go when he broke out of his tank at the New Zealand National Aquarium in 2016?".equals(
                                tempText)) {
                                System.out.println("################");
                            }
                            System.out.println(j + "\t2" + tempText);
                            textList.add(tempText);
                        }
                    } else if (node1 instanceof TextNode) {
                        // 获取子元素
                        textNodeL1 = (TextNode) node1;
                        tempText = textNodeL1.text(); // umami
                        if ("sentient,".equals(tempText)) {
                            System.out.println("##########-3");
                        }
                        System.out.println(j + "\t3" + tempText);
                        textList.add(tempText);
                    }
                }
            }
        }

        Integer startIndex = fileNameCn.lastIndexOf(File.separator);
        Integer endIndex = fileNameCn.lastIndexOf(".");

        String ep = fileNameCn.substring(startIndex, endIndex);
        String name = path + "script_raw.txt";
        // 写中文翻译文本
//            CdFileUtils.writeToFile(fileNameCn, contentList);
        CdFileUtils.writeToFile(name, textList);

        return result;
    }

    public static List<BbcPageInfoEntity> getListBbcPageInfoListByNet() {
        List<BbcPageInfoEntity> result = new ArrayList<>();
        BbcPageInfoEntity bbcPageInfoEntity;
//        //输出文本至屏幕
//        String fileNameCn = BbcConstants.ROOT_FOLDER_NAME + "6-minute-english.html";
//        //
//        List<String> stringList = CdFileUtils.readFileContent(fileNameCn);
//        String textTopic = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));

        String pageUrl = "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english";
        String textTopic = NetProxy.getPageInfo(pageUrl);

        Document docDesc = Jsoup.parse(textTopic);
        // 根据标签ID 获取标签内容
        Elements elements = docDesc.getElementsByClass("course-content-item active");
        int size = elements.size();
        String href;
        String ep;
        for (int i = 0; i < size; i++) {
            Element element = elements.get(i);
            Elements children = element.children();
            if (children.size() > 1) {
                Element elementImg = children.get(1);
                System.out.println(elementImg.text());

                Elements childrenImg = elementImg.children();
                if (childrenImg.size() == 1) {
                    bbcPageInfoEntity = new BbcPageInfoEntity();
                    Element elementImgA = childrenImg.get(0);
                    Attributes attributes = elementImgA.attributes();
                    href = attributes.get("href");
//                    System.out.println(href);//

//                    System.out.println(attributes.size());
//                    href = attributes.toString();
//                    ep = href.substring(href.lastIndexOf("-") + 1);

                    System.out.println("href:\t" + href);
//                    System.out.println("ep:\t" + ep);

                    Elements childrenImgB = elementImgA.children();

                    Attributes attributes1 = childrenImgB.get(0).attributes();
                    String src = attributes1.get("src");
                    System.out.println(src);//data-pid
                    String data_pid = attributes1.get("data-pid");
                    System.out.println(data_pid);//

                    bbcPageInfoEntity.setHref(href.trim());
                    bbcPageInfoEntity.setDataPid(data_pid);
                    result.add(bbcPageInfoEntity);
                }
            }
        }

        return result;
    }

    public static void getDownloadImgInfo(List<BbcPageInfoEntity> bbcPageInfoEntityListSrc) {

        List<String> stringList = CdFileUtils.readFileContent(BbcConstants.ROOT_FOLDER_NAME + "done.txt");
        Set<String> doneDateSet = new HashSet<>();
        doneDateSet.addAll(stringList);

        List<BbcPageInfoEntity> bbcPageInfoEntityList = new ArrayList<>();
        for (BbcPageInfoEntity bbcPageInfoEntity : bbcPageInfoEntityListSrc) {
//            bbcPageInfoEntity = bbcPageInfoEntityList.get(i);
            String href = bbcPageInfoEntity.getHref();
            href = href.substring(href.lastIndexOf("-") + 1);
            if (-1 != href.lastIndexOf("150122")) {
                href = "150122";
            }

            if (!doneDateSet.contains(href)) {
                bbcPageInfoEntityList.add(bbcPageInfoEntity);
            }
        }

        int size = bbcPageInfoEntityList.size();
        String[] pictureUrls = new String[size];//{"https://ichef.bbci.co.uk/images/ic/1248xn/p0f3r8rj.jpg"};
        String[] paths = new String[size];//  BbcConstants.ROOT_FOLDER_NAME + "img//";
        String[] fileNames = new String[size];// new String[]{"temp.jpg"};
        BbcPageInfoEntity bbcPageInfoEntity;
        String href;
        String dataPid;
        for (int i = 0; i < size; i++) {
            bbcPageInfoEntity = bbcPageInfoEntityList.get(i);
            href = bbcPageInfoEntity.getHref();
            href = href.substring(href.lastIndexOf("-") + 1);
            if (-1 != href.lastIndexOf("150122")) {
                href = "150122";
            }

//            System.out.println(href);
            dataPid = bbcPageInfoEntity.getDataPid();
            pictureUrls[i] = BbcConstants.IMG_1248XN + dataPid + ".jpg";

            if (href.length() == 8) {
                paths[i] =
                    BbcConstants.ROOT_FOLDER_NAME + "2014" + File.separator + href.substring(6, 8) + href.substring(
                        2, 4) + href.substring(0, 2) + File.separator;
                fileNames[i] = href.substring(6, 8) + href.substring(2, 4) + href.substring(0, 2) + ".jpg";
            } else {
                paths[i] = BbcConstants.ROOT_FOLDER_NAME + "20" + href.substring(0, 2) + File.separator + href
                    + File.separator;
                fileNames[i] = href + ".jpg";
            }
//            System.out.println(paths[i] + "\t|\t" + fileNames[i] + "\t|\t" + pictureUrls[i]);
        }

        Boolean proxyFlag = true;

        DownloadUtil.downloadPicture(pictureUrls, paths, fileNames, proxyFlag);
    }

    public static List<DownloadInfoEntity> getDownloadImgInfo() {
        List<DownloadInfoEntity> result = new ArrayList<>();
        DownloadInfoEntity downloadInfoEntity;
        List<BbcPageInfoEntity> bbcPageInfoEntityListSrc = getListBbcPageInfoListByHtml();
        List<String> stringList = CdFileUtils.readFileContent(BbcConstants.ROOT_FOLDER_NAME + "done.txt");
        Set<String> doneDateSet = new HashSet<>();
        doneDateSet.addAll(stringList);

        List<BbcPageInfoEntity> bbcPageInfoEntityList = new ArrayList<>();
        for (BbcPageInfoEntity bbcPageInfoEntity : bbcPageInfoEntityListSrc) {
//            bbcPageInfoEntity = bbcPageInfoEntityList.get(i);
            String href = bbcPageInfoEntity.getHref();
            href = href.substring(href.lastIndexOf("-") + 1);
            if (-1 != href.lastIndexOf("150122")) {
                href = "150122";
            }

            if (!doneDateSet.contains(href)) {
                bbcPageInfoEntityList.add(bbcPageInfoEntity);
            }
        }

        int size = bbcPageInfoEntityList.size();
        String[] pictureUrls = new String[size];//{"https://ichef.bbci.co.uk/images/ic/1248xn/p0f3r8rj.jpg"};
        String[] paths = new String[size];//  BbcConstants.ROOT_FOLDER_NAME + "img//";
        String[] fileNames = new String[size];// new String[]{"temp.jpg"};
        BbcPageInfoEntity bbcPageInfoEntity;
        String href;
        String dataPid;
        for (int i = 0; i < size; i++) {
            bbcPageInfoEntity = bbcPageInfoEntityList.get(i);
            href = bbcPageInfoEntity.getHref();
            href = href.substring(href.lastIndexOf("-") + 1);
            if (-1 != href.lastIndexOf("150122")) {
                href = "150122";
            }

//            System.out.println(href);
            dataPid = bbcPageInfoEntity.getDataPid();
            pictureUrls[i] = BbcConstants.IMG_1248XN + dataPid + ".jpg";

            if (href.length() == 8) {
                paths[i] =
                    BbcConstants.ROOT_FOLDER_NAME + "2014" + File.separator + href.substring(6, 8) + href.substring(
                        2, 4) + href.substring(0, 2) + File.separator;
                fileNames[i] = href.substring(6, 8) + href.substring(2, 4) + href.substring(0, 2) + ".jpg";
            } else {
                paths[i] = BbcConstants.ROOT_FOLDER_NAME + "20" + href.substring(0, 2) + File.separator + href
                    + File.separator;
                fileNames[i] = href + ".jpg";
            }
//            System.out.println(paths[i] + "\t|\t" + fileNames[i] + "\t|\t" + pictureUrls[i]);

            downloadInfoEntity = new DownloadInfoEntity();
            downloadInfoEntity.setFileUrl(pictureUrls[i]);
            downloadInfoEntity.setPath(paths[i]);
            downloadInfoEntity.setFileName(fileNames[i]);
            result.add(downloadInfoEntity);
        }

        return result;
    }

    public static List<DownloadInfoEntity> getDownloadHtmlInfo() {
        List<DownloadInfoEntity> result = new ArrayList<>();
        DownloadInfoEntity downloadInfoEntity;
        List<BbcPageInfoEntity> bbcPageInfoEntityListSrc = getListBbcPageInfoListByHtml();
        List<String> stringList = CdFileUtils.readFileContent(BbcConstants.ROOT_FOLDER_NAME + "done.txt");
        Set<String> doneDateSet = new HashSet<>();
        doneDateSet.addAll(stringList);

        List<BbcPageInfoEntity> bbcPageInfoEntityList = new ArrayList<>();
        for (BbcPageInfoEntity bbcPageInfoEntity : bbcPageInfoEntityListSrc) {
//            bbcPageInfoEntity = bbcPageInfoEntityList.get(i);
            String href = bbcPageInfoEntity.getHref();
            href = href.substring(href.lastIndexOf("-") + 1);
            if (-1 != href.lastIndexOf("150122")) {
                href = "150122";
            }

            if (!doneDateSet.contains(href)) {
                bbcPageInfoEntityList.add(bbcPageInfoEntity);
            }
        }

        int size = bbcPageInfoEntityList.size();
        System.out.println(size);
//        String[] pictureUrls = new String[size];//{"https://ichef.bbci.co.uk/images/ic/1248xn/p0f3r8rj.jpg"};
        String[] paths = new String[size];//  BbcConstants.ROOT_FOLDER_NAME + "img//";
        String[] fileNames = new String[size];// new String[]{"temp.jpg"};
        BbcPageInfoEntity bbcPageInfoEntity;
        String href;
//        String dataPid;
        for (int i = 0; i < size; i++) {

            bbcPageInfoEntity = bbcPageInfoEntityList.get(i);
            href = bbcPageInfoEntity.getHref();
            downloadInfoEntity = new DownloadInfoEntity();
            downloadInfoEntity.setFileUrl(BbcConstants.SINGLE_PAGE_URL + href);
            href = href.substring(href.lastIndexOf("-") + 1);
            if (-1 != href.lastIndexOf("150122")) {
                href = "150122";
            }

//            System.out.println(href);
//            dataPid = bbcPageInfoEntity.getDataPid();
//            pictureUrls[i] = BbcConstants.IMG_1248XN + dataPid + ".jpg";

            if (href.length() == 8) {
                paths[i] =
                    BbcConstants.ROOT_FOLDER_NAME + "2014" + File.separator + href.substring(6, 8) + href.substring(
                        2, 4) + href.substring(0, 2) + File.separator;
                fileNames[i] = href.substring(6, 8) + href.substring(2, 4) + href.substring(0, 2) + ".html";
            } else {
                paths[i] = BbcConstants.ROOT_FOLDER_NAME + "20" + href.substring(0, 2) + File.separator + href
                    + File.separator;
                fileNames[i] = href + ".html";
            }
//            System.out.println(paths[i] + "\t|\t" + fileNames[i] + "\t|\t" + pictureUrls[i]);
            downloadInfoEntity.setPath(paths[i]);
            downloadInfoEntity.setFileName(fileNames[i]);
            result.add(downloadInfoEntity);
        }

        return result;
    }
}
