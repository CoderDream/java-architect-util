package com.coderdream.freeapps.util.ppt;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

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
