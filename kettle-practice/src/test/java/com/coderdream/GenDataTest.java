package com.coderdream;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 生成数据文件
 */
public class GenDataTest {

    private String INPUT_PATH;
    private String OUTPUT_PATH;


    private Integer RECORD_COUNT = 10000001;

    @BeforeEach
    void init() {
        System.out.println("init");
        System.out.println(System.getProperty("user.dir"));
        INPUT_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\input";
        OUTPUT_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\output";
    }

    @Test
    public void testWriteToFileIOWay() {
        try {
            File file = createFile("a");
            long startTime = System.currentTimeMillis();
            writeToFileIOWay(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Total A Time is " + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFileIOWay(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                fileOutputStream, 128 * 100);
        String messageToWrite = null;
        for (int i = 1; i < RECORD_COUNT; i++) {
            messageToWrite = i + ";" + "程序不就是0和1_" + i + "\n";
            bufferedOutputStream.write(messageToWrite.getBytes(StandardCharsets.UTF_8));
        }
        bufferedOutputStream.flush();
        fileOutputStream.close();
    }

    private File createFile(String name) throws IOException {
        return new File(OUTPUT_PATH + "\\test_" + name + ".txt");
    }

}
