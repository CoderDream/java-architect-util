package com.coderdream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReadSrtTest {

    @DisplayName("我的第一个测试")
    @Order(1)
    @Test
    public void printSrt() throws IOException {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);
    }


}