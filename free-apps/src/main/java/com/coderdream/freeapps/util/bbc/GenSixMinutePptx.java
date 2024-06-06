package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.util.FileUtils;
import com.coderdream.freeapps.util.ppt.CdPptxUtils;
import io.gitee.jinceon.core.DataSource;
import io.gitee.jinceon.core.SimpleEngine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.springframework.util.ObjectUtils;

/**
 * @author CoderDream
 */
public class GenSixMinutePptx {

    public static void main(String[] args) {
//        String targetPath = "result.pptx";-
        String folderName = "230119";
        String titleName = "拜访制作香精的人";

        folderName = "221027";
        titleName = "万圣节服装太吓人了吗？";
        System.out.println(folderName.substring(3));
        GenSixMinutePptx.genPpt(folderName, titleName);
    }

    /**
     * 分多步生成ppt
     *
     * @param folderName
     * @param titleName
     */
    public static void genPpt(String folderName, String titleName) {
        // 第一步：设置标题图片
        String titleTemplateFileName = fillTitlePicture(folderName);
        // 第二步：设置标题，填充单词
        fillContent(folderName, titleTemplateFileName, titleName);

        // 删除temp文件
        File file = new File(titleTemplateFileName);
        if (file.isFile() & file.exists()) {
            file.delete();
            System.out.println("删除temp文件");
        }
    }

    /**
     * 替换主题图片
     *
     * @param folderName
     */
    public static String fillTitlePicture(String folderName) {
        String pptxFileName = BbcConstants.PPT_TEMPLATE;// "6min.pptx";

//        String titleImage = folderName + ".jpg";
        String titleImageWithPath = CommonUtil.getFullPathFileName(folderName, folderName, ".jpg"); //

        // 新ppt文件名
        String pptxFileNameNew = CommonUtil.getFullPathFileName(folderName, folderName, "_temp.pptx");

        try {
            // 读取模板文件
            FileInputStream fis = new FileInputStream(CdPptxUtils.getTemplatePath() + pptxFileName);
            // 根据模板，创建一个新的ppt文档
            XMLSlideShow ppt = new XMLSlideShow(fis);
            //获取图片信息
            List pictureDateList = ppt.getPictureData();
            if (!ObjectUtils.isEmpty(pictureDateList)) {
                int size = pictureDateList.size();
//                System.out.println("PictureData: " + size);
                if (pictureDateList.size() > 1) {
                    XSLFPictureData picture = (XSLFPictureData) pictureDateList.get(1);
                    picture.setData(FileUtils.readFileToByteArray(new File(titleImageWithPath)));
                }
            }

            // 将新的ppt写入到指定的文件中
            FileOutputStream outputStream = new FileOutputStream(pptxFileNameNew);
            ppt.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pptxFileNameNew;
    }

    public static void fillContent(String folderName, String templateFileName, String titleName) {
        // 1. create engine instance 创建引擎
        SimpleEngine engine = new SimpleEngine(templateFileName);

        // 2. add data to dataSource 填充数据
        DataSource dataSource = new DataSource();
        Title title = new Title(titleName, titleName, folderName);
        Map props = new HashMap(); // 末日滚动：我们为什么喜欢末日滚动？
        props.put("titleName", titleName);
        if ("230209".equals(folderName)) {
            props.put("titleName", "我们为什么喜欢末日滚动？");
        }

        List<VocInfo> vocInfoList = DictUtils.getVocInfoList(folderName);
        int i = 1;
        String sampleSentenceEn;
        String sampleSentenceCn;
        for (VocInfo vocInfo : vocInfoList) {
//            System.out.println(vocInfo);
            props.put("word" + i, vocInfo.getWord());
            props.put("wordEn" + i,
                vocInfo.getWordExplainEn());
            props.put("wordCn" + i, vocInfo.getWordCn());
            props.put("wordCnEx" + i, vocInfo.getWordExplainCn());
            sampleSentenceEn = vocInfo.getSampleSentenceEn();
            sampleSentenceCn = vocInfo.getSampleSentenceCn();
            String mid = "";
            System.out.println(sampleSentenceEn + " : " + sampleSentenceEn.length());
            if (StrUtil.isNotEmpty(sampleSentenceEn) && sampleSentenceEn.length() < 50) {
                mid = "\n";
            }

            props.put("wordSen" + i, sampleSentenceEn + mid + sampleSentenceCn);
            i++;
        }

        dataSource.setVariable("title", title);
        props.put("secretCode", folderName);
        dataSource.setVariable("props", props);
        engine.setDataSource(dataSource);

        // 3. render data to template 将数据渲染到模板上
        engine.process();

        // 4. save result
        // 新ppt文件名
        String pptxFileNameNew = CommonUtil.getFullPathFileName(folderName, folderName, ".pptx");
        engine.save(pptxFileNameNew);

        // 生成待填充到《核心词汇表》的文件
        DictUtils.writeVocCnExcel(folderName);
    }
}
