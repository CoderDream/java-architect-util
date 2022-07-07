package com.coderdream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Java8FileReader {
    public static void main(String[] args) throws IOException {
        // Java8用流的方式读文件，更加高效
        Files.lines(Paths.get("Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);
    }
}