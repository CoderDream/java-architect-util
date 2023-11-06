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
 * @Program: csdn @ClassName: CutOutTool @Author: 剑客阿良_ALiang @Date: 2022-01-23 18:27 @Description: 裁剪工具 @Version: V1.0
 */
public class CutPictureOutTool {

    /**
     * 图片裁剪
     *
     * @param imagePath 图片地址
     * @param outputDir 临时目录
     * @param startX    裁剪起始x坐标
     * @param startY    裁剪起始y坐标
     * @param weight    裁剪宽度
     * @param height    裁剪高度
     * @throws Exception 异常
     */
    public static String cutOutImage(String imagePath, String outputDir, Integer startX, Integer startY, Integer weight,
        Integer height) throws Exception {
        List<String> paths = Splitter.on(".").splitToList(imagePath);
        String ext = paths.get(paths.size() - 1);
        if (!Arrays.asList("png", "jpg").contains(ext)) {
            throw new Exception("format error");
        }
        String resultPath = Joiner.on(File.separator).join(Arrays.asList(outputDir, IdUtil.simpleUUID() + "." + ext));
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        ProcessBuilder builder = new ProcessBuilder(ffmpeg, "-i", imagePath, "-vf",
            MessageFormat.format("crop={0}:{1}:{2}:{3}", String.valueOf(weight), String.valueOf(height),
                String.valueOf(startX), String.valueOf(startY)), "-y", resultPath);
        builder.inheritIO().start().waitFor();
        return resultPath;
    }

    public static void main(String[] args) throws Exception {

        String inputImagePath = "D:\\71_PS_Lesson\\生日图片.png";
        String outputImagePath = "D:\\71_PS_Lesson\\生日图片_1920_1080.png";
//        int x = (1200 - 1080) / 2;
//        int y = 0;
//        int width = 1920;
//        int height = 1080;
//        imageCut( inputImagePath,  outputImagePath,  x,  y,  width,  height);

        String imagePath = "D:\\088_Workshop\\20230615.jpg";
        imagePath = "D:\\71_PS_Lesson\\生日图片_1920.png";
        String outputDir = "D:\\088_Workshop\\";
        outputDir = "D:\\71_PS_Lesson\\";
        Integer startX = (1200-1080)/2;
        Integer startY = 0;
        Integer weight = 1920;
        Integer height = 1080;
        System.out.println(cutOutImage(imagePath, outputDir, startX, startY, weight, height));
//        System.out.println(
//            cutOutImage("C:\\Users\\yi\\Desktop\\2054011.jpg", "C:\\Users\\yi\\Desktop\\", 0, 0, 1920, 2160));
    }
}

