package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.coderdream.freeapps.util.callapi.HttpUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;

/**
 * @author CoderDream
 */
public class DictUtils {

    public static void main(String[] args) {
//        String word = "sustainable";
//        List<String> wordList = Arrays.asList("genre", "upbeat", "jaunty", "gasp", "gobsmacked", "high five");
//        List<String> wordList = Arrays.asList("upbeat", "jaunty", "gasp", "gobsmacked", "high five");
//        List<VocInfo> vocInfoList = DictUtils.queryWords(wordList);
//        for (VocInfo vocInfo : vocInfoList) {
//            System.out.println(vocInfo);
//        }

        String folderName = "230309";
        DictUtils.processVoc(folderName);

//        List<VocInfo> vocInfoList = DictUtils.getVocInfoList(folderName);
//        for (VocInfo vocInfo : vocInfoList) {
//            System.out.println(vocInfo);
//        }

        String wordCn = "气候变化否定者：一种观点，认为全球气候变化是不存在的或者人类活动对气候变化的影响被夸大了。";
        String wordExplainCn = "气候变化否定者：一种观点，认为全球气候变化是不存在的或者人类活动对气候变化的影响被夸大了。； · Climate deniers often argue that the scientific consensus on climate change is based on flawed data.； 气候变化否定者经常辩称，关于气候变化的科学共识是基于错误的数据的。";
        //
        int colonIndex = wordCn.indexOf("：");

        int firstIndexOfIndex = wordExplainCn.indexOf("； · ");
        int lastIndexOfIndex = wordExplainCn.lastIndexOf("； ");
        wordCn = wordExplainCn.substring(0, colonIndex);
//        System.out.println(wordCn);

        String sampleSentenceEn = wordExplainCn.substring(firstIndexOfIndex + 3, lastIndexOfIndex);
        String sampleSentenceCn = wordExplainCn.substring(lastIndexOfIndex + 3);
        wordExplainCn = wordExplainCn.substring(colonIndex + 1, firstIndexOfIndex);

        System.out.println(wordCn);
        System.out.println(wordExplainCn);
        System.out.println(sampleSentenceEn.trim());
        System.out.println(sampleSentenceCn);
    }

    public static List<VocInfo> getVocInfoList(String folderName) {
        String fileName = "voc_cn";
        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> scriptList = new ArrayList<>();

        List<VocInfo> vocInfoList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                scriptList.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断是否为6个词汇
        int size = 0;
        if (scriptList == null || scriptList.size() != 36) {
            System.out.println("判断是否为6个词汇，size：" + scriptList.size());
        } else {
            size = scriptList.size();
        }

        VocInfo vocInfo;
        for (int i = 0; i < size; i++) {
            if ((i + 1) % 6 == 0) {
                vocInfo = new VocInfo();
                vocInfo.setWord(scriptList.get(i - 5));
                vocInfo.setWordExplainEn(scriptList.get(i - 4));
                vocInfo.setWordCn(scriptList.get(i - 3));
                vocInfo.setWordExplainCn(scriptList.get(i - 2));
                vocInfo.setSampleSentenceEn(scriptList.get(i - 1));
                vocInfo.setSampleSentenceCn(scriptList.get(i));
                vocInfoList.add(vocInfo);
            }
        }
        return vocInfoList;
    }

    public static List<VocInfo> writeVocCnExcel(String folderName) {
        String fileName = "voc_cn";
        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> scriptList = new ArrayList<>();

        List<VocInfo> vocInfoList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                scriptList.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 写文件
        String newFileName = CommonUtil.getFullPathFileName(folderName, fileName, "_cn_excel.txt");

        List<String> scriptListNew = new ArrayList<>();
        // 添加第一个空格
        scriptListNew.add("");

        // 判断是否为6个词汇
        int size = 0;
        if (scriptList == null || scriptList.size() != 36) {
            System.out.println("判断是否为6个词汇，size：" + scriptList.size());
        } else {
            size = scriptList.size();
            String string;
            for (int i = 0; i < size; i++) {
                string = scriptList.get(i);
                scriptListNew.add(string);
                if ((i + 1) % 12 == 0) {
                    scriptListNew.add("");
                }
            }
        }

        if (CollectionUtil.isNotEmpty(scriptListNew)) {
            CdFileUtils.writeToFile(newFileName, scriptListNew);
        } else {
            System.out.println("###### 空");
        }

        return vocInfoList;
    }

    /**
     * @param folderName
     */
    public static void processVoc(String folderName) {
        String fileName = "voc";
        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");
        List<String> scriptList = new ArrayList<>();

        List<VocInfo> vocInfoList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (StrUtil.isNotEmpty(line)) {
                    scriptList.add(line);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断是否为6个词汇
        int size = 0;
        if (scriptList == null || scriptList.size() != 12) {
            System.out.println("判断是否为6个词汇");
            return;
        } else {
            size = scriptList.size();
        }

        VocInfo vocInfo;
        String word;
        int startIndex;
        int endIndex;
        for (int i = 0; i < size; i++) {
            if ((i + 1) % 2 == 0) {
                vocInfo = new VocInfo();
                word = scriptList.get(i - 1);
                startIndex = word.lastIndexOf("(");
                endIndex = word.lastIndexOf(")");
                if(startIndex != -1 && endIndex != -1) {
                    word = word.substring(0, startIndex - 1);
                }
                vocInfo.setWord(word);
                vocInfo.setWordExplainEn(scriptList.get(i));
                vocInfoList.add(vocInfo);
            }
        }

        DictUtils.queryVocInfoList(vocInfoList);

        // 写文件
        String newFileName = CommonUtil.getFullPathFileName(folderName, fileName, "_cn.txt");

        List<String> scriptListNew = new ArrayList<>();
        for (VocInfo vocInfo1 : vocInfoList) {
            scriptListNew.add(vocInfo1.getWord());
            scriptListNew.add(vocInfo1.getWordExplainEn());
            scriptListNew.add(vocInfo1.getWordCn() != null ? vocInfo1.getWordCn() : "");
            scriptListNew.add(vocInfo1.getWordExplainCn() != null ? vocInfo1.getWordExplainCn() : "");
            scriptListNew.add(vocInfo1.getSampleSentenceEn() != null ? vocInfo1.getSampleSentenceEn() : "");
            scriptListNew.add(vocInfo1.getSampleSentenceCn() != null ? vocInfo1.getSampleSentenceCn() : "");
//            scriptListNew.add("");
        }

        if (CollectionUtil.isNotEmpty(scriptListNew)) {
            CdFileUtils.writeToFile(newFileName, scriptListNew);
        } else {
            System.out.println("###### 空");
        }
    }

    /**
     * @param vocInfoList
     */
    public static void queryVocInfoList(List<VocInfo> vocInfoList) {
        List<VocInfo> result = new ArrayList<>();
        for (VocInfo vocInfo : vocInfoList) {
            VocInfo vocInfo1 = queryWord(vocInfo.getWord());
            BeanUtils.copyProperties(vocInfo1, vocInfo, "wordExplainEn");
            result.add(vocInfo);
        }
    }

    public static List<VocInfo> queryWords(List<String> wordList) {
        List<VocInfo> vocInfoList = new ArrayList<>();
        for (String word : wordList) {
            vocInfoList.add(queryWord(word));
        }

        return vocInfoList;
    }

    public static VocInfo queryWord(String word) {
        VocInfo vocInfo = new VocInfo();
        Map<String, Object> param = new LinkedHashMap<>();
        param.put("q", word);
//        param.put("client", "");
        Map<String, String> head = new LinkedHashMap<>();
        String url = "https://dict.youdao.com/jsonapi_s?doctype=json&jsonversion=4";
//        Class<T> t = Object.class;
        Integer retryTimes = 3;
        JSONObject jsonObject = HttpUtil.postWithForm(param, head, url, JSONObject.class, retryTimes);

        vocInfo.setWord(word);

        // 填充中文意思字段和句子字段
        fillExplain(vocInfo, jsonObject);

        return vocInfo;
    }

    /**
     * 填充
     *
     * @param vocInfo
     * @param jsonObject
     */
    public static void fillExplain(VocInfo vocInfo, JSONObject jsonObject) {
        // TODO
        String expression1 = "[ec].[exam_type]";
        Object byPath1 = jsonObject.getByPath(expression1);
        String usphone = "[ec].[word].[usphone]";
        Object usphoneObject = jsonObject.getByPath(usphone);
        String ukphone = "[ec].[word].[ukphone]";
        Object ukphoneObject = jsonObject.getByPath(ukphone);
        String expression3 = "[ec].[word].[trs].[pos]";
        Object byPath3 = jsonObject.getByPath(expression3);
        String expression4 = "[ec].[word].[trs].[tran]";
        Object byPath4 = jsonObject.getByPath(expression4);

        String wordCn = "";
        String wordExplainCn = "";
        if (ukphoneObject != null) {
            wordExplainCn += "英/" + ukphoneObject + "/";
        }
        if (usphoneObject != null) {
            wordExplainCn += "美/" + usphoneObject + "/";
        }

        String pos;
        String tran;

        if (byPath3 != null && byPath3 instanceof ArrayList && byPath4 != null && byPath4 instanceof ArrayList) {
            List posList = (ArrayList) byPath3;
            List tranList = (ArrayList) byPath4;
            int size = posList.size();
            if (posList.size() == tranList.size()) {
                for (int i = 0; i < size; i++) {
                    pos = posList.get(i) != null ? posList.get(i).toString() : "";
                    tran = tranList.get(i) != null ? tranList.get(i).toString() : "";
                    if (i == 0) {
                        wordCn = tran; // 设置中文
                    }
                    // 设置解释
                    if (i == size - 1) {
                        wordExplainCn += pos + "" + tran;
                    } else {
                        wordExplainCn += pos + "" + tran + "； ";
                    }
                }
            }
        }
        vocInfo.setWordCn(wordCn);

        vocInfo.setWordExplainCn(wordExplainCn);

        String expression5 = "[expand_ec].[word].[transList].[content].[sents].[sentSpeech].[0].[0].[0]";
        Object byPath5 = jsonObject.getByPath(expression5);
        String expression6 = "[expand_ec].[word].[transList].[content].[sents].[sentTrans].[0].[0].[0]";
        Object byPath6 = jsonObject.getByPath(expression6);

//        System.out.println(byPath1);
//        System.out.println(byPath2);
//        System.out.println(byPath3);
//        System.out.println(byPath4);
        String sampleSentenceEn = "";
        if (byPath5 != null) {
            sampleSentenceEn = byPath5.toString();
            vocInfo.setSampleSentenceEn(sampleSentenceEn);
        }
        if (byPath6 != null) {
            String sampleSentenceCn = byPath6.toString();
            vocInfo.setSampleSentenceCn(sampleSentenceCn);
        }

        // 如果包含冒号：

        // 没有找到例句，找柯林斯例句
        if (StrUtil.isEmpty(sampleSentenceEn)) {
            String expressionCollinsEn = "[collins].[collins_entries].[entries].[entry].[tran_entry].[exam_sents].[sent].[eng_sent].[0].[0].[0].[0]";
            Object objectCollinsEn = jsonObject.getByPath(expressionCollinsEn);
            String expressionCollinsCn = "[collins].[collins_entries].[entries].[entry].[tran_entry].[exam_sents].[sent].[chn_sent].[0].[0].[0].[0]";
            Object objectCollinsCn = jsonObject.getByPath(expressionCollinsCn);

            if (objectCollinsEn != null) {
                sampleSentenceEn = objectCollinsEn.toString();
                vocInfo.setSampleSentenceEn(sampleSentenceEn);
            }
            if (objectCollinsCn != null) {
                String sampleSentenceCn = objectCollinsCn.toString();
                vocInfo.setSampleSentenceCn(sampleSentenceCn);
            }
        }

        // 如果既有冒号，又有点号，则拆分
        if (wordExplainCn.contains(".") && wordExplainCn.contains("：")) {
            //
            int colonIndex = wordCn.indexOf("：");

            int firstIndexOfIndex = wordExplainCn.indexOf("； · ");
            int lastIndexOfIndex = wordExplainCn.lastIndexOf("； ");
            wordCn = wordExplainCn.substring(0, colonIndex);
//        System.out.println(wordCn);

             sampleSentenceEn = wordExplainCn.substring(firstIndexOfIndex + 3, lastIndexOfIndex);
            String sampleSentenceCn = wordExplainCn.substring(lastIndexOfIndex + 3);
            wordExplainCn = wordExplainCn.substring(colonIndex + 1, firstIndexOfIndex);

            System.out.println(wordCn);
            System.out.println(wordExplainCn);
            System.out.println(sampleSentenceEn.trim());
            System.out.println(sampleSentenceCn);

            vocInfo.setWordCn(wordCn);
            vocInfo.setWordExplainCn(wordExplainCn);
            vocInfo.setSampleSentenceEn(sampleSentenceEn);
            vocInfo.setSampleSentenceCn(sampleSentenceCn);
        }

//        JSONObject.get 2 sentSpeech sentTrans
    }

}
