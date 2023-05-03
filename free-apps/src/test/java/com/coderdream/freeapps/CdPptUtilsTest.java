package com.coderdream.freeapps;

import cn.hutool.core.date.DateUtil;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.TopPriceService;
import com.coderdream.freeapps.util.BaseUtils;
import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import com.coderdream.freeapps.util.ppt.pptutil.PPTUtil;
import com.coderdream.freeapps.util.ppt.pptutil.entity.ParagraphTextStyle;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

@SpringBootTest
public class CdPptUtilsTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CdPptUtilsTest.class);

    @Test
    public void testDemo5() {
        PPTUtil pptUtil = getPptUtil();
        // 获取该幻灯片内的所有段落
        List<XSLFTextParagraph> paragraphs = pptUtil.getParagraphsFromSlide(pptUtil.getSlides().get(0));
        for (XSLFTextParagraph paragraph : paragraphs) {
            if (!"".equals(paragraph.getText())) {  // 如果该段落文本不为空，则打印文本
                System.out.println(paragraph.getText());
            }
        }
    }

    @NotNull
    private static PPTUtil getPptUtil() {
        File directory = new File("src/main/resources");
        String reportPath = null;
        try {
            reportPath = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String filePath = reportPath + "/ppt/apps-12.pptx";

//        String filePath = "C:\\Users\\HONOR\\Downloads\\1-201216124221\\公司简介.pptx";
        PPTUtil pptUtil = new PPTUtil(filePath);    // 读取 ppt 模板
        return pptUtil;
    }


    @Test
    public void testDemo6() {
//        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String todayStr = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        todayStr = DateUtil.formatChineseDate(Calendar.getInstance(),false);
        todayStr = new SimpleDateFormat("yyyy/M/d").format(new Date());
        String path = BaseUtils.getPath();
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        String pptFileName = File.separator + path + File.separator + dateStr + ".pptx";
        Integer totalPrice = 158;
        // 构造数据
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("aa", todayStr);
        keyMap.put("bb", totalPrice);

        PPTUtil pptUtil = getPptUtil();
        List<XSLFTextParagraph> paragraphs = pptUtil.getParagraphsFromSlide(
            pptUtil.getSlides().get(0));    // 获取该幻灯片内所有段落
        for (XSLFTextParagraph paragraph : paragraphs) {
            pptUtil.replaceTagInParagraph(paragraph, keyMap);   // 对该段落中所有标签 {**} 进行替换
        }
        pptUtil.writePPT(pptFileName);
    }


    /**
     * 追加新的文本
     */
    @Test
    public void testDemo8() {
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String todayStr = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        todayStr = DateUtil.formatChineseDate(Calendar.getInstance(),false);
        todayStr = new SimpleDateFormat("yyyy/M/d").format(new Date());
        String path = BaseUtils.getPath();
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        String filePath = CdPptxUtils.getTemplateOne();
        String exportPath = File.separator + path + File.separator + dateStr + ".pptx";
//        String filePath = "C:\\Users\\HONOR\\Desktop\\xsmb.pptx";
//        String exportPath = "C:\\Users\\HONOR\\Desktop\\1.pptx";
        PPTUtil pptUtil = new PPTUtil(filePath);
        List<XSLFTextParagraph> paragraphs = pptUtil.getParagraphsFromSlide(pptUtil.getSlides().get(3));

        ParagraphTextStyle pts = new ParagraphTextStyle();
        pts.setColorHex("#ff0000");
        pts.setFontFamily("微软雅黑");


        pptUtil.addParagraphText(paragraphs.get(paragraphs.size() - 1), false, "新的文本", pts);  // 在最后一个段落后面添加新的文本，并利用文本样式对象来设置文本的属性

        pptUtil.addParagraphText(paragraphs.get(paragraphs.size() - 1), true, "字体加粗", true);    // 添加文本，并设置加粗文本

        // 或者先添加文本，再对返回的文本对象进行属性的设置
        XSLFTextRun textRun = pptUtil.addParagraphText(paragraphs.get(paragraphs.size() - 1), true, "下划线文本");
        pptUtil.setTextUnderline(textRun, true);

        pptUtil.writePPT(exportPath);
    }

}
