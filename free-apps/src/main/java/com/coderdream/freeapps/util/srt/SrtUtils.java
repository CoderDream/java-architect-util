package com.coderdream.freeapps.util.srt;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.bbc.CommonUtil;
import com.coderdream.freeapps.util.bbc.ScriptEntity;
import com.coderdream.freeapps.util.bbc.TranslateUtil;
import com.coderdream.freeapps.util.other.CdDateTimeUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.translator.text.TranslatorTextUtil;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CoderDream
 */
@Slf4j
public class SrtUtils {

    public static void main(String[] args) {
        String subtitleFileName = "C:\\Users\\CoderDream\\Videos\\英语面试系列 01 自我介绍 - English interview Self-introducti\\英语面试系列 01 自我介绍 - English interview Self-introducti.srt";

        subtitleFileName = "C:\\Users\\CoderDream\\Videos\\How to say\\How to say.srt";
//        List<SubtitleBaseEntity> subtitleBaseEntityList = genSubtitleBaseEntityList(subtitleFileName);
//        for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityList) {
//            System.out.println(subtitleBaseEntity);
//        }

//        System.out.println(genContent(subtitleFileName));

        String folderName = "C:\\Users\\CoderDream\\Videos\\How to say\\";
        String fileName = "How to say";

        folderName = "D:\\";
        fileName = "email-friend";
//        mergeScriptContent(folderName, fileName);

        String srcFileName = "E:\\bt\\Lethal Hardcore - Girl Scout Nookies 01 (2016) 1080p\\Girl Scout Nookies - S01 Bella Rose - 1080p.srt";
        String srcFileNameCn = "E:\\bt\\Lethal Hardcore - Girl Scout Nookies 01 (2016) 1080p\\Girl Scout Nookies - S01 Bella Rose - 1080p.chn.srt";
        translateEngSrc(srcFileName, srcFileNameCn);
    }

    public static void translateEngSrc(String srcFileName, String srcFileNameCn) {
        String fileName = "eng";
//        String srcFileNameCn = CommonUtil.getFullPathFileName(folderName, "chn", ".srt");
//        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".srt");
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
                // 优化翻译
//                upgradeTranslate(folderName, arr, j, subtitleList);

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


        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, newList);

//        // 双语字幕
//        String srcFileNameEnCn = CommonUtil.getFullPathFileName(folderName, "audio5", ".srt");
//        // 写双语歌词文本
//        CdFileUtils.writeToFile(srcFileNameEnCn, newListEnCn);
//
//        // 双语歌词
//        String lrcFileNameEnCn = CommonUtil.getFullPathFileName(folderName, "audio5", ".lrc");
//        // 写双语歌词文本
//        CdFileUtils.writeToFile(lrcFileNameEnCn, lrcListEnCn);
    }

    public static String genContent(String subtitleFileName) {
        List<SubtitleBaseEntity> subtitleBaseEntityList = genSubtitleBaseEntityList(subtitleFileName);
//        for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityList) {
//            System.out.println(subtitleBaseEntity);
//        }
        String content = "";

        List<String> subtitles = subtitleBaseEntityList.stream().map(SubtitleBaseEntity::getSubtitle)
            .collect(Collectors.toList());
        for (String subtitle : subtitles) {
            content += subtitle + "\r\n";
        }

        String vocFileName = "C:\\Users\\CoderDream\\Videos\\How to say\\How to say.txt";// CommonUtil.getFullPathFileName(folderName, "voc", ".txt");

        CdFileUtils.writeToFile(vocFileName, subtitles);


        return content;
    }

    public static List<String> genContentList(String subtitleFileName) {
        List<SubtitleBaseEntity> subtitleBaseEntityList = genSubtitleBaseEntityList(subtitleFileName);
//        for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityList) {
//            System.out.println(subtitleBaseEntity);
//        }
        String content = "";

        List<String> subtitles = subtitleBaseEntityList.stream().map(SubtitleBaseEntity::getSubtitle)
            .collect(Collectors.toList());

        return subtitles;
    }

    public static void translateEnToCn(String folderName, String fileName) {
//        String fileName = "script_dialog";
        String srcFileName = folderName+ fileName+ ".txt";
        String srcFileNameCn = folderName+ fileName+ "_cn.txt";
//        List<String> stringList = CdFileUtils.readFileContent(srcFileName);

        List<String> stringList = CdFileUtils.readFileContent(srcFileName).stream().filter(str -> !str.trim().isEmpty()).collect(Collectors.toList());;//.stream().filter(Objects::nonNull).collect(Collectors.toList());

        String text = stringList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
        System.out.println("text:  " + text);
        List<String> stringListCn = TranslatorTextUtil.translatorText(text);

        List<String> newList = new ArrayList<>();
        for (int i = 0; i < stringListCn.size(); i++) {
            String temp = stringListCn.get(i);
            String[] arr = temp.split("\r\n");
            for (int j = 0; j < arr.length; j++) {
                System.out.println(arr[j]);
                newList.add(arr[j]);
                // 如果最后一行不是空格，则补一个空白字符串
                if (j == arr.length - 1 && StrUtil.isNotEmpty(arr[j])) {
                    newList.add("");
                }
            }
        }

        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, newList);
    }



    /**
     * 生成对话脚本合集
     *
     * @param folderName
     */
    public static void mergeScriptContent(String folderName, String fileName) {
        if(StrUtil.isEmpty(fileName)) {
            fileName = "script_dialog";
        }
        String srcFileName = folderName+ fileName+ ".txt";
        String srcFileNameCn = folderName+ fileName+ "_cn.txt";
        String srcFileNameMerge = folderName+ fileName+ "_中英双语对话脚本.txt";

//        List<ScriptEntity> scriptEntityListEn = CdFileUtils.genScriptEntityList(srcFileName);

        List<String> stringEnList = CdFileUtils.readFileContent(srcFileName);


        translateEnToCn(folderName, fileName);
        List<String> stringCnList = CdFileUtils.readFileContent(srcFileNameCn);

//        List<ScriptEntity> scriptEntityListCn = CdFileUtils.genScriptEntityList(srcFileNameCn);
//        String scriptEntityEn;
//        ScriptEntity scriptEntityCn;
        String scriptEn;
        String scriptCn;
        List<String> newList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(stringEnList) && CollectionUtil.isNotEmpty(stringCnList)
            && stringEnList.size() == stringCnList.size()) {
            for (int i = 0; i < stringEnList.size(); i++) {
//                scriptEntityEn = stringEnList.get(i);
//                scriptEn = scriptEntityEn.getContent();
                scriptEn = stringEnList.get(i);
//                scriptEntityCn = scriptEntityListCn.get(i);
//                scriptCn = scriptEntityCn.getContent();
                scriptCn = stringCnList.get(i);
//                newList.add(scriptEntityEn.getTalker() + "(" + scriptEntityCn.getTalker() + ")");
                newList.add(scriptEn + "\r\n" + scriptCn);
                newList.add("");
            }
        } else {
            if (CollectionUtil.isEmpty(stringEnList)) {
                System.out.println("stringCnList 为空。");
            } else if (CollectionUtil.isEmpty(stringCnList)) {
                System.out.println("stringCnList 为空。");
            } else {
                System.out.println(
                    "两个脚本格式不对，实体大小分别为：" + stringEnList.size() + "\t:\t"
                        + stringCnList.size()); //
            }
        }

        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameMerge, newList);
    }

    public static void mergeScriptContentSimple(String folderName, String fileName) {
        if(StrUtil.isEmpty(fileName)) {
            fileName = "script_dialog";
        }
        String srcFileName = folderName+ fileName+ ".txt";
        String srcFileNameCn = folderName+ fileName+ "_cn.txt";
        String srcFileNameMerge = folderName+ fileName+ "_中英双语对话脚本.txt";

//        List<ScriptEntity> scriptEntityListEn = CdFileUtils.genScriptEntityList(srcFileName);

        List<String> stringEnList = CdFileUtils.readFileContent(srcFileName);

        Set<String> set = new HashSet<>();
        List<String> pureList = new ArrayList<>();
        for (String s: stringEnList        ) {
            if(!set.contains(s)) {
                pureList.add(s);
            }
            set.add(s);
        }


        translateEnToCn(folderName, fileName);
        List<String> stringCnList = CdFileUtils.readFileContent(srcFileNameCn);

//        List<ScriptEntity> scriptEntityListCn = CdFileUtils.genScriptEntityList(srcFileNameCn);
//        String scriptEntityEn;
//        ScriptEntity scriptEntityCn;
        String scriptEn;
        String scriptCn;
        List<String> newList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(stringEnList) && CollectionUtil.isNotEmpty(stringCnList)
            && stringEnList.size() == stringCnList.size()) {
            for (int i = 0; i < stringEnList.size(); i++) {
//                scriptEntityEn = stringEnList.get(i);
//                scriptEn = scriptEntityEn.getContent();
                scriptEn = stringEnList.get(i);
//                scriptEntityCn = scriptEntityListCn.get(i);
//                scriptCn = scriptEntityCn.getContent();
                scriptCn = stringCnList.get(i);
//                newList.add(scriptEntityEn.getTalker() + "(" + scriptEntityCn.getTalker() + ")");
                newList.add(scriptEn + "\r\n" + scriptCn);
                newList.add("");
            }
        } else {
            if (CollectionUtil.isEmpty(stringEnList)) {
                System.out.println("stringCnList 为空。");
            } else if (CollectionUtil.isEmpty(stringCnList)) {
                System.out.println("stringCnList 为空。");
            } else {
                System.out.println(
                    "两个脚本格式不对，实体大小分别为：" + stringCnList.size() + "\t:\t"
                        + stringCnList.size()); //
            }
        }

        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameMerge, newList);
    }

    public static List<SubtitleBaseEntity> genSubtitleBaseEntityList(String subtitleFileName) {
        long startTime = System.currentTimeMillis();

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "subtitle" + File.separatorChar + "2023";
//        String fileName = folderPath + File.separatorChar + subtitleFileName;

        List<SubtitleBaseEntity> subtitleBaseEntityList = null;
        List<String> biStringList = CdFileUtils.readFileContent(subtitleFileName); // TODO
        Map<Integer, SubtitleBaseEntity> subtitleBaseEntityMap = new LinkedHashMap<>();
        int index = 0;
        int count = 0;

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        if (CollectionUtils.isNotEmpty(biStringList)) {
            subtitleBaseEntityList = new ArrayList<>();
            SubtitleBaseEntity subtitleBaseEntity;
            for (String str : biStringList) {
                subtitleBaseEntity = subtitleBaseEntityMap.get(index);
                if (subtitleBaseEntity == null) {
                    subtitleBaseEntity = new SubtitleBaseEntity();
                    subtitleBaseEntityMap.put(index, subtitleBaseEntity);
                    subtitleBaseEntityList.add(subtitleBaseEntity);
                }
                count++;
                if (StrUtil.isNotEmpty(str)) {
                    log.error(str);
                    switch (count) {
                        case 1:
                            subtitleBaseEntity.setSubIndex(Integer.parseInt(str));
                            break;

                        case 2:
                            subtitleBaseEntity.setTimeStr(str);
                            break;
                        case 3:
                            subtitleBaseEntity.setSubtitle(str);
                            break;
                        case 4:
                            subtitleBaseEntity.setSubtitleSecond(str);
                            break;

                        default:
                            break;
                    }

                } else {
                    count = 0;
                    index++;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        String operateTimeLengthStr = CdDateTimeUtils.genMessage(period);

        log.error("本次耗时" + operateTimeLengthStr + "。");

        return subtitleBaseEntityList;
    }
}
