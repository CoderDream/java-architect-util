package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CoderDream
 */
public class GenSrtUtil {


    public final static String FOLDER_NAME = "230810";

    public static void main(String[] args) {

//        ep230907();
        String folderName = "220106";

        List<String> stringList = GenSrtUtil.processScriptDialog(folderName);
        System.out.println("##########################");
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println("#" + i + "\t:length: " + stringList.get(i).length() + "\t\t: " + stringList.get(i));
        }
    }

    /**
     * 获取主持人和嘉宾名字列表
     *
     * @return
     */
    public static List<String> getHost() {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
//        String filePath = folderPath + File.separator + "host.xlsx";
        String filePath = folderPath + File.separator + "host.txt";

//        List<String> hostList = CdExcelUtil.genHostList(filePath);
//        System.out.println(hostList.stream().map(String::valueOf).collect(Collectors.joining(",")));

        List<String> hostList = FileUtil.readLines(filePath, "UTF-8");
        return hostList;
    }

    public static void writeHost(String folderName, List<String> talkerList) {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
//        String filePath = folderPath + File.separator + "host.xlsx";
        String filePath = folderPath + File.separator + "host.txt";
        String filePathNew = folderPath + File.separator + folderName + "_host.txt";

//        List<String> hostList = CdExcelUtil.genHostList(filePath);
//        System.out.println(hostList.stream().map(String::valueOf).collect(Collectors.joining(",")));

        FileUtil.writeLines(talkerList, filePath, "UTF-8");
        FileUtil.writeLines(talkerList, filePathNew, "UTF-8");
    }

    /**
     * 获取主持人和嘉宾名字集合
     *
     * @return
     */
    public static Set<String> getHostSet() {
        Set<String> hostSet = new TreeSet<>();
        List<String> hostList = getHost();
        for (String s : hostList) {
            hostSet.add(s.toLowerCase());
        }
        return hostSet;
    }

    /**
     * 缩写形式列表
     *
     * @return
     */
    public static Map<String, String> genAbbrevCompleteMap() {
        String filePath = BbcConstants.ROOT_FOLDER_NAME + FOLDER_NAME + "abbreviation.xlsx";

        Map<String, String> stringMap = CdExcelUtil.genAbbrevCompleteMap(filePath);
//        System.out.println(hostList.stream().map(String::valueOf).collect(Collectors.joining(",")));

        return stringMap;
    }

    public static List<String> getScripts(String folderName, String fileName) {
        Set<String> hostSet = new TreeSet<>();
        hostSet.addAll(getHost());
        if (StrUtil.isEmpty(folderName)) {
            folderName = FOLDER_NAME;
        }

        if (StrUtil.isEmpty(fileName)) {
            fileName = "script.txt";
        }

        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt"); //
        List<String> scriptList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (StrUtil.isNotEmpty(line) && !hostSet.contains(line)) {
//                    System.out.println("###:  " + line);
                    scriptList.add(line);
//                    scriptList.addAll(BreakUpToSentence.splitSentence(line));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scriptList;
    }

    public static List<String> getScripts(String filePath) {
        Set<String> hostSet = new TreeSet<>();
        hostSet.addAll(getHost());
//        String filePath = ROOT_FOLDER_NAME + FOLDER_NAME + "script.txt";
        List<String> scriptList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (StrUtil.isNotEmpty(line) && !hostSet.contains(line)) {
//                    System.out.println("###:  " + line);
                    scriptList.add(line);
//                    scriptList.addAll(BreakUpToSentence.splitSentence(line));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scriptList;
    }

    /**
     * @param folderName
     * @return
     */
    public static List<String> processScriptDialogV2(String folderName) {
        String fileName = "script_dialog";
        Set<String> hostSet = new TreeSet<>();
        hostSet.addAll(getHost());
        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> scriptList = new ArrayList<>();
        scriptList.add("6 Minute English from bbc learningenglish.com");

        List<ScriptEntity> scriptEntityList = CdFileUtils.genScriptEntityList(filePath);
        List<String> contentList = scriptEntityList.stream().map(ScriptEntity::getContent)
            .collect(Collectors.toList());

        String tempStr = "";
        for (String content : contentList) {
            List<String> strings = BreakUpToSentence.breakToSentence(content);
            for (String str : strings) {
                // 如果大于句子长度阈值
                if (str.length() > BbcConstants.SENTENCE_LENGTH) {
                    // 如果有分号
                    if (str.contains(";")) {
                        String[] semicolonArr = str.split(";");
                        for (int i = 0; i < semicolonArr.length; i++) {
                            tempStr = semicolonArr[i];
                            if (tempStr.length() > BbcConstants.SENTENCE_LENGTH) {
                                // 根据逗号分割
                                processCommaScript(str, scriptList);
                            } else {
                                if (i != semicolonArr.length - 1) {
                                    scriptList.add(tempStr + ";");
                                } else {
                                    scriptList.add(tempStr);
                                }
                            }
                        }
                    } else {
                        processCommaScript(str, scriptList);
                    }
                } else {
                    scriptList.add(str);
                }
            }
        }
        // 写文件
        String newFileName = CommonUtil.getFullPathFileName(folderName, fileName, "_wx.txt");
        // 6 Minute English
        //from bbc learningenglish.com
        scriptList.add("6 Minute English from bbc learningenglish.com");

        for (String script : scriptList) {
            if (script.length() > BbcConstants.SINGLE_SCRIPT_LENGTH) {
                System.out.println(script.length() + ":" + script);
            }
        }

        CdFileUtils.writeToFile(newFileName, scriptList);

        return scriptList;
    }

    private static void processCommaScript(String str, List<String> scriptList) {
        String tempStr;
        // 根据逗号分割
        String[] commaArr = str.split(",");
        for (int j = 0; j < commaArr.length; j++) {
            tempStr = commaArr[j];
            if (j != commaArr.length - 1) {
                scriptList.add(tempStr + ",");
            } else {
                scriptList.add(tempStr);
            }
        }
    }

    /**
     * @param folderName
     * @return
     */
    public static List<String> processScriptDialog(String folderName) {
        String fileName = "script_dialog";
        Set<String> hostSet = new TreeSet<>();
        hostSet.addAll(getHost());
        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> scriptList = new ArrayList<>();

        if(folderName.startsWith("22") || folderName.startsWith("21") || folderName.startsWith("20")|| folderName.startsWith("19")|| folderName.startsWith("18")) {
            scriptList.add("This is a download from bbc learning English.");
            scriptList.add("To find out more, visit our website.");
        }

        scriptList.add("6 Minute English from bbc learningenglish.com");
        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            List<String> stringList2 = CdFileUtils.readFileContent(filePath);
            for (String line : stringList2) {
                // 如果不为空，而且也不是主持人及嘉宾列表中的字符串
                if (StrUtil.isNotEmpty(line) && !hostSet.contains(line.trim())) {
                    // System.out.println("###:  " + line);
                    line = line.replaceAll("     ", " ");
                    // 多个空格置换成一个空格
                    line = line.replaceAll("  ", " ");

                    line = line.replaceAll(" ", " ");
                    // 中文全角减号置换成英文【-】
                    line = line.replaceAll("–", "-");
                    //
                    line = line.replaceAll("\t", " ");
                    // 去除前后空格
                    line = line.trim();
                    // 如果当前行超出设定的长度
                    if (line.length() > BbcConstants.SINGLE_SCRIPT_LENGTH) {
                        // 先分割成句子
                        List<String> stringList = BreakUpToSentence.breakToSentence(line);
                        for (String str : stringList) {
                            // 如果存在【 - 】，这以 【- 】分割
                            String[] split = str.split(" - ");
                            if (split != null && split.length > 1) {
                                for (int i = 0; i < split.length; i++) {
                                    if (i == 0) {
                                        processSentence(scriptList, split[i]);
                                    } else {
                                        processSentence(scriptList, "- " + split[i]);
                                    }
                                }
                            } else {
                                processSentence(scriptList, str);
                            }
                        }
                    } else {
                        scriptList.add(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 写文件
        String newFileName = CommonUtil.getFullPathFileName(folderName, fileName, "_new.txt");
        // 6 Minute English
        //from bbc learningenglish.com
        scriptList.add("6 Minute English from bbc learningenglish.com");

        for (String tempStr : scriptList) {
            if (tempStr.length() > BbcConstants.SINGLE_SCRIPT_LENGTH) {
                System.out.println(tempStr.length() + ":" + tempStr);
            }
        }

        CdFileUtils.writeToFile(newFileName, scriptList);

        return scriptList;
    }

    /**
     * 处理单行
     *
     * @param scriptList
     * @param splitStr
     */
    private static void processSentence(List<String> scriptList, String splitStr) {
        // 如果单行字符超过某个阈值，则需要分割
        if (splitStr.length() > BbcConstants.SINGLE_SCRIPT_LENGTH) {
//            List<String> splitSentence = BreakUpToSentence.splitSentence(splitStr);
            String[] strings = splitStr.split(",");
            if (strings != null && strings.length > 0) {
                int len = strings.length;

                // 分段处理批量短句，
                List<String> subList = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    // 去掉左边空格
                    strings[i] = StringUtils.stripStart(strings[i], null);
                    if (i == len - 1) {
                        processSubSentence(subList, strings[i]);
                    } else {
                        processSubSentence(subList, strings[i] + ", ");
                    }
                }

                // 如果前后两句够短就拼接
                // for () TODO

                List<String> mergeSubSentence = mergeSubSentence(subList);

                scriptList.addAll(mergeSubSentence);
            } else {
                processSubSentence(scriptList, splitStr);
            }
        }
        // 否则直接处理
        else {
            scriptList.add(splitStr);
        }
    }

    private static void processSubSentence(List<String> scriptList, String splitStr) {
        // 如果单行字符超过某个阈值，则需要分割
        if (splitStr.length() > BbcConstants.SINGLE_SCRIPT_LENGTH) {
//            List<String> splitSentence = BreakUpToSentence.splitSentence(splitStr);
            String[] strings = splitStr.split(" ");
            List<String> splitSentence = Arrays.asList(strings);
            if (CollectionUtil.isNotEmpty(splitSentence)) {
                scriptList.addAll(processString(splitSentence));
            } else {
                System.out.println("##### splitSentence ERROR: " + splitStr + " length: " + splitStr.length());
            }
        }
        // 否则直接处理
        else {
            scriptList.add(splitStr);
        }
    }

    public static void checkSentenceLength(String folderName, String fileName) {
        String newFileName = CommonUtil.getFullPathFileName(folderName, fileName, "_new.txt");
        List<String> scriptList = CdFileUtils.readFileContent(newFileName);
        for (String tempStr : scriptList) {
            if (tempStr.length() > 60) {
                System.out.println(tempStr.length() + ":" + tempStr);
            } else if (tempStr.length() < 2) {
                System.out.println(tempStr.length() + ":" + tempStr);
            }
        }
    }


    public static void processInteger(List<Integer> integerList) {
        int MAX = 55;
        // 如果只有一个元素，直接返回
        if (integerList.size() == 1) {
            System.out.println(integerList.get(0));
        } else {
            int sum;
            // 先将第一个元素保存到临时变量
            int temp = integerList.get(0);
            for (int i = 1; i < integerList.size(); i++) {
                // 累加循环得到的变量
                sum = temp + integerList.get(i);
                // 如果没有超过阈值，则继续
                if (sum < MAX) {
                    // 修改临时变量
                    temp = sum;
                } else {
                    // 已经超过阈值，则输出临时变量（累加结束）
                    System.out.println(temp);
                    // 将当前元素赋值给临时变量
                    temp = integerList.get(i);
                }
                // 如果没有元素了，则直接输出最后得到的结果
                if (i == integerList.size() - 1) {
                    System.out.println(temp);
                }
            }
        }
    }

    /**
     * 输出合并后的字符串列表，字符串长度不大于某个阈值
     *
     * @param stringList 输入字符串列表
     * @return 输入字符串列表
     */
    public static List<String> processString(List<String> stringList) {
        List<String> result = new ArrayList<>();
        String tempString;
        // 如果只有一个元素，直接返回
        if (stringList.size() == 1) {
            System.out.println(stringList.get(0));
            tempString = stringList.get(0);
            result.add(tempString);
        } else {
            int sum;
            String sumStr;
            // 先将第一个元素保存到临时变量
            tempString = stringList.get(0);
            int temp = tempString.length();
            for (int i = 1; i < stringList.size(); i++) {
                // 累加循环得到的变量
                sum = temp + stringList.get(i).length();
                sumStr = tempString + " " + stringList.get(i);
                sum += 1;
                // 如果没有超过阈值，则继续  + 5
                if (sum <= BbcConstants.SINGLE_SCRIPT_LENGTH + 5) {
                    // 修改临时变量
                    temp = sum;
                    tempString = sumStr;
                } else {
                    // 5. Apache Commons
                    //
                    //为此，我们首先添加的 lang3 依赖性：
                    //
                    //<dependency>
                    //    <groupId>org.apache.commons</groupId>
                    //    <artifactId>commons-lang3</artifactId>
                    //    <version>3.11</version>
                    //</dependency>
                    //根据文档，我们使用null来去除空格：
//                    String ltrim = StringUtils.stripStart(src, null);
//                    String rtrim = StringUtils.stripEnd(src, null);
                    // 已经超过阈值，则输出临时变量（累加结束）
//                    result.add(tempString.tr()); // TODO 去空格
                    result.add(StringUtils.stripStart(tempString, null)); // TODO 去空格
                    // 将当前元素赋值给临时变量
                    temp = stringList.get(i).length();
                    tempString = stringList.get(i);
                }
                // 如果没有元素了，则直接输出最后得到的结果
                if (i == stringList.size() - 1) {
                    result.add(tempString.trim());
                }
            }
        }
        return result;
    }

    /**
     * 输出合并后的字符串列表，字符串长度不大于某个阈值
     *
     * @param stringList 输入字符串列表
     * @return 输入字符串列表
     */
    public static List<String> mergeSubSentence(List<String> stringList) {
        List<String> result = new ArrayList<>();
        String tempString;
        // 如果只有一个元素，直接返回
        if (stringList.size() == 1) {
            System.out.println(stringList.get(0));
            tempString = stringList.get(0);
            result.add(tempString);
        } else {
            int sum;
            String sumStr;
            // 先将第一个元素保存到临时变量
            tempString = stringList.get(0);
            int temp = tempString.length();
            for (int i = 1; i < stringList.size(); i++) {
                // 累加循环得到的变量
                sum = temp + stringList.get(i).length();
                sumStr = tempString + " " + stringList.get(i);
                sum += 1;
                // 如果没有超过阈值，则继续  + 5
                if (sum <= BbcConstants.SINGLE_SCRIPT_LENGTH) {
                    // 修改临时变量
                    temp = sum;
                    tempString = sumStr;
                } else {
                    // 5. Apache Commons
                    //
                    //为此，我们首先添加的 lang3 依赖性：
                    //
                    //<dependency>
                    //    <groupId>org.apache.commons</groupId>
                    //    <artifactId>commons-lang3</artifactId>
                    //    <version>3.11</version>
                    //</dependency>
                    //根据文档，我们使用null来去除空格：
//                    String ltrim = StringUtils.stripStart(src, null);
//                    String rtrim = StringUtils.stripEnd(src, null);
                    // 已经超过阈值，则输出临时变量（累加结束）
//                    result.add(tempString.tr()); // TODO 去空格
                    result.add(StringUtils.stripStart(tempString, null)); // TODO 去空格
                    // 将当前元素赋值给临时变量
                    temp = stringList.get(i).length();
                    tempString = stringList.get(i);
                }
                // 如果没有元素了，则直接输出最后得到的结果
                if (i == stringList.size() - 1) {
                    result.add(tempString.trim());
                }
            }
        }
        return result;
    }
}
