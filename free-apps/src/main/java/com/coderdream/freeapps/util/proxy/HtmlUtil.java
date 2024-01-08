package com.coderdream.freeapps.util.proxy;


import com.coderdream.freeapps.model.BbcPageInfoEntity;
import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.util.bbc.BbcConstants;
import com.coderdream.freeapps.util.download.v4.FileUtils;
import com.coderdream.freeapps.util.hutool.xml.RssParserUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.DownloadUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

//        List<DownloadInfoEntity> downloadHtmlInfo = HtmlUtil.getDownloadHtmlInfo();
//        System.out.println(downloadHtmlInfo.size());
////        System.out.println(downloadHtmlInfo.get(0));
//        for (DownloadInfoEntity downloadInfoEntity : downloadHtmlInfo) {
//            System.out.println(downloadInfoEntity); // 190131
//        }
//        System.out.println(downloadHtmlInfo.size());

//        HtmlUtil.getDownloadHtmlInfo("2022");

//        boolean test = true;
//        List<DownloadInfoEntity> downloadInfoEntityListTemp = new ArrayList<>();
//        if (test) {
//            DownloadInfoEntity infoEntity = new DownloadInfoEntity();
//            String folderName = "221103";
//            infoEntity.setFileUrl(
//                "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_2022/ep-" + folderName);
//            infoEntity.setPath("D:/14_LearnEnglish/6MinuteEnglish/2022/" + folderName + "/");
//            infoEntity.setFileName(folderName + ".html");
//            downloadInfoEntityListTemp = Arrays.asList(infoEntity);
//        } else {
//            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("2022");
//        }
//        Map<String, String> audioMap = RssParserUtil.getAudioMap();
//        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
//
//            String path = downloadInfoEntity.getPath();
//            String fileName = downloadInfoEntity.getFileName();
//
//            HtmlUtil.genScriptRawByHtml(downloadInfoEntity.getPath(), downloadInfoEntity.getFileName());
//
////            Map<String, DownloadInfoEntity> listBbcPageInfoListByHtml = HtmlUtil.getListBbcPageInfoListByHtml(path,
////                fileName, audioMap);
////            DownloadInfoEntity downloadInfoEntityPdf = listBbcPageInfoListByHtml.get("pdf");
////            System.out.println(downloadInfoEntityPdf);
////            DownloadUtil.downloadPageToFile(downloadInfoEntityPdf.getFileUrl(), downloadInfoEntityPdf.getPath(),
////                downloadInfoEntityPdf.getFileName(), true);
//
////            long startTime = System.currentTimeMillis();
//
////            System.out.println("####" + downloadInfoEntity);
////            Map<String, DownloadInfoEntity> listBbcPageInfoListByHtml = HtmlUtil.getListBbcPageInfoListByHtml(downloadInfoEntity.getPath(), downloadInfoEntity.getFileName());
////                DownloadInfoEntity downloadInfoEntityPdf = listBbcPageInfoListByHtml.get("pdf");
////                DownloadUtil.downloadFile(downloadInfoEntityPdf.getFileUrl(), downloadInfoEntityPdf.getPath(),
////                    downloadInfoEntityPdf.getFileName(), true);
////            DownloadInfoEntity downloadInfoEntityPdf = listBbcPageInfoListByHtml.get("pdf");
////            DownloadInfoEntity downloadInfoEntityMp3 = listBbcPageInfoListByHtml.get("mp3");
////            int index = 0;
////            if (downloadInfoEntityMp3 != null) {
////                long fileSize = FileUtils.getFileContentLength(
////                    downloadInfoEntity.getPath() + downloadInfoEntity.getFileName());
////                if (fileSize == 0) {
////                    index++;
////                    System.out.println(index + "\t mp3|\t" + downloadInfoEntityMp3.getFileName());
////                }
////                //                DownloadUtil.downloadFile(downloadInfoEntityMp3.getFileUrl(), downloadInfoEntityMp3.getPath(),
//////                    downloadInfoEntityMp3.getFileName(), true);
////            } else {
////                index++;
////                System.out.println(index + "\t pdf|\t" + downloadInfoEntityPdf.getFileName());
////            }
//
//            // http://downloads.bbc.co.uk/learningenglish/features/6min/220929_6min_%20english_inflation_download.mp3
//            // http://downloads.bbc.co.uk/learningenglish/features/6min/220929_6min_ english_inflation_download.mp3
//
////            Integer period2= new Random().nextInt(4000) + 300;
////            try {
////                Thread.sleep(period2);   // 休眠3秒
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
//
////                System.out.println("Thread" + getName() + "  " + downloadInfoEntityList.pop());
//            // long period = System.currentTimeMillis() - startTime;
//            //  System.out.println("耗时毫秒数：\t" + period);
//        }

//        System.out.println(listBbcPageInfoListByHtml.get("mp3"));

        m3();
    }

    public static void m3() {
        List<DownloadInfoEntity> downloadInfo = HtmlUtil.getDownloadInfo();
        for (DownloadInfoEntity downloadInfoEntity : downloadInfo) {
            System.out.println(downloadInfoEntity);
        }
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


    public static Map<String, DownloadInfoEntity> getListBbcPageInfoListByHtml(String path, String fileName,
        Map<String, String> audioMap) {
        Map<String, DownloadInfoEntity> result = new LinkedHashMap<>();
//        Set<String> hrefSet = new HashSet<>();
//        List<DownloadInfoEntity> result = new ArrayList<>();
        DownloadInfoEntity downloadInfoEntity;
        // 输出文本至屏幕
        String fileNameCn = path + File.separator + fileName;
        //
        List<String> stringList = CdFileUtils.readFileContent(fileNameCn);
        String textTopic = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        Document docDesc = Jsoup.parse(textTopic);
        String pdfClass = "download bbcle-download-extension-pdf";
        DownloadInfoEntity downloadInfoEntityPdf = extractedFileInfo(docDesc, pdfClass, path);
        result.put("pdf", downloadInfoEntityPdf);

        String mp3Class = "download bbcle-download-extension-mp3";
        DownloadInfoEntity downloadInfoEntityMp3 = extractedAudioFileInfo(docDesc, mp3Class, path,
            downloadInfoEntityPdf, audioMap);
        if (downloadInfoEntityMp3 != null) {
            result.put("mp3", downloadInfoEntityMp3);
        } else {
            System.out.println("############### pdf" + downloadInfoEntityPdf.getFileName());
        }

        return result;
    }

    private static DownloadInfoEntity extractedFileInfo(Document docDesc, String pdfClass, String path) {
        DownloadInfoEntity downloadInfoEntity = null;
        // 根据标签ID 获取标签内容
        Elements elementsPdf = docDesc.getElementsByClass(pdfClass);
        if (elementsPdf.size() == 1) {
            Element element = elementsPdf.get(0);

            Attributes attributes = element.attributes();

            downloadInfoEntity = new DownloadInfoEntity();
            // 文件地址
            String fileUrl = attributes.get("href").toString();
            // 文件存储文件夹
            downloadInfoEntity.setPath(path);
            downloadInfoEntity.setFileUrl(fileUrl);
            // 文件名称
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            fileName = fileName.replaceAll("_6min_english", "");
            fileName = fileName.replaceAll("_6_minute_english", "");
            fileName = fileName.replaceAll("_6_min_english", "");
            fileName = fileName.replaceAll("_6min", "");
            fileName = fileName.replaceAll("_download", "");
            fileName = fileName.replaceAll("_DOWNLOAD", "");
            downloadInfoEntity.setFileName(fileName);
        }

        return downloadInfoEntity;
    }

    private static DownloadInfoEntity extractedAudioFileInfo(Document docDesc, String pdfClass, String path,
        DownloadInfoEntity downloadInfoEntityPdf, Map<String, String> audioMap) {
        DownloadInfoEntity downloadInfoEntity = new DownloadInfoEntity();
        // 文件存储文件夹
        downloadInfoEntity.setPath(path);
        // 根据标签ID 获取标签内容
        Elements elementsPdf = docDesc.getElementsByClass(pdfClass);
        if (elementsPdf.size() == 1) {
            Element element = elementsPdf.get(0);

            Attributes attributes = element.attributes();

            // 文件地址
            String fileUrl = attributes.get("href").toString();
            downloadInfoEntity.setFileUrl(fileUrl);
            // 文件名称
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            fileName = fileName.replaceAll("_6min_english", "");
            fileName = fileName.replaceAll("_6_minute_english", "");
            fileName = fileName.replaceAll("_6_min_english", "");
            fileName = fileName.replaceAll("_6min", "");
            fileName = fileName.replaceAll("_download", "");
            fileName = fileName.replaceAll("_DOWNLOAD", "");
            downloadInfoEntity.setFileName(fileName);
        } else {
            String pdfFileName = downloadInfoEntityPdf.getFileName();
            String folderName = pdfFileName.substring(0, 6);
            String fileName = pdfFileName.substring(0, pdfFileName.lastIndexOf("."));
            String mp3FileName = fileName + ".mp3";

            downloadInfoEntity.setFileUrl(audioMap.get(folderName));
            downloadInfoEntity.setFileName(mp3FileName);

            System.out.println("############### MP3" + downloadInfoEntity);
        }

        return downloadInfoEntity;
    }

    /**
     * @param path
     * @param filename
     * @return
     */
    public static List<BbcPageInfoEntity> genScriptRawByHtml(String path, String filename) {

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

        // TODO widget-container widget-container-left
        Elements elementsContentTemp = docDesc.getElementsByClass(
            "widget-container widget-container-left"); // widget widget-richtext 6

        System.out.println(elementsContentTemp.text());
        List<String> strings = elementsContentTemp.eachText();
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println();

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

//        String ep = fileNameCn.substring(startIndex, endIndex);
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

        DownloadUtil.downloadFile(pictureUrls, paths, fileNames, proxyFlag);
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

    public static List<DownloadInfoEntity> getDownloadHtmlInfo(String extFileName, String... args) {
        List<DownloadInfoEntity> result = new ArrayList<>();
        List<DownloadInfoEntity> downloadInfoEntityList = getDownloadInfo(extFileName);
        if (args.length == 1) {
            String year = args[0];
            String shortYear = year.substring(2, 4);
            String tempYear;
            for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
                tempYear = downloadInfoEntity.getFileName().substring(0, 2);
//            System.out.println(shortYear + "\t|\t" + tempYear);
                if (shortYear.equals(tempYear)) {
                    result.add(downloadInfoEntity);
//                System.out.println(downloadInfoEntity);
                }
            }
        }

        if (args.length >= 2) {
            String year = args[0];
            int size = args.length - 1;
            String[] monthArr = new String[size];
            for (int i = 0; i < size; i++) {
//                monthArr[i] = args[i + 1];

                String month = args[ i + 1];
                String shortYear = year.substring(2, 4) + month;
                String tempYearMonth;
                for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
                    tempYearMonth = downloadInfoEntity.getFileName().substring(0, 4);
//            System.out.println(shortYear + "\t|\t" + tempYear);
                    if (shortYear.equals(tempYearMonth)) {
                        result.add(downloadInfoEntity);
//                System.out.println(downloadInfoEntity);
                    }
                }
            }

//            for (String month : monthArr) {
////                String month = args[1];
//                String shortYear = year.substring(2, 4) + month;
//                String tempYearMonth;
//                for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
//                    tempYearMonth = downloadInfoEntity.getFileName().substring(0, 4);
////            System.out.println(shortYear + "\t|\t" + tempYear);
//                    if (shortYear.equals(tempYearMonth)) {
//                        result.add(downloadInfoEntity);
////                System.out.println(downloadInfoEntity);
//                    }
//                }
//
//            }

//        if (args.length >= 3) {
//            String year = args[0];
//            String month1 = args[1];
//            String month2 = args[2];
//            String shortYear1 = year.substring(2, 4) + month1;
//            String shortYear2 = year.substring(2, 4) + month2;
//            String tempYearMonth;
//            for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
//                tempYearMonth = downloadInfoEntity.getFileName().substring(0, 4);
////            System.out.println(shortYear + "\t|\t" + tempYear);
//                if (shortYear1.equals(tempYearMonth) || shortYear2.equals(tempYearMonth)) {
//                    result.add(downloadInfoEntity);
////                System.out.println(downloadInfoEntity);
//                }
//            }
//        }
            }

            return result;
        }

//    public static List<DownloadInfoEntity> getDownloadHtmlInfo(String extFileName, String... args) {
//        List<DownloadInfoEntity> result = new ArrayList<>();
//        List<DownloadInfoEntity> downloadInfoEntityList = getDownloadInfo(extFileName);
//        if (args.length == 1) {
//            String year = args[0];
//            String shortYear = year.substring(2, 4);
//            String tempYear;
//            for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
//                tempYear = downloadInfoEntity.getFileName().substring(0, 2);
////            System.out.println(shortYear + "\t|\t" + tempYear);
//                if (shortYear.equals(tempYear)) {
//                    result.add(downloadInfoEntity);
////                System.out.println(downloadInfoEntity);
//                }
//            }
//        }
//
//        if (args.length == 2) {
//            String year = args[0];
//            String month = args[1];
//            String shortYear = year.substring(2, 4) + month;
//            String tempYearMonth;
//            for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
//                tempYearMonth = downloadInfoEntity.getFileName().substring(0, 4);
////            System.out.println(shortYear + "\t|\t" + tempYear);
//                if (shortYear.equals(tempYearMonth)) {
//                    result.add(downloadInfoEntity);
////                System.out.println(downloadInfoEntity);
//                }
//            }
//        }
//
//        if (args.length == 3) {
//            String year = args[0];
//            String month1 = args[1];
//            String month2 = args[2];
//            String shortYear1 = year.substring(2, 4) + month1;
//            String shortYear2 = year.substring(2, 4) + month2;
//            String tempYearMonth;
//            for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
//                tempYearMonth = downloadInfoEntity.getFileName().substring(0, 4);
////            System.out.println(shortYear + "\t|\t" + tempYear);
//                if (shortYear1.equals(tempYearMonth) || shortYear2.equals(tempYearMonth)) {
//                    result.add(downloadInfoEntity);
////                System.out.println(downloadInfoEntity);
//                }
//            }
//        }
//
//        return result;
//    }

        public static List<DownloadInfoEntity> getDownloadInfo (String extFileName){
            List<DownloadInfoEntity> result = new ArrayList<>();
            DownloadInfoEntity downloadInfoEntity;
            List<BbcPageInfoEntity> bbcPageInfoEntityListSrc = getListBbcPageInfoListByHtml();
            List<String> stringList = CdFileUtils.readFileContent(BbcConstants.ROOT_FOLDER_NAME + "done.txt");
            Set<String> doneDateSet = new HashSet<>();
//        doneDateSet.addAll(stringList);

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
                    fileNames[i] =
                        href.substring(6, 8) + href.substring(2, 4) + href.substring(0, 2) + "." + extFileName;
                } else {
                    paths[i] = BbcConstants.ROOT_FOLDER_NAME + "20" + href.substring(0, 2) + File.separator + href
                        + File.separator;
                    fileNames[i] = href + "." + extFileName;
                }
//            System.out.println(paths[i] + "\t|\t" + fileNames[i] + "\t|\t" + pictureUrls[i]);
                downloadInfoEntity.setPath(paths[i]);
                downloadInfoEntity.setFileName(fileNames[i]);
                result.add(downloadInfoEntity);
            }

            return result;
        }

        public static List<DownloadInfoEntity> getDownloadInfo () {
            List<DownloadInfoEntity> result = new ArrayList<>();
            DownloadInfoEntity downloadInfoEntity;
            List<BbcPageInfoEntity> bbcPageInfoEntityListSrc = getListBbcPageInfoListByHtml();
            List<String> addressStringList = CdFileUtils.readFileContent("d:/add.txt");
            List<String> nameStringList = CdFileUtils.readFileContent("d:/name.txt");

            int size = addressStringList.size();
            System.out.println(size);
//        String[] pictureUrls = new String[size];//{"https://ichef.bbci.co.uk/images/ic/1248xn/p0f3r8rj.jpg"};
            String[] paths = new String[size];//  BbcConstants.ROOT_FOLDER_NAME + "img//";
            BbcPageInfoEntity bbcPageInfoEntity;
            String href;
//        String dataPid;
            String address = "";
            String name = "";
            String extName = "";
            for (int i = 0; i < size; i++) {
                address = addressStringList.get(i);
                name = nameStringList.get(i);
                extName = address.substring(address.lastIndexOf("."));
                downloadInfoEntity = new DownloadInfoEntity();
                downloadInfoEntity.setFileUrl(address);
                downloadInfoEntity.setPath("D:\\0000\\");

                downloadInfoEntity.setFileName(name + "" + extName);
                result.add(downloadInfoEntity);
            }

            return result;
        }
    }
