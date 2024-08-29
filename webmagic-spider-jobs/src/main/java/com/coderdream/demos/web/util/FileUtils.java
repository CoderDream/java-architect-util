package com.coderdream.demos.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderDream
 */
public class FileUtils {
    public static String getResourceRealPath() {
        File directory = new File("src/main/resources");
        String reportPath = "";
        try {
            reportPath = directory.getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportPath;
    }

    public static List<String> readFileContent(String fileName) {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);//定义一个file对象，用来初始化FileReader
        FileReader reader;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                stringList.add(s.trim().replaceAll("\"","'"));
//                System.out.println(s);
            }
            // 补空行
            stringList.add("");
            bReader.close();
//            String str = sb.toString();
//            System.out.println(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }
}
