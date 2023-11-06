package com.coderdream.freeapps.util.video.ffmpeg;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FrameGrabber;
import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class SmartFileUtilsTest {

    private String testPath;

//    @BeforeClass
//    void setUp() {
//        testPath = "/Users/zhaoqingfeng/Documents/temp/test.mp4";
//        //testPath="https://v.qq.com/txp/iframe/player.html?vid=q0024wtk0v8";
//    }
//
//    @After
//    void tearDown() {
//    }

    @Test
    public void testVideoImage() {
        String fileNamePath= "D:\\hls\\lpl.mp4";
        testPath= "D:\\hls\\";
        try {
            String filePath = SmartFileUtils.videoImage(fileNamePath, testPath);
            log.info("{}", filePath);
        } catch (FrameGrabber.Exception e) {
            log.error("出错", e);
        }
    }
}
