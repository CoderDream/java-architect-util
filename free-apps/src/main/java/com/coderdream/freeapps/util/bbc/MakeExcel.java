package com.coderdream.freeapps.util.bbc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.coderdream.freeapps.model.WordInfo;
import com.coderdream.freeapps.util.easyexcel.TestFileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author CoderDream
 */
public class MakeExcel {

    public static void main(String[] args) {
        String templateFileName =
            TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "词汇.xlsx";

        // 方案1 一下子全部放到内存里面 并填充
        String excelFileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";

        List<WordInfo> wordInfoList = data1();// process(folderName, fileName);
        List<WordInfo> wordInfoList2 = data2();// process(folderName, fileName);
        String sheetName = "词汇表";
        String sheetName2 = "其他";
        MakeExcel.listFill(templateFileName, excelFileName, sheetName, wordInfoList);
    }

    /**
     * https://blog.csdn.net/hunagzheng123456/article/details/103272656
     *
     * @param templateFileName
     * @param fileName
     * @param sheetName
     * @param wordInfoList
     */
    public static void listFill(String templateFileName, String fileName, String sheetName,
        List<WordInfo> wordInfoList) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
        // 如果填充list的对象是map,必须包涵所有list的key,哪怕数据为null，必须使用map.put(key,null)

        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontName("微软雅黑");
        contentWriteFont.setFontHeightInPoints((short) 11);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);

        //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);//细实线
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
            new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        EasyExcel.write(fileName).withTemplate(templateFileName).registerWriteHandler(horizontalCellStyleStrategy)
            .sheet(sheetName).doFill(wordInfoList);

//        EasyExcel.write(fileName).withTemplate(templateFileName).registerWriteHandler(horizontalCellStyleStrategy)
//            .sheet(sheetName1).doFill(wordInfoList1);
//        EasyExcel.write(fileName).withTemplate(templateFileName).registerWriteHandler(horizontalCellStyleStrategy)
//            .sheet(sheetName2).doFill(wordInfoList2);
    }


    public static void listFill(String templateFileName, String fileName, String sheetName1,
        List<WordInfo> wordInfoList1, String sheetName2, List<WordInfo> wordInfoList2, String sheetName3,
        List<WordInfo> wordInfoList3, String sheetName4, List<WordInfo> wordInfoList4) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
        // 如果填充list的对象是map,必须包涵所有list的key,哪怕数据为null，必须使用map.put(key,null)

        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontName("微软雅黑");
        contentWriteFont.setFontHeightInPoints((short) 11);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);

        //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);//细实线
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
            new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

//        EasyExcel.write(fileName).withTemplate(templateFileName).registerWriteHandler(horizontalCellStyleStrategy)
//            .sheet(sheetName).doFill(wordInfoList);

//        EasyExcel.write(fileName).withTemplate(templateFileName).registerWriteHandler(horizontalCellStyleStrategy)
//            .sheet(sheetName1).doFill(wordInfoList1);
//        EasyExcel.write(fileName)ExcelWriter excelWriter = null;
//  try {
//  	excelWriter = EasyExcel.write(output).withTemplate(templateInputStream).build();
//         WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
//         excelWriter.fill(data, writeSheet);
//
//         WriteSheet writeSheet2 = EasyExcel.writerSheet("sheet2").build();
//         excelWriter.fill(data2, writeSheet2);
//  } finally {
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//  }

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName)
                .registerWriteHandler(horizontalCellStyleStrategy).build();
            WriteSheet writeSheet1 = EasyExcel.writerSheet(sheetName1).build();
            excelWriter.fill(wordInfoList1, writeSheet1);

            WriteSheet writeSheet2 = EasyExcel.writerSheet(sheetName2).build();
            excelWriter.fill(wordInfoList2, writeSheet2);

            WriteSheet writeSheet3 = EasyExcel.writerSheet(sheetName3).build();
            excelWriter.fill(wordInfoList3, writeSheet3);

            WriteSheet writeSheet4 = EasyExcel.writerSheet(sheetName4).build();
            excelWriter.fill(wordInfoList4, writeSheet4);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * EasyExcel 填充报表
     *
     * @param response
     * @param sheetAndDataMap key:sheet页，value:填充的list集合
     * @param map             填充单个的值
     * @param filename        文件名
     * @param inputStream     文件流.
     */
    public static void fillReportWithEasyExcel(HttpServletResponse response, Map<String, List<?>> sheetAndDataMap,
        Map<String, String> map, String filename, InputStream inputStream) {
        ExcelWriter excelWriter = null;
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
            for (Map.Entry<String, List<?>> entry : sheetAndDataMap.entrySet()) {
                List<?> value = entry.getValue();
                WriteSheet writeSheet = EasyExcel.writerSheet(Integer.valueOf(entry.getKey())).build();
                FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
                excelWriter.fill(value, fillConfig, writeSheet);
                excelWriter.fill(map, writeSheet);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excelWriter.finish();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<WordInfo> data() {
        List<WordInfo> list = ListUtils.newArrayList();
        WordInfo fillData1 = new WordInfo("a", "[ə]", "[ə; eɪ]",
            "n. 字母A；第一流的；学业成绩达最高标准的评价符号；abbr. [物]安（ampere）", "C01", "C01", 12);
        WordInfo fillData2 = new WordInfo("an", "[ən]", "[ən; æn]", "art. 一（在元音音素前）", "C01", "C01", 15);
        WordInfo fillData3 = new WordInfo("ability", "[əˈbɪləti]", "[əˈbɪləti]", "n. 能力，能耐；才能", "C01", "C01", 16);
        WordInfo fillData4 = new WordInfo("able", "[ˈeɪb(ə)l]", "[ˈeɪbl]", "adj. 能；[经管] 有能力的；能干的", "C01",
            "C01", 41);
        WordInfo fillData5 = new WordInfo("about", "[əˈbaʊt]", "[əˈbaʊt]",
            "prep. 关于；大约；n. 大致；粗枝大叶；不拘小节的人；adj. 在附近的；四处走动的；在起作用的；adv. 大约；周围；到处",
            "C01", "C01", 12);
        list.add(fillData1);
        list.add(fillData2);
        list.add(fillData3);
        list.add(fillData4);
        list.add(fillData5);

        return list;
    }

    public static List<WordInfo> data1() {
        List<WordInfo> list = ListUtils.newArrayList();
        WordInfo fillData1 = new WordInfo("a", "[ə]", "[ə; eɪ]",
            "n. 字母A；第一流的；学业成绩达最高标准的评价符号；abbr. [物]安（ampere）", "C01", "C01", 12);
        WordInfo fillData2 = new WordInfo("an", "[ən]", "[ən; æn]", "art. 一（在元音音素前）", "C01", "C01", 15);
        WordInfo fillData3 = new WordInfo("ability", "[əˈbɪləti]", "[əˈbɪləti]", "n. 能力，能耐；才能", "C01", "C01", 16);
        WordInfo fillData4 = new WordInfo("able", "[ˈeɪb(ə)l]", "[ˈeɪbl]", "adj. 能；[经管] 有能力的；能干的", "C01",
            "C01", 41);
        WordInfo fillData5 = new WordInfo("about", "[əˈbaʊt]", "[əˈbaʊt]",
            "prep. 关于；大约；n. 大致；粗枝大叶；不拘小节的人；adj. 在附近的；四处走动的；在起作用的；adv. 大约；周围；到处",
            "C01", "C01", 12);
        list.add(fillData1);
        list.add(fillData2);
        list.add(fillData3);

        return list;
    }

    public static List<WordInfo> data2() {
        List<WordInfo> list = ListUtils.newArrayList();
        WordInfo fillData1 = new WordInfo("a", "[ə]", "[ə; eɪ]",
            "n. 字母A；第一流的；学业成绩达最高标准的评价符号；abbr. [物]安（ampere）", "C01", "C01", 12);
        WordInfo fillData2 = new WordInfo("an", "[ən]", "[ən; æn]", "art. 一（在元音音素前）", "C01", "C01", 15);
        WordInfo fillData3 = new WordInfo("ability", "[əˈbɪləti]", "[əˈbɪləti]", "n. 能力，能耐；才能", "C01", "C01", 16);
        WordInfo fillData4 = new WordInfo("able", "[ˈeɪb(ə)l]", "[ˈeɪbl]", "adj. 能；[经管] 有能力的；能干的", "C01",
            "C01", 41);
        WordInfo fillData5 = new WordInfo("about", "[əˈbaʊt]", "[əˈbaʊt]",
            "prep. 关于；大约；n. 大致；粗枝大叶；不拘小节的人；adj. 在附近的；四处走动的；在起作用的；adv. 大约；周围；到处",
            "C01", "C01", 12);
        list.add(fillData4);
        list.add(fillData5);

        return list;
    }
}
