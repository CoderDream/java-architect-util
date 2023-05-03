package com.coderdream.freeapps.util.ppt;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.core.io.ClassPathResource;

public class CdPptxUtilsTest {

    public static void main(String[] args) {
        // 1. 打开PPT
       // CdPptxUtils
    }

    public void process() {
        try {
            FileInputStream fis = new FileInputStream("example.pptx"); // 打开pptX文件流
            XMLSlideShow pptx = new XMLSlideShow(fis); // 创建XMLSlideShow对象


            fis.close(); // 关闭文件流
            // 处理pptX文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
