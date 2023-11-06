package com.coderdream.freeapps.util.video.ffmpeg;


import cn.hutool.core.util.IdUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.bytedeco.javacpp.Loader;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @Program: csdn @ClassName: CutOutTool @Author: 剑客阿良_ALiang @Date: 2022-01-23 18:27 @Description:
 * 裁剪工具 @Version: V1.0
 */
public class CutOutTool {

    /**
     * 视频裁剪
     *
     * @param videoPath 视频地址
     * @param outputDir 临时目录
     * @param startX 裁剪起始x坐标
     * @param startY 裁剪起始y坐标
     * @param weight 裁剪宽度
     * @param height 裁剪高度
     * @throws Exception 异常
     */
    public static String cutOutVideo(
        String videoPath,
        String outputDir,
        Integer startX,
        Integer startY,
        Integer weight,
        Integer height)
        throws Exception {
        List<String> paths = Splitter.on(".").splitToList(videoPath);
        String ext = paths.get(paths.size() - 1);
        if (!Arrays.asList("mp4", "avi", "flv").contains(ext)) {
            throw new Exception("format error");
        }
        String resultPath =
            Joiner.on(File.separator).join(Arrays.asList(outputDir, IdUtil.simpleUUID() + "." + ext));
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        ProcessBuilder builder =
            new ProcessBuilder(
                ffmpeg,
                "-i",
                videoPath,
                "-vf",
                MessageFormat.format(
                    "crop={0}:{1}:{2}:{3}",
                    String.valueOf(weight),
                    String.valueOf(height),
                    String.valueOf(startX),
                    String.valueOf(startY)),
                "-b",
                "2000k",
                "-y",
                "-threads",
                "5",
                "-preset",
                "ultrafast",
                "-strict",
                "-2",
                resultPath);
        builder.inheritIO().start().waitFor();
        return resultPath;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
            cutOutVideo("C:\\Users\\yi\\Desktop\\3.mp4", "C:\\Users\\yi\\Desktop\\", 0, 0, 960, 1080));
    }
}

