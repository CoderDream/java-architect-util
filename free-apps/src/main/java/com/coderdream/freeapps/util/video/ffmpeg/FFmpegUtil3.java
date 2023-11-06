package com.coderdream.freeapps.util.video.ffmpeg;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;


/**
 * 视频处理工具
 * Java使用javacv获取视频时长以及截取帧图片
 * https://blog.csdn.net/Linchack/article/details/105764245
 *
 * @author layduo
 */
public class FFmpegUtil3 {

    public static void main(String[] args) throws IOException {
//        //获取文件存放目录
////        String uploadpath = Global.getUploadPath();
//        String filepath = "D:\\BaiduNetdiskDownload\\零基础学PS，30堂课从入门到精通（完结）\\视频课\\第01堂课：课程须知 成为PS高手的第一步.MP4";// uploadpath + File.separator + "视频.mp4";
//
//        File file = new File(filepath);
//        System.out.println("视频时长：" + formatDuration(videoDuration(file)));
//
//        System.out.println("===============================================");
//
//        //为了避免截取图片全黑情况，截取位置应该大于5，看具体情况而定
////        videoImage(file, 100, uploadpath);

        String folderPath = "D:\\BaiduNetdiskDownload\\零基础学PS，30堂课从入门到精通（完结）\\视频课\\";
        countDirDuration(folderPath); // 视频时长：07:42:12
    }

    /**
     *
     * @param folderPath
     */
    public static void countDirDuration(String folderPath) {
        //获取文件夹下的所有文件名
        List<String> allFileNames = CdFileUtils.getAllFileNames(folderPath);
        Long total = 0L;
        if (CollectionUtils.isNotEmpty(allFileNames)) {
            for (String fileName : allFileNames) {
                total += videoDuration(new File(fileName));
            }
        }
        System.out.println("视频时长：" + formatDuration(total));

        System.out.println("===============================================");

        //为了避免截取图片全黑情况，截取位置应该大于5，看具体情况而定
//        videoImage(file, 100, uploadpath);
    }

    private static final String IMAGE_SUFFIX = "png";

    /**
     * 获取视频时长，单位为秒S
     *
     * @param file 视频源
     * @return
     */
    @SuppressWarnings("resource")
    public static long videoDuration(File file) {
        long duration = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
        try {
            ff.start();
            duration = ff.getLengthInTime() / (1000 * 1000);
            ff.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

    /**
     * 获取视频帧图片
     *
     * @param file   视频源
     * @param number 第几帧
     * @param dir    文件存放根目录
     * @param args   文件存放根目录
     * @return
     */
    @SuppressWarnings("resource")
    public static String videoImage(File file, Integer number, String dir, Object... args) {
        String picPath = "";// StringUtils.EMPTY;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
        try {
            ff.start();
            int i = 0;
            int length = ff.getLengthInFrames();
            Frame frame;
            while (i < length) {
                frame = ff.grabFrame();
                //截取第几帧图片
                if ((i > number) && (frame.image != null)) {
                    //获取生成图片的路径
                    picPath = getImagePath(args);
                    //执行截图并放入指定位置
                    doExecuteFrame(frame, dir + File.separator + picPath);
                    break;
                }
                i++;
            }
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return picPath;
    }

    /**
     * 截取缩略图
     *
     * @param frame
     * @param targerFilePath 图片存放路径
     */
    public static void doExecuteFrame(Frame frame, String targerFilePath) {
        //截取的图片
        if (null == frame || null == frame.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage srcImage = converter.getBufferedImage(frame);
        int srcImageWidth = srcImage.getWidth();
        int srcImageHeight = srcImage.getHeight();
        //对帧图片进行等比例缩放（缩略图）
        int width = 480;
        int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
        BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        thumbnailImage.getGraphics()
            .drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        File output = new File(targerFilePath);
        try {
            ImageIO.write(thumbnailImage, IMAGE_SUFFIX, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成图片的相对路径
     *
     * @param args 传入生成图片的名称、为空则用UUID命名
     * @return 例如 upload/images.png
     */
    public static String getImagePath(Object... args) {
        if (CollectionUtils.isNotEmpty(Arrays.asList(args))) {
            return String.valueOf(args[0]) + "." + IMAGE_SUFFIX;
        }
        return getUUID() + "." + IMAGE_SUFFIX;
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
     * 时长格式换算
     *
     * @param duration 时长
     * @return HH:mm:ss
     */
    public static String formatDuration(Long duration) {
        String formatTime = "";// StringUtils.EMPTY;
        double time = Double.valueOf(duration);
        if (time > -1) {
            int hour = (int) Math.floor(time / 3600);
            int minute = (int) (Math.floor(time / 60) % 60);
            int second = (int) (time % 60);

            if (hour < 10) {
                formatTime = "0";
            }
            formatTime += hour + ":";

            if (minute < 10) {
                formatTime += "0";
            }
            formatTime += minute + ":";

            if (second < 10) {
                formatTime += "0";
            }
            formatTime += second;
        }
        return formatTime;
    }

}
