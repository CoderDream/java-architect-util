package com.coderdream.freeapps.util.srt;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.bbc.BreakUpToSentence;
import com.coderdream.freeapps.util.bbc.CommonUtil;
import com.coderdream.freeapps.util.bbc.TranslateUtil;
import com.coderdream.freeapps.util.other.CdDateTimeUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.translator.text.TranslatorTextUtil;
import java.io.File;
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
 * <pre>
 * TODO
 * 1. 句号、问号后的第一个单词也要首字母大写
 *
 * </pre>
 *
 * @author CoderDream
 */
@Slf4j
public class ProcessYoutubeTxtUtilsV3 {

    public static void main(String[] args) {

        String folderName = "D:\\Download\\Daily English for intermediates\\";
//        folderName = "D:\\Download\\面試篇中級\\";
//        folderName = "D:\\Download\\每天睡前英語聽力練習，快速習慣美國人的正常語速\\";
        folderName = "C:\\Users\\CoderDream\\Documents\\慎小嶷十天突破雅思口语系列\\配套音频-剑16版\\十天突破雅思口语\\Day5：Part1短兵相接\\";
        String fileName = "v1.txt";
        fileName = "01_爱好类考题.txt";
//        fileName = "new_version_2.txt";
//        fileName = "temp.txt";
        String fileNameNew = "new_version_1-new.txt";

        String fileNameEnCn = "new_version_1-new-EnCn.txt";

        String patternFileName =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "txt" + File.separatorChar
                + "youtube.txt";

        process(folderName, fileName, fileNameNew, fileNameEnCn, patternFileName);
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

    public static final String MUSIC = "[Music]";

    public static final Integer PREFIX_SIZE = 20;

    public static final Integer REPEAT_SIZE = 5;

    public static void process(String folderName, String fileName, String fileNameNew, String fileNameEnCn,
        String patternFileName) {
        // TODO
//        List<String> contentStringList = CdFileUtils.readFileContent(folderName + fileName); //

//            String fileName = "eng";
        String srcFileName = "";// CommonUtil.getFullPathFileName(folderName, fileName, ".srt");
        // readSrcFileContent

//        List<SubtitleBaseEntity> subtitleBaseEntityList = CdFileUtils.readSrcFileContent(srcFileName);
//
//        List<String> subtitleList = subtitleBaseEntityList.stream().map(SubtitleBaseEntity::getSubtitle)
//            .collect(Collectors.toList());

//        List<String> subtitleList = CdFileUtils.readFileContent(folderName + fileName);
//
//        String text = subtitleList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));
//        List<String> stringListCn = TranslatorTextUtil.translatorText(text);


//        for (String s : newContentList) {
//            System.out.println(s);
//        }

        // 写中文翻译文本
//        CdFileUtils.writeToFile(folderName + fileNameNew, newContentList);


        List<String>  strings = CdFileUtils.readFileContent(folderName + fileName);
        String text = strings.stream().map(String::valueOf).collect(Collectors.joining(" "));
        List<String> rawContentList = BreakUpToSentence.breakToSentence(text);
        // 写中文翻译文本
        CdFileUtils.writeToFile(folderName + fileNameNew, rawContentList);

            // 翻译文本
        List<String> translatedStringList = TranslateUtil.translateStringList(rawContentList);

        List<String> enCnList = new ArrayList<>();
        int sentenceIndex = 0;
        for (int i = 0; i < rawContentList.size(); i++) {
            sentenceIndex++;
            enCnList.add(sentenceIndex + ". " + rawContentList.get(i));
            enCnList.add(translatedStringList.get(i));
            enCnList.add("");
        }

        // 写英中文双语文本
        CdFileUtils.writeToFile(folderName + fileNameEnCn, enCnList);
    }

    /**
     * java string 首字母大写方法(最高效率) https://blog.csdn.net/qq_37949192/article/details/117415225
     * 首字母大写(进行字母的ascii编码前移，效率是最高的)
     *
     * @param fieldName 需要转化的字符串
     */
    public static String upperFirstLetter(String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = toUpperCase(chars[0]);
        return String.valueOf(chars);
    }

    /**
     * 字符转成大写
     *
     * @param c 需要转化的字符
     */
    public static char toUpperCase(char c) {
        if (97 <= c && c <= 122) {
            c ^= 32;
        }
        return c;
    }

    public static String upperFirstLetterForSentence(String rawString) {
        if (StrUtil.isEmpty(rawString)) {
            return rawString;
        }

        String result = "";
        List<String> strings = BreakUpToSentence.breakToSentence(rawString);
        for (String str : strings) {
            result += upperFirstLetter(str) + " ";
        }

        return result;
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
