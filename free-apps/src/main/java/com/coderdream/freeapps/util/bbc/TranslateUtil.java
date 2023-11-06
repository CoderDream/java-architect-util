package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.model.TopicEntity;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.translator.text.TranslatorTextUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CoderDream
 */
public class TranslateUtil {

    public static void main(String[] args) {

        String folderName = "230302";
//        TranslateUtil.process(folderName);
//        TranslateUtil.mergeScriptContent(folderName);
//        TranslateUtil.translateEngSrc(folderName);

//
//        List<String> NUMBER_LIST = Arrays.asList("230406", "230413", "230420", "230427");
//        TranslateUtil.translateTitle(NUMBER_LIST);

//        TranslateUtil.mergeScriptContentWx(folderName);

        translateAllTitle();
    }

    public static List<String> translateTitle(List<String> folderNameList, String fileName) {
        if (fileName == null) {
            fileName = "script_raw";
        }
        List<String> titleList = new ArrayList<>();
        for (String folderName : folderNameList) {
            String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
            List<String> stringList = CdFileUtils.readFileContent(srcFileName);

            String title;
            int size = stringList.size();

            if(CollectionUtil.isNotEmpty(stringList)) {
                titleList.add(stringList.get(0));
            }
        }

        String textTitleList = titleList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        List<String> stringListTitleCn = TranslatorTextUtil.translatorText(textTitleList);
        String[] arr = new String[0];
        for (int i = 0; i < stringListTitleCn.size(); i++) {
            String temp = stringListTitleCn.get(i);
            arr = temp.split("\r\n");
        }

        List<String> titleCnList = Arrays.asList(arr);
        List<String> newList = new ArrayList<>();
        String titleTranslate;
        for (int i = 0; i < arr.length; i++) {
            titleTranslate = folderNameList.get(i).substring(2) + "\t" + titleList.get(i) + "\t" + arr[i];
            System.out.println(titleTranslate);
            newList.add(titleTranslate);
        }

        String srcFileNameCn = BbcConstants.ROOT_FOLDER_NAME + File.separator + "title.txt";
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, newList);
        return titleCnList;
    }

    /**
     * 手工粘贴文本
     * @param folderNameList
     * @param fileName
     * @return
     */
    public static List<String> translateTitleManual(List<String> folderNameList, String fileName) {
        if (fileName == null) {
            fileName = "script_raw";
        }
        List<String> titleList = new ArrayList<>();
        for (String folderName : folderNameList) {
            String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
            List<String> stringList = CdFileUtils.readFileContent(srcFileName);

            String title;
            int size = stringList.size();

            for (int i = 0; i < size; i++) {
                String tempStr = stringList.get(i);
//                System.out.println("tempStr: " + tempStr);
                if ("INTERMEDIATE LEVEL".equals(tempStr)) {
                    title = stringList.get(i + 1);
                    titleList.add(title);
                    break;
                }
            }
        }

        String textTitleList = titleList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        List<String> stringListTitleCn = TranslatorTextUtil.translatorText(textTitleList);
        String[] arr = new String[0];
        for (int i = 0; i < stringListTitleCn.size(); i++) {
            String temp = stringListTitleCn.get(i);
            arr = temp.split("\r\n");
        }

        List<String> titleCnList = Arrays.asList(arr);
        List<String> newList = new ArrayList<>();
        String titleTranslate;
        for (int i = 0; i < arr.length; i++) {
            titleTranslate = folderNameList.get(i).substring(2) + "\t" + titleList.get(i) + "\t" + arr[i];
            System.out.println(titleTranslate);
            newList.add(titleTranslate);
        }

        String srcFileNameCn = BbcConstants.ROOT_FOLDER_NAME + File.separator + "title.txt";
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, newList);
        return titleCnList;
    }

    /**
     * 翻译脚本
     *
     * @param folderName
     */
    public static void process(String folderName) {
        String fileName = "script_dialog";
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> stringList = CdFileUtils.readFileContent(srcFileName);

        String text = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        List<String> stringListCn = TranslatorTextUtil.translatorText(text);

        List<String> newList = new ArrayList<>();
        for (int i = 0; i < stringListCn.size(); i++) {
            String temp = stringListCn.get(i);
            String[] arr = temp.split("\r\n");
            for (int j = 0; j < arr.length; j++) {
                upgradeTranslate(folderName, arr, j);

                System.out.println(arr[j]);
                newList.add(arr[j]);
                // 如果最后一行不是空格，则补一个空白字符串
                if (j == arr.length - 1 && StrUtil.isNotEmpty(arr[j])) {
                    newList.add("");
                }
            }
        }

        String srcFileNameCn = CommonUtil.getFullPathFileName(folderName, fileName, "_cn.txt");
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, newList);
    }


    /**
     * 优化句子
     *
     * @param folderName
     * @param arr
     * @param j
     */
    private static void upgradeTranslate(String folderName, String[] arr, int j) {
        if ("抢".equals(arr[j])) {
            arr[j] = "罗伯";
        }
        if ("山 姆".equals(arr[j])) {
            arr[j] = "山姆";
        }
        arr[j] = arr[j].replaceAll("Rob", "罗伯");
        arr[j] = arr[j].replaceAll("伟大！", "太好了！");
        arr[j] = arr[j].replaceAll("右。", "好的。"); // Right应该翻译成好的，而不是右
        arr[j] = arr[j].replaceAll("山 姆", "山姆");
        arr[j] = arr[j].replaceAll("6分钟", "六分钟");
        arr[j] = arr[j].replaceAll(" 6 分钟", "六分钟");
        arr[j] = arr[j].replaceAll(";", "；");
        arr[j] = arr[j].replaceAll("——", " —— ");// ——
        arr[j] = arr[j].trim();//去掉前后空格
        if ("231026".equals(folderName)) {
            arr[j] = arr[j].replaceAll("一百个", "一百岁");
        }
        // 针对230413的AI翻译优化
        if ("230413".equals(folderName)) {
            arr[j] = arr[j].replaceAll("垃圾场", "情绪低落");
            arr[j] = arr[j].replaceAll("垃圾堆", "情绪低落");
            arr[j] = arr[j].replaceAll("最尖锐的", "极度");
            arr[j] = arr[j].replaceAll(" Covid", "冠状病毒");
            arr[j] = arr[j].replaceAll("Covid", "冠状病毒");
            arr[j] = arr[j].replaceAll("英国广播公司（BBC）", "BBC");//英国广播公司（BBC）
            arr[j] = arr[j].replaceAll("《纪录片》（The Documentary）", "《纪录片》");//《纪录片》（The Documentary）
            arr[j] = arr[j].replaceAll("海伦·罗素（Helen Russell）", "海伦·罗素");// 海伦·罗素（Helen Russell）
            arr[j] = arr[j].replaceAll("托马斯·迪克森（Thomas Dixon）", "托马斯·迪克森");// 托马斯·迪克森（Thomas Dixon）
        }
        // 针对230302的AI翻译优化
        if ("230302".equals(folderName)) {
            arr[j] = arr[j].replaceAll("Rob", "'dunk'");
            arr[j] = arr[j].replaceAll("扣篮", "'dunk'");
            arr[j] = arr[j].replaceAll("英国广播公司（BBC）", "BBC");//英国广播公司（BBC）
            arr[j] = arr[j].replaceAll("迈克尔·罗森（Michael Rosen）", "迈克尔·罗森");
            arr[j] = arr[j].replaceAll("朱莉·塞迪维（Julie Sedivy）", "朱莉·塞迪维");
            arr[j] = arr[j].replaceAll("计划", "节目");
        }

        //
        // 针对230330的AI翻译优化
        if ("230330".equals(folderName)) {
            arr[j] = arr[j].replaceAll("历克斯·米尔克（Alex Mielke）", "历克斯·米尔克");
        }
    }

    public static void translateEngSrc(String folderName) {
        String fileName = "eng";
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".srt");
        // readSrcFileContent

        List<SubtitleBaseEntity> subtitleBaseEntityList = CdFileUtils.readSrcFileContent(srcFileName);

        List<String> subtitleList = subtitleBaseEntityList.stream().map(SubtitleBaseEntity::getSubtitle)
            .collect(Collectors.toList());

        String text = subtitleList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        List<String> stringListCn = TranslatorTextUtil.translatorText(text);

        List<String> newList = new ArrayList<>();
        List<String> newListEnCn = new ArrayList<>();
        List<String> lrcListEnCn = new ArrayList<>();
        SubtitleBaseEntity subtitleBaseEntity;
        String timeStr;
        String lrc;
        for (int i = 0; i < stringListCn.size(); i++) {
            String temp = stringListCn.get(i);
            String[] arr = temp.split("\r\n");
            // 检查大小
            if (arr.length != subtitleBaseEntityList.size()) {
                System.out.println("###");
                break;
            }
            for (int j = 0; j < arr.length; j++) {

                upgradeTranslate(folderName, arr, j);

                System.out.println(arr[j]);
                subtitleBaseEntity = subtitleBaseEntityList.get(j);
                newList.add(subtitleBaseEntity.getSubIndex() + "");
                newList.add(subtitleBaseEntity.getTimeStr());
                newList.add(arr[j]);
                newList.add("");

                newListEnCn.add(subtitleBaseEntity.getSubIndex() + "");
                newListEnCn.add(subtitleBaseEntity.getTimeStr());
                newListEnCn.add(subtitleList.get(j) + "\r" + arr[j]);
                newListEnCn.add("");
                timeStr = subtitleBaseEntity.getTimeStr();
                timeStr = timeStr.substring(3, 11);
                timeStr = timeStr.replaceAll(",", ".");
                lrc = "[" + timeStr + "]" + subtitleList.get(j) + "|" + arr[j];
                lrcListEnCn.add(lrc);
            }
        }

        String srcFileNameCn = CommonUtil.getFullPathFileName(folderName, "chn", ".srt");
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, newList);

        // 双语字幕
        String srcFileNameEnCn = CommonUtil.getFullPathFileName(folderName, "audio5", ".srt");
        // 写双语歌词文本
        CdFileUtils.writeToFile(srcFileNameEnCn, newListEnCn);

        // 双语歌词
        String lrcFileNameEnCn = CommonUtil.getFullPathFileName(folderName, "audio5", ".lrc");
        // 写双语歌词文本
        CdFileUtils.writeToFile(lrcFileNameEnCn, lrcListEnCn);
    }

    /**
     * 生成对话脚本合集
     *
     * @param folderName
     */
    public static void mergeScriptContent(String folderName) {
        String fileName = "script_dialog";
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<ScriptEntity> scriptEntityListEn = CdFileUtils.genScriptEntityList(srcFileName);

        String srcFileNameCn = CommonUtil.getFullPathFileName(folderName, fileName, "_cn.txt");
        List<ScriptEntity> scriptEntityListCn = CdFileUtils.genScriptEntityList(srcFileNameCn);
        ScriptEntity scriptEntityEn;
        ScriptEntity scriptEntityCn;
        List<String> newList = new ArrayList<>();
        if (scriptEntityListEn.size() == scriptEntityListCn.size()) {
            for (int i = 0; i < scriptEntityListEn.size(); i++) {
                scriptEntityEn = scriptEntityListEn.get(i);
                scriptEntityCn = scriptEntityListCn.get(i);
                newList.add(scriptEntityEn.getTalker() + "(" + scriptEntityCn.getTalker() + ")");
                newList.add(scriptEntityEn.getContent() + "\r\n" + scriptEntityCn.getContent());
                newList.add("");
            }
        } else {
            System.out.println(
                "两个脚本格式不对，实体大小分别为：" + scriptEntityListEn.size() + "\t:\t"
                    + scriptEntityListCn.size()); //
        }

        String srcFileNameMerge = CommonUtil.getFullPathFileName(folderName, fileName, "_merge.txt");
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameMerge, newList);
    }

    /**
     * 生成对话脚本合集
     *
     * @param folderName
     */
    public static void mergeScriptContentWx(String folderName) {
        String fileName = "script_dialog_wx2";
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");

        List<String> stringList = CdFileUtils.readFileContent(srcFileName);

        String text = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        List<String> stringListCn = TranslatorTextUtil.translatorText(text);

        List<String> newList = new ArrayList<>();
        List<String> newListEnCn = new ArrayList<>();
        List<String> lrcListEnCn = new ArrayList<>();
        SubtitleBaseEntity subtitleBaseEntity;
        String timeStr;
        String lrc;

        for (int i = 0; i < stringListCn.size(); i++) {
            String temp = stringListCn.get(i);
            String[] arr = temp.split("\r\n");

            for (int j = 0; j < arr.length; j++) {

                upgradeTranslate(folderName, arr, j);

                System.out.println(arr[j]);
//                subtitleBaseEntity = subtitleBaseEntityList.get(j);
//                newList.add(subtitleBaseEntity.getSubIndex() + "");
//                newList.add(subtitleBaseEntity.getTimeStr());
//                newList.add(arr[j]);
//                newList.add("");

//                newListEnCn.add(subtitleBaseEntity.getSubIndex() + "");
//                newListEnCn.add(subtitleBaseEntity.getTimeStr());
                newListEnCn.add(stringList.get(j) + "\r" + arr[j]);
                newListEnCn.add("");

            }
        }

        String srcFileNameMerge = CommonUtil.getFullPathFileName(folderName, fileName, "_merge.txt");
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameMerge, newListEnCn);
    }


    /**
     * 生成对话脚本合集
     */
    public static void translateAllTitle() {
        String fileName = BbcConstants.ROOT_FOLDER_NAME + "all.txt";
        List<TopicEntity> topicEntityList = CdFileUtils.genTopicEntityList(fileName);

//        List<String> stringList = CdFileUtils.readFileContent(srcFileName);
        List<String> textTopicStringList = topicEntityList.stream().map(TopicEntity::getTopic)
            .collect(Collectors.toList());
        List<String> stringTopicCn = translateStringList(textTopicStringList);

        List<String> textDescriptionStringList = topicEntityList.stream().map(TopicEntity::getDescription)
            .collect(Collectors.toList());
        List<String> stringDescriptionCn = translateStringList(textDescriptionStringList);

        List<String> newListEnCn = new ArrayList<>();
        String titleStr;

        if (CollectionUtil.isNotEmpty(stringTopicCn) && CollectionUtil.isNotEmpty(stringDescriptionCn)
            && topicEntityList.size() == stringDescriptionCn.size()
            && stringTopicCn.size() == stringDescriptionCn.size()) {

            for (int i = 0; i < topicEntityList.size(); i++) {
                titleStr = topicEntityList.get(i).getEpisode()
                    + "\t" + topicEntityList.get(i).getTopic() + "|" + stringTopicCn.get(i)
                    + "\t" + topicEntityList.get(i).getDescription() + "|" + stringDescriptionCn.get(i);
                newListEnCn.add(titleStr);
            }

        }

        String fileNameCn = BbcConstants.ROOT_FOLDER_NAME + "all_title_cn.txt";
        // 写中文翻译文本
        CdFileUtils.writeToFile(fileNameCn, newListEnCn);
    }

    /**
     * 翻译字符串数组
     *
     * @param textTopicStringList
     * @return
     */
    public static List<String> translateStringList(List<String> textTopicStringList) {

        List<String> result = new ArrayList<>();
        String textTopic = textTopicStringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        List<String> stringList = TranslatorTextUtil.translatorText(textTopic);

        for (int i = 0; i < stringList.size(); i++) {
            String temp = stringList.get(i);
            String[] arr = temp.split("\r\n");
            result = Arrays.asList(arr);
        }

        return result;
    }

}