package com.coderdream.freeapps.util.ppt;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.core.io.ClassPathResource;

public class CdPptxUtils {

    public static void main(String[] args) {
        String targetPath = "result.pptx";
        CdPptxUtils.genPpt(targetPath);
    }

    public static void genPpt(String targetPath) {
        // 读取模板文件
        ClassPathResource resource = new ClassPathResource("templates/test.pptx");
        // 根据模板，创建一个新的ppt文档
        XMLSlideShow ppt;
        try {
            ppt = new XMLSlideShow(resource.getInputStream());

            // 得到每页ppt
            List<XSLFSlide> slides = ppt.getSlides();
            // 遍历ppt，填充模板
            for (int i = 0; i < slides.size(); i++) {
                // 遍历每页ppt中待填充的形状组件
                for (XSLFShape shape : slides.get(i).getShapes()) {
                    if (shape instanceof TextShape) {
                        // 替换文本
                        TextShape textShape = (TextShape) shape;
                        TextRun textRun;
                        String text = textShape.getText();
                        switch (text) {
                            case "username":
                                textRun = textShape.setText("张三");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                break;
                            case "reportDate":
                                textRun = textShape.setText("2022-10-30");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                break;
                            case "completedCnt":
                                textRun = textShape.setText("16");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                textRun.setFontColor(Color.green);
                                break;
                            case "UnCompletedCnt":
                                textRun = textShape.setText("23");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                textRun.setFontColor(Color.red);
                                break;
                            case "planDate":
                                textRun = textShape.setText("2022-11-25");
                                textRun.setFontFamily("宋体(正文)");
                                textRun.setFontSize(18.0);
                                textRun.setFontColor(Color.blue);
                                break;
                        }
                    } else if (shape instanceof PictureShape) {
                        // 替换图片
                        PictureData pictureData = ((PictureShape) shape).getPictureData();
                        pictureData.setData(FileUtils.readFileToByteArray(new File("D:\\images\\" + i + ".jpg")));
                    }
                }
            }

            // 将新的ppt写入到指定的文件中
            FileOutputStream outputStream = new FileOutputStream(targetPath);
            ppt.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
