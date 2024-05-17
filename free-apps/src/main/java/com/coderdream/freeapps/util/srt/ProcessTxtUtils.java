package com.coderdream.freeapps.util.srt;

import com.coderdream.freeapps.util.bbc.BreakUpSentence;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.other.CdDateTimeUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.translator.text.TranslatorTextUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CoderDream
 */
@Slf4j
public class ProcessTxtUtils {

    public static void main(String[] args) {
//        String subtitleFileName = "C:\\Users\\CoderDream\\Videos\\英语面试系列 01 自我介绍 - English interview Self-introducti\\英语面试系列 01 自我介绍 - English interview Self-introducti.srt";
//
//        subtitleFileName = "C:\\Users\\CoderDream\\Videos\\How to say\\How to say.srt";
////        List<SubtitleBaseEntity> subtitleBaseEntityList = genSubtitleBaseEntityList(subtitleFileName);
////        for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityList) {
////            System.out.println(subtitleBaseEntity);
////        }
//
////        System.out.println(genContent(subtitleFileName));
//
//        String folderName = "C:\\Users\\CoderDream\\Videos\\How to say\\";
//        String fileName = "How to say";
//
//        mergeScriptContent(folderName, fileName);

        String folderName = "D:\\02_Work\\202401_电鸭培训\\01_英语陪学\\英语面试系列\\";
        String fileName = "英语面试系列 04 谈论你以前的工作经历 - English interview series Talk about your working experience.txt";
//        fileName = "temp.txt";
        String fileNameNew = "英语面试系列 04 谈论你以前的工作经历 - English interview series Talk about your working experience-new.txt";

        String patternFileName =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "txt" + File.separatorChar
                + "replace-pattern-interview.txt";

        process(folderName, fileName, fileNameNew, patternFileName);
    }

    //    private static final String SEPARATOR_REGEX = "[,.?!，。？！]";
    private static final String SEPARATOR_REGEX = "[.?!。？！]";

    /**
     * 通过分隔符拆分句子,并保留分隔符
     * <p>
     * 例如：很抱歉打扰到您了，祝您生活愉快，再见。 根据分隔符拆分： 很抱歉打扰到您了， 祝您生活愉快， 再见。
     *
     * @return
     */
    public static String[] splitSentence(String sentence) {
        //1. 定义匹配模式
        Pattern p = Pattern.compile(SEPARATOR_REGEX);
        Matcher m = p.matcher(sentence);

        //2. 拆分句子[拆分后的句子符号也没了]
        String[] words = p.split(sentence);

        //3. 保留原来的分隔符
        if (words.length > 0) {
            int count = 0;
            while (count < words.length) {
                if (m.find()) {
                    words[count] += m.group();
                }
                count++;
            }
        }
        return words;
    }

    public static void process(String folderName, String fileName, String fileNameNew, String patternFileName) {
        List<String> contentStringList = CdFileUtils.readFileContent(folderName + fileName); // TODO

        Map<String, String> patternMap = new HashMap<>();
        List<String> patternStringList = CdFileUtils.readFileContent(patternFileName); // TODO
        for (String patternStr : patternStringList) {
            String[] strings = patternStr.split("\t");
            if (strings != null && strings.length == 2) {
                patternMap.put(strings[0], strings[1]);
            }
        }


//
//        try (BufferedReader br = new BufferedReader(new FileReader(patternFileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] strings = line.split("\t");
//                // 处理每个字段
////                for (String field : fields) {
////                    System.out.println(field);
////                }
//
//                if (strings != null && strings.length == 2) {
//                    patternMap.put(strings[0], strings[1]);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<String> newContentList = new ArrayList<>();
        for (String content : contentStringList) {
            if (StrUtil.isNotEmpty(content)) {

                String[] splitSentence = splitSentence(content);

                // 如果英文字符串数组不为空
                if (splitSentence != null) {

                    for (String str : splitSentence) {
                        newContentList.add(str.trim());
                    }
                } else {
                    newContentList.add(content.trim());
                    System.out.println(content);
                }
            }
        }

        List<String> finalStringList = new ArrayList<>();
        for (String pureContent : newContentList) {
            // 替换中文逗号
            Map<Integer, Integer> appearNumber = appearNumber(pureContent, "，");
            if (!appearNumber.isEmpty()) {
                Set<Integer> integers = appearNumber.keySet();
                for (Integer key : integers) {
                    Integer value = appearNumber.get(key);
                    if (value != 0) {
                        char ch = pureContent.charAt(value - 1);
                        // 如果中文逗号前是英文，则替换为英文逗号
                        if (Character.isLetter(ch)) {
                            StringBuilder sb = new StringBuilder(pureContent);
                            sb.setCharAt(value, ',');
                            pureContent = sb.toString();
                        }
                    } else {
                        System.out.println("############ 中文逗号开头 " + pureContent);
                        pureContent = pureContent.substring(1);
                    }
                }
            }
            // 参考：
            // Java-修改 String 指定位置的字符最全方法总结（StringBuilder 和 StringBuffer 的使用以及区别）
            // https://blog.csdn.net/qq_46634307/article/details/126283200
            // 替换英文逗号
            Map<Integer, Integer> appearNumberComma = appearNumber(pureContent, ",");
            if (!appearNumberComma.isEmpty()) {
                Set<Integer> integers = appearNumberComma.keySet();
                for (Integer key : integers) {
                    Integer value = appearNumberComma.get(key);
                    if (value != 0) {
                        char ch = pureContent.charAt(value - 1);
                        String s = String.valueOf(ch);

                        // 如果中文逗号前是英文，则替换为英文逗号
                        if (checkName(s)) {
                            StringBuilder sb = new StringBuilder(pureContent);
                            sb.setCharAt(value, '，');
                            pureContent = sb.toString();
                        }
                    } else {
//                        System.out.println("############ 英文逗号开头 " + pureContent);
                        pureContent = pureContent.substring(1);
                    }
                }
            }

            // 替换中文句号
            int indexFullStopCn = pureContent.lastIndexOf("。");
            if (indexFullStopCn != -1 && indexFullStopCn == pureContent.length() - 1) {
                // 倒数第二个字符
                char ch = pureContent.charAt(pureContent.length() - 2);
                if (Character.isLetter(ch)) {
                    pureContent = pureContent.substring(0, pureContent.length() - 1) + ".";
                }
            }

            // 替换英文句号
            int indexFullStopEn = pureContent.lastIndexOf(".");
            if (indexFullStopEn != -1 && indexFullStopEn == pureContent.length() - 1) {
                // 倒数第二个字符
                char ch = pureContent.charAt(pureContent.length() - 2);
                if (checkName(String.valueOf(ch))) {
                    pureContent = pureContent.substring(0, pureContent.length() - 1) + "。";
                }
            }

            // 第一个字符转换成大写
            char firstCh = pureContent.charAt(0);
            if (Character.isLetter(firstCh)) {
                StringBuilder sb = new StringBuilder(pureContent);
                String tempStr = String.valueOf(firstCh);
                tempStr = tempStr.toUpperCase();
                sb.setCharAt(0, tempStr.charAt(0));
                pureContent = sb.toString();
            }

            // 字符串替换
            Set<String> stringSet = patternMap.keySet();
            for (String key : stringSet) {
                pureContent = pureContent.replaceAll(key, patternMap.get(key));
            }

//            System.out.println(pureContent);
            finalStringList.add(pureContent);
        }

        for (String s : finalStringList) {
            System.out.println(s);
        }

        // 写中文翻译文本
        CdFileUtils.writeToFile(folderName + fileNameNew, finalStringList);

    }

    public static boolean checkName(String name) {
        int n = 0;
        for (int i = 0; i < name.length(); i++) {
            n = (int) name.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static Map<Integer, Integer> appearNumber(String srcText, String findText) {
        int count = 0;
        Map<Integer, Integer> result = new HashMap<>();
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
            int i = m.start();
//            System.out.println("第" + count + "次,位置：" + i);
            result.put(count, i);
        }
        return result;
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
        String srcFileName = folderName + fileName + ".txt";
        String srcFileNameCn = folderName + fileName + "_cn.txt";
        List<String> stringList = CdFileUtils.readFileContent(srcFileName);

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
        if (StrUtil.isEmpty(fileName)) {
            fileName = "script_dialog";
        }
        String srcFileName = folderName + fileName + ".txt";
        String srcFileNameCn = folderName + fileName + "_cn.txt";
        String srcFileNameMerge = folderName + fileName + "_中英双语对话脚本.txt";

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
                    "两个脚本格式不对，实体大小分别为：" + stringCnList.size() + "\t:\t"
                        + stringCnList.size()); //
            }
        }

        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameMerge, newList);
    }

    public static void mergeScriptContentSimple(String folderName, String fileName) {
        if (StrUtil.isEmpty(fileName)) {
            fileName = "script_dialog";
        }
        String srcFileName = folderName + fileName + ".txt";
        String srcFileNameCn = folderName + fileName + "_cn.txt";
        String srcFileNameMerge = folderName + fileName + "_中英双语对话脚本.txt";

//        List<ScriptEntity> scriptEntityListEn = CdFileUtils.genScriptEntityList(srcFileName);

        List<String> stringEnList = CdFileUtils.readFileContent(srcFileName);

        Set<String> set = new HashSet<>();
        List<String> pureList = new ArrayList<>();
        for (String s : stringEnList) {
            if (!set.contains(s)) {
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
