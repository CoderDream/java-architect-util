package com.coderdream.freeapps.util.hutool.xml;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.model.BbcAudioEntity;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @author CoderDream
 */
public class RssParserUtil {

    public static void main(String[] args) {
        List<BbcAudioEntity> analysis = RssParserUtil.analysis();
        for (BbcAudioEntity bbcAudioEntity : analysis) {
            // System.out.println(bbcAudioEntity);
        }

        Map<String, String> audioMap = RssParserUtil.getAudioMap();
    }

    /**
     * 获取音频键值对
     * @return
     */
    public static Map<String, String> getAudioMap() {
        List<BbcAudioEntity> analysis = RssParserUtil.analysis();
        Map<String, String> audioMap = null;
        if(CollectionUtil.isNotEmpty(analysis)) {
            audioMap = new HashMap<>(analysis.size());
            for (BbcAudioEntity bbcAudioEntity : analysis) {
                // System.out.println(bbcAudioEntity);
                audioMap.put(bbcAudioEntity.getPubDate(), bbcAudioEntity.getUrl());
            }
        }
        return audioMap;
    }

    /**
     * @return
     */
    public static List<BbcAudioEntity> analysis() {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String fileName = folderPath + File.separator + "rss.xml";
        // 把要解析的xml变成file文件
        File file = new File(fileName);

        // 获取解析器对象
        SAXReader reader = new SAXReader();
        // 把文件解析成document树
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 获取根节点
        Element studentRoot = document.getRootElement();

//        studentRoot.g

        // 获取根节点中所有节点
        List<Element> elements = studentRoot.elements();

        // System.out.println(elements.size());

        List<BbcAudioEntity> bbcAudioEntityList = new ArrayList<>();
        BbcAudioEntity bbcAudioEntity = new BbcAudioEntity();
        int itemIndex = 0;
        if (CollectionUtil.isNotEmpty(elements)) {
            for (Element element : elements) {
                if (StrUtil.isNotEmpty(element.getName()) && StrUtil.isNotEmpty(element.getText())) {
                    // System.out.println(                        element.getName() + "--ele2_1->" + element.getText());
                }
                List<Element> elementsLevel1 = element.elements();

                if (CollectionUtil.isNotEmpty(elementsLevel1)) {
                    for (Element elementLevel1 : elementsLevel1) {
                        if (StrUtil.isNotEmpty(elementLevel1.getName()) && StrUtil.isNotEmpty(
                            elementLevel1.getText())) {
                            // System.out.println(elementLevel1.getName() + "--ele2_2->" + elementLevel1.getText());

                            if ("item".equals(elementLevel1.getName())) {
                                itemIndex++;
                                // System.out.println("########## " + itemIndex);
                                bbcAudioEntity = new BbcAudioEntity();
                                bbcAudioEntityList.add(bbcAudioEntity);
                            }
                        }
                        List<Element> elementsLevel2 = elementLevel1.elements();
                        if (CollectionUtil.isNotEmpty(elementsLevel2)) {
                            for (Element elementLevel2 : elementsLevel2) {
                                if (StrUtil.isNotEmpty(elementLevel2.getName()) && StrUtil.isNotEmpty(
                                    elementLevel2.getText())) {
                                    // System.out.println(elementLevel2.getName() + "--ele3->" + elementLevel2.getText());
                                    if ("title".equals(elementLevel2.getName())) {
                                        bbcAudioEntity.setTitle(elementLevel2.getText());
                                    }
                                    if ("description".equals(elementLevel2.getName())) {
                                        bbcAudioEntity.setDescription(elementLevel2.getText());
                                    }
                                    if ("pubDate".equals(elementLevel2.getName())) {
                                        String pubDateStr = elementLevel2.getText();
                                        Date date = new Date(pubDateStr);
                                        SimpleDateFormat dd = new SimpleDateFormat("yyMMdd");
                                        String pubDate = dd.format(date);
                                        bbcAudioEntity.setPubDate(pubDate);
                                    }
                                }
                                List<Element> elementsLevel3 = elementLevel2.elements();
//                                // System.out.println(elementsLevel4.size());

                                if (CollectionUtil.isNotEmpty(elementsLevel3)) {
                                    for (Element elementLevel3 : elementsLevel3) {
                                        if (StrUtil.isNotEmpty(elementLevel3.getName()) && StrUtil.isNotEmpty(
                                            elementLevel3.getText())) {
                                            // System.out.println(                                                elementLevel3.getName() + "--ele4->" + elementLevel3.getText());
                                        }
                                        List<Element> elementsLevel4 = elementLevel2.elements();
//                                // System.out.println(elementsLevel4.size());
                                    }
                                } else {
                                    if (StrUtil.isNotEmpty(elementLevel2.getName()) && StrUtil.isNotEmpty(
                                        elementLevel2.getText())) {
                                        // System.out.println(                                            elementLevel2.getName() + "--ele2_3->" + elementLevel2.getText());
                                    }
                                }

                                //未知属性名情况下
                                List<Attribute> attributesL3 = elementLevel2.attributes();
                                for (Attribute attribute : attributesL3) {
                                    if (StrUtil.isNotEmpty(attribute.getName()) && StrUtil.isNotEmpty(
                                        attribute.getValue())) {
                                        // System.out.println(attribute.getName() + "--attr3-> " + attribute.getValue());
                                        if ("url".equals(attribute.getName())) {
                                            bbcAudioEntity.setUrl(attribute.getValue());
                                        }
                                    }
                                }
                            }
                        } else {
                            if (StrUtil.isNotEmpty(elementLevel1.getName()) && StrUtil.isNotEmpty(
                                elementLevel1.getText())) {
                                // System.out.println(                                    elementLevel1.getName() + "--ele1->" + elementLevel1.getText());
                            }
                        }

                        //未知属性名情况下
                        List<Attribute> attributesL2 = elementLevel1.attributes();
                        for (Attribute attribute : attributesL2) {
                            if (StrUtil.isNotEmpty(attribute.getName()) && StrUtil.isNotEmpty(attribute.getValue())) {
                                // System.out.println(attribute.getName() + "--attr2-> " + attribute.getValue());
                            }
                        }
                    }
                } else {
                    if (StrUtil.isNotEmpty(element.getText())) {
                        // System.out.println("element.getText: " + element.getText());
                    }
                }
                //未知属性名情况下
                List<Attribute> attributesLevel1 = element.attributes();
                for (Attribute attribute : attributesLevel1) {
                    if (StrUtil.isNotEmpty(attribute.getName()) && StrUtil.isNotEmpty(attribute.getValue())) {
                        // System.out.println(attribute.getName() + "--attr1-> " + attribute.getValue());
                    }
                }
            }
        }

        return bbcAudioEntityList;
    }


}
