package com.coderdream.freeapps.util.video.ffmpeg.v1;

import com.coderdream.freeapps.util.video.ffmpeg.v1.domain.ImageMetaInfo;
import com.coderdream.freeapps.util.video.ffmpeg.v1.domain.MusicMetaInfo;
import com.coderdream.freeapps.util.video.ffmpeg.v1.domain.VideoMetaInfo;
import com.coderdream.freeapps.util.video.ffmpeg.v1.domain.gif.AnimatedGifEncoder;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 基于FFmpeg内核来编解码音视频信息； 使用前需手动在运行环境中安装FFmpeg运行程序，然后正确设置FFmpeg运行路径后MediaUtil.java才能正常调用到FFmpeg程序去处理音视频；
 *
 * @author: dreamer-1
 * <p>
 * version: 1.0
 */
@Slf4j
public class MediaUtilTest {

    public static void main(String[] args) {
//        MediaUtil.isExecutable();
        String videoFileName = "C:\\Users\\CoderDream\\Downloads2\\德云社相声2023.De.Yun.Society.2023.1080p.WEB-DL.H264.AAC-OurTV\\德云社尚九熙郭霄汉相声专场上海站.De.Yun.Society.20230703.1080p.WEB-DL.H264.AAC-OurTV.mp4";
//        VideoMetaInfo videoMetaInfo = MediaUtil.getVideoMetaInfo(new File(videoFileName));
//        System.out.println(videoMetaInfo);
        videoFileName = "D:\\202307082232.mp4";
        File videoFile = new File(videoFileName);

        int timeLength = 14;
        String outputFileA = "C:\\Users\\CoderDream\\Downloads2\\德云社相声2023.De.Yun.Society.2023.1080p.WEB-DL.H264.AAC-OurTV\\德云社尚九熙郭霄汉相声专场上海站.De.Yun.Society.20230703.1080p.WEB-DL.H264.AAC-OurTV_A.mp4";
        outputFileA = "D:\\202307082232_" + timeLength + ".mp4";
        File outputFile = new File(outputFileA);
        Time startTime = new Time(0, 0, 0);
        MediaUtil.cutVideo(videoFile, outputFile, startTime, timeLength);
    }

}
