package com.coderdream.freeapps.util.video.ffmpeg;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author qing-feng.zhao
 */
public class SmartFileUtils {

    /**
     * * 截取视频第六帧的图片
     *
     * @param filePath 视频路径
     * @param dir      文件存放的根目录
     * @return 图片的相对路径 例：pic/1.png
     */
    public static String videoImage(String filePath, String dir) throws FrameGrabber.Exception {
        String pngPath = "";
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.setFormat("mp4");
        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            //截取第6帧
            if ((i > 5) && (f.image != null)) {
                //生成图片的相对路径 例如：pic/uuid.png
                pngPath = getPngPath();
                File pathDir = new File(pngPath);
                if(!pathDir.exists()) {
                    pathDir.mkdirs();
                }

                //执行截图并放入指定位置
                doExecuteFrame(f, dir + pngPath);
                break;
            }
            i++;
        }
        ff.stop();

        return pngPath;
    }

    /**
     * 生成图片的相对路径
     *
     * @return 图片的相对路径 例：pic/1.png
     */
    private static String getPngPath() {
        return "pic/" + getUUID() + ".png";
    }


    /**
     * 生成唯一的uuid
     *
     * @return uuid
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 截取缩略图
     *
     * @param f                       Frame
     * @param targetFilePath:封面图片存放路径
     */
    private static void doExecuteFrame(Frame f, String targetFilePath) {
        String imageFormat = "png";
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targetFilePath);
        try {
            ImageIO.write(bi, imageFormat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
