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
public class HtmlToScriptRawUtil {

    public static void main(String[] args) {

        boolean test = true;
        List<DownloadInfoEntity> downloadInfoEntityListTemp = new ArrayList<>();
        if (test) {
            DownloadInfoEntity infoEntity = new DownloadInfoEntity();
            String folderName = "230209";
            infoEntity.setFileUrl(
                "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_2023/ep-" + folderName);
            infoEntity.setPath("D:/14_LearnEnglish/6MinuteEnglish/2023/" + folderName + "/");
            infoEntity.setFileName(folderName + ".html");
            downloadInfoEntityListTemp = Arrays.asList(infoEntity);
        } else {
            downloadInfoEntityListTemp = HtmlToScriptRawUtil.getDownloadHtmlInfo("2022");
        }

        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
            genScriptRawByHtml(downloadInfoEntity.getPath(), downloadInfoEntity.getFileName());
        }

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
        String FIND_WORD = "Sam";
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
//                                            String tagName = elementTempL2.tagName();
//                                            System.out.println(tagName);
//                                            tempText = elementTempL2.text();
//                                            System.out.println(k + "\t3" + tempText);
//                                            if ("sentient,".equals(tempText) || "sentient".equals(tempText)) {
//                                                System.out.println("##########-0");
//                                            }
//                                            if (tempText.lastIndexOf(FIND_WORD) != -1) {
//                                                System.out.println("############# - 1");
//                                            }
//                                            textList.add(tempText);

                                            List<Node> nodes = elementTempL2.childNodes();

                                            // TODO
                                            for (Node node : nodes) {
                                                node2 = node;
                                                if (node2 instanceof Element) {
                                                    // 获取子元素
                                                    Element e = (Element) node2;
                                                    textList.add(e.text());
                                                } else if (node2 instanceof TextNode) {
                                                    TextNode t = (TextNode) node2;
                                                    textList.add(t.text());
                                                }
                                            }
                                        } else {
                                            String tagName = elementTempL2.tagName();
                                            System.out.println(tagName);
                                            if ("br".equals(tagName)) {
//                                                textList.add("");
                                            }
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
                                    if (tempText.lastIndexOf(FIND_WORD) != -1) {
                                        System.out.println("############# - 2");
                                    }
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
                            if (tempText.lastIndexOf(FIND_WORD) != -1) {
                                System.out.println("############# - 2");
                            }
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
                        if (tempText.lastIndexOf(FIND_WORD) != -1) {
                            System.out.println("############# - 4");
                        }
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

    public static List<DownloadInfoEntity> getDownloadHtmlInfo(String... args) {
        List<DownloadInfoEntity> result = new ArrayList<>();
        List<DownloadInfoEntity> downloadInfoEntityList = HtmlUtil.getDownloadImgInfo();
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

        if (args.length == 2) {
            String year = args[0];
            String month = args[1];
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

        if (args.length == 3) {
            String year = args[0];
            String month1 = args[1];
            String month2 = args[2];
            String shortYear1 = year.substring(2, 4) + month1;
            String shortYear2 = year.substring(2, 4) + month2;
            String tempYearMonth;
            for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityList) {
                tempYearMonth = downloadInfoEntity.getFileName().substring(0, 4);
//            System.out.println(shortYear + "\t|\t" + tempYear);
                if (shortYear1.equals(tempYearMonth) || shortYear2.equals(tempYearMonth)) {
                    result.add(downloadInfoEntity);
//                System.out.println(downloadInfoEntity);
                }
            }
        }

        return result;
    }

}
