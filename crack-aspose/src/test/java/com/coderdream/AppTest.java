package com.coderdream;

//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;

//import org.junit.Test;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    String sourceFile = "D:\\2班成绩.xlsx";//输入的文件
    String targetFile = "D:\\2班成绩.pdf";//输出的文件


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void check() {

        assertTrue(true);
    }

    @Test
    void crack() {
    }

    @Test
    void excelToPdf() {
        App.excelToPdf(sourceFile, targetFile);
    }
}
