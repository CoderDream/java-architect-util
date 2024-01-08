package com.coderdream.freeapps.util.pdf;

//import com.itextpdf.io.source.RandomAccessFileOrArray;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
//import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;

import cn.hutool.core.util.StrUtil;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderDream
 */
public class ReadPdfUtilV7 {

    public static void main(String[] args) {

        String fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\2022\\221110\\221110_controlling_the_weather.pdf";
        fileName  = "D:\\14_LearnEnglish\\6MinuteEnglish\\2022\\220428\\220428_desmond_tutu.pdf";
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

    public static List<String> getStringList(String fileName) {
        String string = readPdfByPage(fileName);
        String[] arr = string.split("\n");

//        System.out.println(arr);

        List<String> stringList = new ArrayList<>();
        boolean emptyFlag = false;
        for (String str : arr) {
            if (!str.contains(STR_ONE) && !str.contains(STR_TWO)) {
                if(StrUtil.isBlank(str)) {
                    if(!emptyFlag) {
                        stringList.add(str);
                        emptyFlag = true;
                    }
                } else {
                    stringList.add(str);
                    emptyFlag = false;
                }
            }
        }

        return stringList;
    }

    /**
     * 用来读取pdf文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
//    public static String readPdfByPage(String fileName) {
//        String result = "";
//        File file = new File(fileName);
//        FileInputStream in = null;
//        try {
//            in = new FileInputStream(fileName);
//            // 新建一个PDF解析器对象
//            PdfReader reader = new PdfReader(fileName);
//            reader.setAppendable(true);
//            // 对PDF文件进行解析，获取PDF文档页码
//            int size = reader.getNumberOfPages();
//            for (int i = 1; i < size + 1; i++) {
//                //一页页读取PDF文本
//                String pageStr = PdfTextExtractor.getTextFromPage(reader, i);
////                System.out.println(pageStr);
//                result += pageStr + "\n";// + "PDF解析第"+ (i)+ "页\n";
////                i++;
//            }
//            reader.close();
//        } catch (Exception e) {
//            System.out.println("读取PDF文件" + file.getAbsolutePath() + "生失败！" + e);
//            e.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
////        System.out.println(result);
//        return result;
//    }

    private static String readPdfByPage(String file) {
        PdfReader pr = null;
        try {
            pr = new PdfReader(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PdfDocument doc = new PdfDocument(pr);
        int num = doc.getNumberOfPages();

        String result = "";
        for (int i = 1; i <= num; i++) {
            String str = PdfTextExtractor.getTextFromPage(doc.getPage(i));
            System.out.println(str);
            result += str;
        }

        return result;
    }
}
