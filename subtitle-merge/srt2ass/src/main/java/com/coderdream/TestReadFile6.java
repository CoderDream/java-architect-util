package com.coderdream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestReadFile6 {
    public static void main(String[] args) throws IOException {
        if (!Files.exists(Paths.get("test.txt"))) Files.write(Paths.get("test.txt"), new byte[0]);
        if (!Files.exists(Paths.get("newtest.txt"))) Files.write(Paths.get("newtest.txt"), new byte[0]);
        try (
                FileInputStream fis = new FileInputStream("test.txt");
                FileOutputStream fos = new FileOutputStream("newtest.txt");
        ) {
            if (Files.readAllLines(Paths.get("test.txt")).size() == 0) {
                Files.write(Paths.get("test.txt"), ("first write------" + System.currentTimeMillis()).getBytes());
            } else {
                //一行代码写入文件.
                Files.write(Paths.get("test.txt"), ("more write------" + System.currentTimeMillis()).getBytes());
            }
            int result = 0;
            byte[] by = new byte[1024];
            while ((result = fis.read(by)) != -1) {
                fos.write((new String(by) + "---" + System.currentTimeMillis()).getBytes());
            }
            /*
             * java 8 一行代码读取文件内容
               *
               1）文件可能很大，可能会超出内存空间，使用前要做评估。
               2）要输出日志，记录为什么无法读取文件或者在阅读文件时遇到的任何错误。
               3）在把字节转换成字符时，应该指定字符编码。
               4）要处理文件不存在的情况。
               还要注意，如果读入的文件的编码是ANSI编码，那么上面的例子在读取文件内容时会报java.nio.charset.MalformedInputException: Input length = 1错误。
             *
             */
            Files.readAllLines(Paths.get("newtest.txt"), Charset.defaultCharset()).forEach(t -> System.out.println("newtest.txt:" + t));
        }
    }
}