package com.coderdream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @DisplayName("我的第一个测试")
    @Order(1)
    @Test
    void testFirstTest() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("t_user_1000.txt"), true));
            for (int i = 0; i < 10000123; i++) {
                writer.write(i + ";" + "程序不就是0和1_" + i + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("我的第一个测试开始测试");
    }

    private String FILE_PATH = System.getProperty("sys.dir");


    @Test
    public void testWritingStringToFile() {
        try {
            File file = createFile("a");
            long startTime = System.currentTimeMillis();
            writeToFileNIOWay(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Total A Time is " + (endTime - startTime));


            file = createFile("b");
            startTime = System.currentTimeMillis();
            writeToFileIOWay(file);
            endTime = System.currentTimeMillis();
            System.out.println("Total B Time is " + (endTime - startTime));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * @throws IOException IOException
     */
    public void writeToFileNIOWay(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = null;
        String messageToWrite = null;
        for (int i = 1; i < RECORD_COUNT; i++) {
            messageToWrite = i + ";" + "程序不就是0和1_" + i + "\n";
            byteBuffer = ByteBuffer.wrap(messageToWrite.getBytes(StandardCharsets.UTF_8));
            fileChannel.write(byteBuffer);
        }
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

    private Integer RECORD_COUNT = 10000001;

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
        File file = new File("test_" + name + ".txt");
        file.createNewFile();
        return file;
    }
}
