package com.coderdream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class SubtitleUtilTest {

    String INPUT_PATH = "D:\\data";
    String OUTPUT_PATH = "D:\\data";

    @BeforeEach
    void init(){
        System.out.println("init");
        System.out.println(System.getProperty("user.dir"));
        INPUT_PATH=  System.getProperty("user.dir")  + "\\src\\test\\resources\\data\\input";
        OUTPUT_PATH=  System.getProperty("user.dir")  + "\\src\\test\\resources\\data\\output";
    }

    @Test
    void merge() throws IOException {
        // 1、本地文件列表
        List<File> files = new ArrayList<>();
        // String path = "D:\\JAVA";		//要遍历的路径
        File filePath = new File(INPUT_PATH);        //获取其file对象
        File[] fileArray = filePath.listFiles();    //遍历path下的文件和目录，放在File数组中
        for (File tempFile : fileArray) {                    //遍历File[]数组
            if (!tempFile.isDirectory()) {        //若非目录(即文件)，则打印
                files.add(tempFile);// //   System.out.println(f);
                String shortFileName = tempFile.getName();
                System.out.println(tempFile);
//                String sourceFileName = "";
                String distFileName = OUTPUT_PATH + File.separatorChar + shortFileName;
                SubtitleUtil.mergeToOneLine(tempFile.toString(), distFileName);
            }
        }
        // 检查需要下载多文件列表中文件路径是否都存在
        for (File fileTemp : files) {
            if (!fileTemp.exists()) {
                // 需要下载的文件中存在不存在地址
                return;
            }
        }

        System.out.println("");
    }

    @Test
    void mergeToOneLine() {
        System.out.println("dxper.net".indexOf(".") + 115 % 50);

    }
}