package com.coderdream.freeapps.util.pdf;

//import com.itextpdf.io.source.RandomAccessFileOrArray;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
//import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.util.bbc.CommonUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CoderDream
 */
public class ReadPdfUtil {

    public static void main(String[] args) {

        String fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\2022\\221110\\221110_controlling_the_weather.pdf";
        fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\2022\\220630\\220630_science.pdf";

        fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\2023\\231207\\231207_invasive_species_why_not_eat_them.pdf";
//        String string = readPdfByPage(fileName);
//        String[] arr = string.split("\n");
//
//        System.out.println(arr);

//        PdfReader pr = null;
//        try {
//            pr = new PdfReader(fileName);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        PdfDocument pd = new PdfDocument(pr);
//        LocationTextExtractionStrategy strategy = new LocationTextExtractionStrategy();
//        PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy);
//        parser.processPageContent(pd.getPage(1));
//        String text = strategy.getResultantText();
//        System.out.println(text);

        List<String> stringList = getStringList(fileName);
        for (String str : stringList) {
            System.out.println(str);
        }
    }

    public static final String STR_ONE = "6 Minute English ©British Broadcasting Corporation";

    public static final String STR_TWO = "bbclearningenglish.com Page";

    public static void genScriptTxt(String folderName, String fileName) {

        List<String> stringList = getStringList(folderName + fileName);
        // 文本末尾补空行
        if (CollectionUtil.isNotEmpty(stringList) && StrUtil.isNotEmpty(stringList.get(stringList.size() - 1))) {
            stringList.add("");
        }

        String srcFileNameCn = folderName + fileName.substring(0, 6) + "_script" + ".txt";
        // 写中文翻译文本
        CdFileUtils.writeToFile(srcFileNameCn, stringList);
    }

    public static List<String> getStringList(String fileName) {
        String string = readPdfByPage(fileName);
        String[] arr = string.split("\n");

//        System.out.println(arr);

//        List<String> stringList = Arrays.asList(arr);
//        stringList.addAll(arr);
//        boolean emptyFlag = false;

        List<String> stringList = new ArrayList<>();
        int length = arr.length;
        int flagIdx = 0;
        for (int i = 0; i < length; i++) {
            String str = arr[i];
            // 找到后开始新增，过滤掉6行，包括 自己及前2行和后3行
            if (str.contains(STR_ONE)) {

                if (flagIdx == 0) {
                    for (int j = 0; j < i - 2; j++) {
                        stringList.add(arr[j]);
                    }
                    i += 4;
                    flagIdx = i;
                } else {
                    System.out.println("flagIdx: " + flagIdx);
                    for (int j = flagIdx; j < i - 2; j++) {
                        stringList.add(arr[j]);
                    }

                    i += 4;
                    flagIdx = i;
                }
            }
        }

//        List<String> list = Arrays.asList(arr);
//        int removeIdx;
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).contains(STR_ONE)) {
//                removeIdx = i - 2;
//                for (int j = i - 2; j < i + 3; j++) {
//                    list.remove(removeIdx);
//                }
//            }
//        }
        System.out.println(stringList);

        return stringList;
    }

    /**
     * 用来读取pdf文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readPdfByPage(String fileName) {
        String result = "";
        File file = new File(fileName);
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileName);
            // 新建一个PDF解析器对象
            PdfReader reader = new PdfReader(fileName);
            reader.setAppendable(true);
            // 对PDF文件进行解析，获取PDF文档页码
            int size = reader.getNumberOfPages();
            for (int i = 1; i < size + 1; i++) {
                //一页页读取PDF文本
                String pageStr = PdfTextExtractor.getTextFromPage(reader, i);
//                System.out.println(pageStr);
                result += pageStr + "\n";// + "PDF解析第"+ (i)+ "页\n";
//                i++;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("读取PDF文件" + file.getAbsolutePath() + "生失败！" + e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
//        System.out.println(result);
        return result;
    }
}
