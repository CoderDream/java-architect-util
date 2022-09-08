package com.coderdream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

//@Slf4j
class SubtitleUtilTest {

    /**
     * 各种字符的unicode编码的范围：
     * <pre>
     * 　    汉字：[0x4e00,0x9fa5] 或  十进制[19968,40869]
     *      数字：[0x30,0x39] 或   十进制[48, 57]
     *      小写字母：[0x61,0x7a] 或  十进制[97, 122]
     *      大写字母：[0x41,0x5a] 或  十进制[65, 90]
     *      Java中如何判断一个字符是中文字符
     *      https://blog.csdn.net/wangzhaotongalex/article/details/46738955
     * </pre>
     */
    @Test
    void merge() {
        String s = "都 是，。？！A5a12B1b";
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(SubtitleUtil.isChinese(c[i])) {
                System.out.println("汉字: " + s.charAt(i));
            } else {
                if(Character.isWhitespace(c[i])) {
                    System.out.println("空格: " + s.charAt(i));
                } else {
                    System.out.println("非汉字: " + s.charAt(i));
                }
            }
        }
    }

    @Test
    void testTrimChineseBlank() {
        String s1 = "每天 数以亿计的人依靠着信息 app";
        String s2 = "都 ";
        String s3 = " 是";
        String s4 = "都 是，。？！A5a12B1b都 是";
        String s5 = "都 是，。？！A5 a12B1b";
        System.out.println(SubtitleUtil.trimChineseBlank(s1));
        System.out.println(SubtitleUtil.trimChineseBlank(s2));
        System.out.println(SubtitleUtil.trimChineseBlank(s3));
        System.out.println(SubtitleUtil.trimChineseBlank(s4));
        System.out.println(SubtitleUtil.trimChineseBlank(s5));
    }

    @Test
    void testRemoveLastPunctuation() {
        String s1 = "And now, your Focus can also carry into the apps themselves,";
        String s2 = "a,";
        String s3 = "Tim Cook: 早上好 欢迎参加 WWDC";
        String s4 = "a,.";
        String s5 = "都 是，。？！A5 a12B1b";
        System.out.println(SubtitleUtil.removeLastPunctuation(s1));
        System.out.println(SubtitleUtil.removeLastPunctuation(s2));
        System.out.println(SubtitleUtil.removeLastPunctuation(s3));
        System.out.println(SubtitleUtil.removeLastPunctuation(s4));
        System.out.println(SubtitleUtil.removeLastPunctuation(s5));
    }


    @Test
    void mergeToOneLine() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\wwdc22\\";
        String fileName1 = absolutePath + "WWDC22 Keynote_4_text.srt";
        String fileName2 = absolutePath + "WWDC22 Keynote_4_text_new.srt";

        try {
            SubtitleUtil.mergeToOneLine(fileName1, fileName2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void merge_02() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\wwdc22\\";
        String fileName1 = absolutePath + "WWDC22 Keynote_4_text.srt";
        String fileName2 = absolutePath + "WWDC22 Keynote_6_text.srt";
        String fileNameMerge = absolutePath + "WWDC22 Keynote_text.srt";

        try {
            SubtitleUtil.merge(fileName1, fileName2, fileNameMerge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void merge_03() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\wwdc22\\";
        String fileName1 = absolutePath + "WWDC22 Keynote_1_text.srt";
        String fileName2 = absolutePath + "WWDC22 Keynote_2_text.srt";
        String fileNameMerge = absolutePath + "WWDC22 Keynote_text.srt";

        try {
            SubtitleUtil.merge(fileName1, fileName2, fileNameMerge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void merge_05() {
        // 从字符串提取出日期
        String strDate = "01:48:29,610";
        System.out.println( SubtitleUtil.formatTime(strDate));
    }


    @Test
    void mergeToAss_01() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\wwdc22\\";
        String fileName1 = absolutePath + "WWDC22 Keynote_1_text.srt";
        String fileName2 = absolutePath + "WWDC22 Keynote_2_text.srt";
        String fileNameMerge = absolutePath + "WWDC22 Keynote_text.ass";

        try {
            SubtitleUtil.mergeToAss(fileName1, fileName2, fileNameMerge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void mergeToAss_02() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\wwdc22\\";
        String fileName1 = absolutePath + "WWDC22 Keynote_4_text.srt";
        String fileName2 = absolutePath + "WWDC22 Keynote_6_text.srt";
        String fileNameMerge = absolutePath + "WWDC22 Keynote_text.ass";

        try {
            SubtitleUtil.mergeToAss(fileName1, fileName2, fileNameMerge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 苹果2022年秋季发布会
     */
    @Test
    void mergeToAss_03() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\202209\\";
        String fileName1 = absolutePath + "Apple Event, September 2022_5_text.srt";
        String fileName2 = absolutePath + "Apple Event, September 2022_9_text.srt";
        String fileNameMerge = absolutePath + "Apple Event, September 2022.ass";

        try {
            SubtitleUtil.mergeToAss(fileName1, fileName2, fileNameMerge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //


    /**
     * 苹果2022年秋季发布会
     */
    @Test
    void transferSrtToAss_01() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.endsWith("src" + File.separatorChar + "test" + File.separatorChar + "resources"));
        // Java8用流的方式读文件，更加高效
        //   Files.lines(Paths.get(absolutePath + "\\testdata\\input\\Subtitle_5.srt"), StandardCharsets.UTF_8).forEach(System.out::println);

        absolutePath += "\\testdata\\input\\202209\\";
        String srcName = absolutePath + "Apple Event, September 2022.srt";
        String destName = absolutePath + "Apple Event, September 2022.ass";

        try {
            SubtitleUtil.transferSrtToAss(srcName, destName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}