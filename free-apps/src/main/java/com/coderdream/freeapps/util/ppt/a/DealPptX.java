package com.coderdream.freeapps.util.ppt.a;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.util.BaseUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class DealPptX {
//
//    @Autowired
//    private TextTranslationService textTranslationService;

    public static void main(String[] args) {
        System.out.println("----------单元测试开始----------");
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String todayStr = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        todayStr = DateUtil.formatChineseDate(Calendar.getInstance(), false);
        todayStr = new SimpleDateFormat("yyyy/M/d").format(new Date());
        String path = BaseUtils.getPath();
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        String filePath = File.separator + path + File.separator + dateStr + ".pptx";

        File directory = new File("src/main/resources");
        String reportPath = null;
        try {
            reportPath = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        filePath = reportPath + "/ppt/apps-12.pptx";
        String exportPath = File.separator + path + File.separator + dateStr + ".pptx";

        try {
            dealPptX(filePath, exportPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("----------单元测试结束----------");
    }

    /**
     * 处理pptX文件
     *
     * @param inputFilePath
     * @param outputFilePath
     * @throws IOException XMLSlideShow处理pttx 注意：单一文本框中的所有文字视为同一段落，文本框内不同行用 \n 分割。 文本框内分段使用 \n，在这里每个文本框视为同一段落，进行整体翻译，
     *                     所以双语对照时，跟随翻译结果进行对照，故无法进行文本框内分段对照
     */
    public static void dealPptX(String inputFilePath, String outputFilePath) throws IOException {

        FileInputStream inputStream = new FileInputStream(inputFilePath);
        FileOutputStream outputStream = new FileOutputStream(outputFilePath);

        XMLSlideShow xss = new XMLSlideShow(inputStream);

        //得到幻灯片的集合，每一个XSLFSlide代表一页幻灯片的对象
        List<XSLFSlide> pages = xss.getSlides();

        int index = 0;

        // 遍历每一张幻灯片
        for (XSLFSlide slide : pages) {
            // 计数
            index++;

            // 获得单张幻灯片内的各种对象 （包括但不限于文本框）
            // XSLFSlide.getShapes()得到XSLFShape对象，这个对象是单页幻灯片内所有对象的父类
            for (XSLFShape shape : slide.getShapes()) {

                if (ObjectUtil.isNull(shape)) {
                    continue;
                }

                // 判断 当前对象是否为文本对象 XSLFTextShape
                // 每一个XSLFTextShape代表一个文本框对象
                if (shape instanceof XSLFTextShape) {
                    // 向下转型 XSLFTextShape 获得文本
                    String text = ((XSLFTextShape) shape).getText();
                    if (StrUtil.isNotEmpty(text)) {
                        // 获取翻译结果翻译
                        String tgt_text = "B站";// doTrans(text);
                        if (StrUtil.isNotEmpty(tgt_text)) {
                            // 清空之前的文本
                            // ((XSLFTextShape) shape).clearText();
                            // 添加翻译后的文本
                            // 等同=((XSLFTextShape) shape).setText("\n"+tgt_text);
                            ((XSLFTextShape) shape).appendText("\n" + tgt_text, false);
                        }
                    }
                }
                // 判断 当前对象是否为表格对象 XSLFTable
                //每一个XSLFTable代表一个表格对象
                if (shape instanceof XSLFTable) {
                    // 向下转型为XSLFTable对象 一个表格对象
                    // 遍历行
                    for (XSLFTableRow row : ((XSLFTable) shape).getRows()) {
                        // 遍历单元格
                        for (XSLFTableCell cell : row.getCells()) {
                            // 获得文本
                            String text = cell.getText();
                            if (StrUtil.isNotEmpty(text)) {
                                String tgt_text = "bili";// doTrans(text);
                                cell.clearText();
                                cell.setText(text + "\n" + tgt_text);
                            }
                        }
                    }
                }
            }
        }
        xss.write(outputStream);
    }

//    public static String doTrans(String text){
//        TranslationParamsBo paramsBo = new TranslationParamsBo();
//        paramsBo.setFrom("zh");
//        paramsBo.setSrc_text(text);
//        paramsBo.setTo("en");
//        try {
//            // 令qps小于3
//            Thread.sleep(400);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return textTranslationService.textTranslation(paramsBo).getData().getTgt_text();
//    }

}


