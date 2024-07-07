package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.coderdream.freeapps.model.ScriptEntity;
import com.coderdream.freeapps.util.easyexcel.TestFileUtil;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 *     处理原始文本，执行一下操作：
 *     1、生成对话脚本文本文件；
 *     2、生成词汇表文本文件；
 *     3、更新主持人Excel文件（host.xlsx）
 *
 * </pre>
 *
 * @author CoderDream
 */
public class ProcessRawTxtUtil {

    public static void main(String[] args) {
        String folderName = "221124";
        ProcessRawTxtUtil.processRawTxtSrt(folderName);

//        replace();
    }

    /**
     * @param folderName
     */
    public static void processRawTxtSrt(String folderName) {
        String fileName = "script_raw";
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> stringList = CdFileUtils.readFileContent(srcFileName);
//        List<SubtitleBaseEntity> subtitleBaseEntityList = new ArrayList<>();
//
//        List<String> newScriptList = new ArrayList<>();
        List<String> vocList = new ArrayList<>();
        List<String> scriptList = new ArrayList<>();
        String str = "";
        int vocBeginIndex = 0;
        int vocEndIndex = 0;
        int transcriptIndex = 0;

        int scriptBeginIndex = 0;
        int scriptEndIndex = 0;
        int size = stringList.size();
        for (int i = 0; i < size; i++) {
            String tempStr = stringList.get(i);
//            System.out.println("tempStr: " + tempStr);
            if ("Vocabulary".equals(tempStr)) {
                if (StrUtil.isNotEmpty(stringList.get(i + 1))) {
                    vocBeginIndex = i + 1;
                }
                if (StrUtil.isNotEmpty(stringList.get(i + 2))) {
                    vocBeginIndex = i + 2;
                }
            }

//            if ("TRANSCRIPT".equals(tempStr)) {
//                transcriptIndex = i;
//            }

            if ("TRANSCRIPT".equals(tempStr)) {
                if (StrUtil.isNotEmpty(stringList.get(i + 1))) {
                    scriptBeginIndex = i + 1;
                    vocEndIndex = i - 1;
                }
                if (StrUtil.isNotEmpty(stringList.get(i + 2))) {
                    scriptBeginIndex = i + 2;
                    vocEndIndex = i;
                }
            }

            if (-1 != tempStr.lastIndexOf("word-for-word transcript")) {
                for(int x = 1; x < 6; x++) {
                    if (StrUtil.isNotEmpty(stringList.get(i + x))) {
                        scriptBeginIndex = i + x;
                        break;
                    }
                }
            }

            // 找到脚本的第一非空字符串的位置就是脚本的开始位置
//            if (tempStr.endsWith("word-for-word transcript.") || tempStr.endsWith("word-for-word transcript")
//                || -1 != tempStr.lastIndexOf("word-for-word transcript")) {
//                System.out.println("#####" + tempStr);
//                if (StrUtil.isNotEmpty(stringList.get(i + 1))) {
//                    scriptBeginIndex = i + 1;
//                } else if (StrUtil.isNotEmpty(stringList.get(i + 2))) {
//                    scriptBeginIndex = i + 2;
//                }
//            }
            // 脚本结束位置
//            if ("Latest 6 Minute English".equals(tempStr)) {
//                scriptEndIndex = i;
//            }
        }

        if (scriptEndIndex == 0) {
            scriptEndIndex = size;
        }

        // 设置词汇表的字符串列表
        for (int i = vocBeginIndex; i < vocEndIndex; i++) {
            String tempStr = stringList.get(i);
            String prevStr = stringList.get(i - 1);
            System.out.println("VOC tempStr: " + tempStr);
            if (StrUtil.isNotEmpty(tempStr) || StrUtil.isNotEmpty(prevStr)) {
                vocList.add(tempStr);
            }
        }

        // 设置对话脚本的字符串列表
        for (int i = scriptBeginIndex; i < scriptEndIndex; i++) {
            String tempStr = stringList.get(i);
            System.out.println("script: " + tempStr);
            scriptList.add(tempStr);
        }
        scriptList.add("");// 补最后的空格

        // (scriptList.get(i).startsWith("a)") || scriptList.get(i).startsWith("b)") ||
        // TODO 处理问题，将问题及选项合并到同一段落
        String previewOne = "";
        String previewTwo = "";
        String previewThree = "";
        String previewFour = "";
        String previewFive = "";
        String previewSix = "";

        String newScript = "";
        String currentScript = "";
        for (
            int i = 0; i < scriptList.size(); i++) {
            currentScript = scriptList.get(i);
            if (currentScript.startsWith("c)")) {
                previewOne = scriptList.get(i - 1);
                previewTwo = scriptList.get(i - 2);
                previewThree = scriptList.get(i - 3);
                previewFour = scriptList.get(i - 4);
                previewFive = scriptList.get(i - 5);
                previewSix = scriptList.get(i - 6);
                // 如果前一行为空，则将本行附加到前两行
                if (StrUtil.isEmpty(previewOne)) {
                    // 如果前五行为空，则将本行附加到前两行
                    if (StrUtil.isEmpty(previewFive)) {
                        newScript = previewSix + " " + previewFour + "; " + previewTwo + " " + currentScript;
                        newScript = newScript.replaceAll("\\?;", "\\?");
                        newScript = newScript.replaceAll("\\?,", "\\?"); // ?,
                        newScript = newScript.replaceAll(",;", ";");
                        scriptList.set(i - 6, newScript);
                        scriptList.remove(i);
                        scriptList.remove(i - 1);
                        scriptList.remove(i - 2);
                        scriptList.remove(i - 3);
                        scriptList.remove(i - 4);
                        scriptList.remove(i - 5);
                    }
                    // 否则附加到前一行
                    else {
                        newScript = previewFive + " " + previewFour + "; " + previewTwo + " " + currentScript;
                        newScript = newScript.replaceAll("\\?;", "\\?");
                        newScript = newScript.replaceAll("\\?,", "\\?"); // ?,
                        newScript = newScript.replaceAll(",;", ";");
                        scriptList.set(i - 5, newScript);
                        scriptList.remove(i);
                        scriptList.remove(i - 1);
                        scriptList.remove(i - 2);
                        scriptList.remove(i - 3);
                        scriptList.remove(i - 4);
                    }
                }
                // 没有空行，紧凑型
                else {
                    // 如果前五行为空，则将本行附加到前两行
                    if (StrUtil.isEmpty(previewThree)) {
                        newScript = previewFour + " " + previewTwo + "; " + previewOne + " " + currentScript;
                        newScript = newScript.replaceAll("\\?;", "\\?");
                        newScript = newScript.replaceAll("\\?,", "\\?"); // ?,
                        newScript = newScript.replaceAll(",;", ";");
                        scriptList.set(i - 4, newScript);
                        scriptList.remove(i);
                        scriptList.remove(i - 1);
                        scriptList.remove(i - 2);
                        scriptList.remove(i - 3);
                    }
                    // 否则附加到前一行
                    else {
                        newScript = previewThree + " " + previewTwo + "; " + previewOne + " " + currentScript;
                        newScript = newScript.replaceAll("\\?;", "\\?");
                        newScript = newScript.replaceAll("\\?,", "\\?"); // ?,
                        newScript = newScript.replaceAll(",;", ";");
                        scriptList.set(i - 3, newScript);
                        scriptList.remove(i);
                        scriptList.remove(i - 1);
                        scriptList.remove(i - 2);
                    }
                }
            }
        }

        String vocFileName = CommonUtil.getFullPathFileName(folderName, "voc", ".txt");
        CdFileUtils.writeToFile(vocFileName, vocList);

        String newFileName = CommonUtil.getFullPathFileName(folderName, "script_dialog", ".txt");

        // TODO
        int beginIndex = 0;
        int spaceIndex = 0;
        String tempStr;
        List<String> finalList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < scriptList.size(); i++) {
            tempStr = scriptList.get(i);
            if (StrUtil.isEmpty(tempStr)) {
                // 增加说话人
                finalList.add(scriptList.get(beginIndex));

                tempList = new ArrayList<>();
                for (int j = beginIndex + 1; j < i; j++) {
                    System.out.println(j + "\t" + scriptList.get(j));
                    tempList.add(scriptList.get(j));
                }
                String text = tempList.stream().map(String::valueOf).collect(Collectors.joining(" "));
                text = text.replaceAll("Hello. This is 6 Minute English from BBC Learning English. ", ",");
                text = text.replaceAll("  ", " ");// 将两个空格变成一个空格
                text = text.replaceAll(" ,", ",");// 去掉逗号前面的空格

                // 增加谈话内容
                finalList.add(text);
                // 增加空格
                finalList.add(tempStr);

                // 第一次
                if (i != 0) {
                    beginIndex = i + 1;
                }
            }
        }
        scriptList = new ArrayList<>();
        // 移除多余的空行，只保留一个
        boolean emptyFlag = false;
        for (int j = 0; j < finalList.size(); j++) {
            if(!emptyFlag || StrUtil.isNotEmpty(finalList.get(j))) {
//                finalList.set(j,  finalList.get(j).replaceAll("Hello. This is 6 Minute English from BBC Learning English. ", ""));
                scriptList.add(finalList.get(j));
            }

            emptyFlag = StrUtil.isEmpty(finalList.get(j));

//            if(j > 0) {
//                String str1 = finalList.get(j);
//                String str2 = stringList.get(j - 1);
//                System.out.println("VOC tempStr: " + str1 + "\t|\t");
//                if (StrUtil.isNotEmpty(str1) || StrUtil.isNotEmpty(str2)) {
//                    scriptList.add(str1);
//                }
//            } else {
//                scriptList.add(finalList.get(j));
//            }
        }

        // 校验脚本并补上末尾的句号
        int scriptListSize = scriptList.size();
        String scriptStr = "";
        String lastChar = "";
        if (scriptListSize % 3 == 0) {
            for (int i = 0; i < scriptListSize; i += 3) {
                scriptStr = scriptList.get(i + 1);
                if (StrUtil.isNotEmpty(scriptStr)) {
                    lastChar = scriptStr.substring(scriptStr.length() - 1);
//                    System.out.println("LAST_CHAR:\t" + lastChar);
                    if (StrUtil.isNotEmpty(lastChar) && !".".equals(lastChar) && !"!".equals(lastChar) && !"?".equals(
                        lastChar) && !"…".equals(lastChar) && !":".equals(lastChar)) {
                        scriptList.set(i + 1, scriptList.get(i + 1) + ".");
                    }
                }
            }

            CdFileUtils.writeToFile(newFileName, scriptList);

            // 更新Talker
//            updateHostFile(scriptList);

        } else {
            System.out.println("ERRRRR");
        }
    }

    public static void updateHostFile(List<String> scriptList) {
        List<ScriptEntity> scriptEntityList = new ArrayList<>();
        ScriptEntity scriptEntity;
        // 脚本列表是3的倍数，否则有问题
        if (scriptList.size() % 3 != 0) {
            System.out.println("###");
            return;
        }
        for (int i = 0; i < scriptList.size(); i += 3) {
            if (StrUtil.isEmpty(scriptList.get(i + 2))) {
                scriptEntity = new ScriptEntity();
                scriptEntity.setTalker(scriptList.get(i));
                scriptEntity.setContent(scriptList.get(i + 1));

                scriptEntityList.add(scriptEntity);
            }
        }

        List<String> talkerList = scriptEntityList.stream().map(ScriptEntity::getTalker).collect(Collectors.toList());

        Set<String> talkerSet = scriptEntityList.stream().map(ScriptEntity::getTalker).collect(Collectors.toSet());

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String filePath = folderPath + File.separator + "host.xlsx";

        List<String> hostList = CdExcelUtil.genHostList(filePath);
        talkerSet.addAll(hostList);
        List<Talker> talkers = new ArrayList<>();
        Talker talker1;
        for (String talker : talkerSet) {
            talker1 = new Talker();
            talker1.setTalker(talker);
            talkers.add(talker1);
        }

        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(filePath, Talker.class).sheet("Sheet1").doWrite(talkers);
    }

}

