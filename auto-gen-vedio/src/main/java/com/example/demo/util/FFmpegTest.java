package com.example.demo.util;


import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 诸葛小猿
 * @date 2020-10-24
 */
public class FFmpegTest {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String timeStr = simpleFormatter.format(System.currentTimeMillis());
        //合成的MP4
        String mp4SavePath = "D:\\temp\\" + timeStr + ".mp4";
        //图片地址 这里面放了22张图片
        String img = "D:\\temp\\mac";
        int width = 1600;
        int height = 900;
        //读取所有图片
        File file = new File(img);
        File[] files = file.listFiles();
        Map<Integer, File> imgMap = new HashMap<Integer, File>();
        int num = 0;
        for (File imgFile : files) {
            imgMap.put(num, imgFile);
            num++;
        }
        createMp4(mp4SavePath, imgMap, width, height);
    }

    private static void createMp4(String mp4SavePath, Map<Integer, File> imgMap, int width, int height)
            throws FrameRecorder.Exception {
        //视频宽高最好是按照常见的视频的宽高  16：9  或者 9：16
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(mp4SavePath, width, height);
        //设置视频编码层模式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        //设置视频为25帧每秒
        recorder.setFrameRate(25);
        //设置视频图像数据格式
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setFormat("mp4");
        try {
            recorder.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            //录制一个22秒的视频
            for (int i = 0; i < imgMap.size(); i++) {
                BufferedImage read = ImageIO.read(imgMap.get(i));
                //一秒是25帧 所以要记录25次
                for (int j = 0; j < 25; j++) {
                    recorder.record(converter.getFrame(read));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //最后一定要结束并释放资源
            recorder.stop();
            recorder.release();
        }
    }

}
