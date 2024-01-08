package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.io.FileUtil;
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
public class ProcessScriptUtil {

    public static void main(String[] args) {
        String folderName = "220106";
        ProcessScriptUtil.process(folderName);

//        replace();
    }

    /**
     * @param folderName 文件夹
     */
    public static void process(String folderName) {
        String fileName = folderName + "_script";
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> stringList = CdFileUtils.readFileContent(srcFileName);
        List<String> vocList = new ArrayList<>();
        List<String> scriptList = new ArrayList<>();
        String str = "";
        int vocBeginIndex = 0;
        int vocEndIndex = -1;
        int transcriptIndex = 0;

        int scriptBeginIndex = 0;
        int scriptEndIndex = 0;
        int size = stringList.size();
        for (int i = 0; i < size; i++) {
            String tempStr = stringList.get(i);
//            System.out.println("tempStr: " + tempStr);

            if (-1 != tempStr.lastIndexOf("word-for-word transcript")) {
                for (int x = 1; x < 6; x++) {
                    if (StrUtil.isNotEmpty(stringList.get(i + x))) {
                        scriptBeginIndex = i + x;
                        break;
                    }
                }
            }

            if (tempStr.contains("VOCABULARY") || tempStr.contains("Vocabulary")) {
                for (int x = 1; x < 5; x++) {
                    if (StrUtil.isNotEmpty(stringList.get(i + x))) {
                        vocBeginIndex = i + x;
                        break;
                    }
                }
                scriptEndIndex = i - 1;
            }

            if (tempStr.contains("QUIZ")) {
                vocEndIndex = i;
            }
        }

        // 如果vocEndIndex未更新过值，则更新vocEndIndex
        if (vocEndIndex == -1) {
            vocEndIndex = size;
        }

        // 生成词汇列表
        genVocList(vocBeginIndex, vocEndIndex, stringList, vocList);

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
        int beginIndex = 0;
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
            if (!emptyFlag || StrUtil.isNotEmpty(finalList.get(j))) {
                scriptList.add(finalList.get(j));
            }

            emptyFlag = StrUtil.isEmpty(finalList.get(j));
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
//            updateHostFile(scriptList); // TODO

            GenSrtUtil.writeHost(folderName, scriptList);
        } else {
            System.out.println("ERRRRR");
        }
    }

    /**
     * @param vocBeginIndex
     * @param vocEndIndex
     * @param stringList
     * @param vocList
     */
    private static void genVocList(int vocBeginIndex, int vocEndIndex, List<String> stringList, List<String> vocList) {
        // 设置词汇表的字符串列表
        int vocIdx = 0;// 上一次词汇的结束标志位
        String phrase;
        for (int i = vocBeginIndex; i < vocEndIndex; i++) {
            // 如果是空字符串，则说明
            String tempStr = stringList.get(i);
            if (StrUtil.isEmpty(tempStr)) {
                // 第一个
                if (vocIdx == 0) {
                    phrase = stringList.get(vocBeginIndex);

                    String explainEn = "";
                    for (int j = vocBeginIndex + 1; j < i; j++) {
                        if (StrUtil.isEmpty(explainEn)) {
                            explainEn += stringList.get(j);
                        } else {
                            explainEn += " " + stringList.get(j);
                        }
                    }

                    vocList.add(phrase);
                    vocList.add(explainEn);
                    vocList.add("");
                    vocIdx = i + 1;
                } else {
                    // 如果前一行不是空行就继续
                    if (!StrUtil.isEmpty(stringList.get(i - 1))) {
                        phrase = stringList.get(vocIdx);

                        String explainEn = "";
                        for (int j = vocIdx + 1; j < i; j++) {
                            if (StrUtil.isEmpty(explainEn)) {
                                explainEn += stringList.get(j);
                            } else {
                                explainEn += " " + stringList.get(j);
                            }
                        }

                        vocList.add(phrase);
                        vocList.add(explainEn);
                        vocList.add("");
                        vocIdx = i + 1;
                    }
                }

//                System.out.println("VOC tempStr: " + tempStr);
//                if (StrUtil.isNotEmpty(tempStr) || StrUtil.isNotEmpty(prevStr)) {
//                    vocList.add(tempStr);
//                }
            }
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
