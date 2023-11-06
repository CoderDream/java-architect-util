package com.coderdream.freeapps.util.video.ffmpeg;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameFilter;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 诸葛小猿
 * @date 2020-10-24
 */
@Slf4j
public class MakeVideo {

    public static void main(String[] args) throws Exception {
        MakeVideo.m1();
//        m2();
    }

    private static void m1() {
        //合成的MP4
        String mp4SavePath = "C:\\Users\\WuXiaoLong\\Desktop\\img\\img.mp4";

        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        mp4SavePath = dateStr + "_img.mp4";
        //图片地址 这里面放了22张图片
        String img = "C:\\Users\\WuXiaoLong\\Desktop\\img";
        img = "D:\\12_iOS_Android\\202306\\20230618\\page";
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


    private static void createMp4(String mp4SavePath, Map<Integer, File> imgMap, int width, int height) {
        int FRAME_RATE = 30;
        int FRAME_SECONDS = 1;
        //视频宽高最好是按照常见的视频的宽高  16：9  或者 9：16
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(mp4SavePath, width, height);
        //设置视频编码层模式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        //设置视频为25帧每秒
        recorder.setFrameRate(FRAME_RATE);
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
                for (int j = 0; j < FRAME_RATE * FRAME_SECONDS; j++) {
                    recorder.record(converter.getFrame(read));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //最后一定要结束并释放资源
            try {
                recorder.stop();
            } catch (FFmpegFrameRecorder.Exception e) {
                throw new RuntimeException(e);
            }
            try {
                recorder.release();
            } catch (FFmpegFrameRecorder.Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void m2() {
        String fileName = "C:\\Users\\CoderDream\\Downloads2\\抖包袱大会.Dou.Bao.Fu.S01.2023.1080p.H264.AAC-OurTV\\抖包袱大会.Dou.Bao.Fu.2023.S01E01.1080p.H264.AAC-OurTV.mp4";
        //crop=width:height::x:y
        videoConvert(fileName, "D:\\testvedio\\", MessageFormat.format(
            "crop={0}:{1}:{2}:{3}",
            String.valueOf(1920),
            String.valueOf(1080),
            String.valueOf(0),
            String.valueOf(0)), 1920, 1080, "mp4");
    }

    /**
     * 视频转分辨率转视频编码
     *
     * @param inputFile     源文件
     * @param outputPath    输出目录
     * @param filterContent 过滤器配置参数
     * @param width         需要转成的视频的宽度
     * @param height        需要转成的视频的高度
     * @param videoFormat   需要转换成的视频格式
     * @return 返回新文件名称，可以根据实际使用修改
     */
    public static String videoConvert(String inputFile, String outputPath, String filterContent, Integer width,
        Integer height, String videoFormat) {
        File output = new File(outputPath);
        if (!output.exists()) {
            output.mkdirs();
        }

        List<String> paths = Splitter.on(".").splitToList(inputFile);
        String ext = paths.get(paths.size() - 1);
        if (StringUtils.isEmpty(videoFormat)) {
            videoFormat = ext;
        }
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + videoFormat;
        String resultPath = Joiner.on(File.separator).join(Arrays.asList(outputPath, newFileName));

        Frame frame;
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        FFmpegFrameRecorder recorder = null;
        double aspectRatio = 1.0;

        FFmpegFrameFilter filter = new FFmpegFrameFilter(filterContent, grabber.getImageWidth(),
            grabber.getImageHeight());
        try {

            // 初始化帧抓取器，例如数据结构（时间戳、编码器上下文、帧对象等），
            // 如果入参等于true，还会调用avformat_find_stream_info方法获取流的信息，放入AVFormatContext类型的成员变量oc中
            try {
                grabber.start(true);
            } catch (FFmpegFrameGrabber.Exception e) {
                throw new RuntimeException(e);
            }
            // grabber.start方法中，初始化的解码器信息存在放在grabber的成员变量oc中

            //过滤器启动
            filter.start();

            AVFormatContext avformatcontext = grabber.getFormatContext();
            // 文件内有几个媒体流（一般是视频流+音频流）
            int streamNum = avformatcontext.nb_streams();
            // 没有媒体流就不用继续了
            if (streamNum < 1) {
                log.info("视频文件格式不对");
                return "error";
            }
            // 取得视频的帧率
            int framerate = (int) grabber.getVideoFrameRate();
            log.info("视频帧率:{}，视频时长:{}秒，媒体流数量:{}", framerate, avformatcontext.duration() / 1000000,
                streamNum);
            // 遍历每一个流，检查其类型
            for (int i = 0; i < streamNum; i++) {
                AVStream avstream = avformatcontext.streams(i);
                AVCodecParameters avcodecparameters = avstream.codecpar();
                log.info("流的索引:{}，编码器类型:{}，编码器ID:{}", i, avcodecparameters.codec_type(),
                    avcodecparameters.codec_id());
            }
            // 源视频宽度
            int frameWidth = grabber.getImageWidth();
            // 源视频高度
            int frameHeight = grabber.getImageHeight();
            // 源音频通道数量
            int audioChannels = grabber.getAudioChannels();
            log.info("源视频宽度:{}，源视频高度:{}，音频通道数:{}", frameWidth, frameHeight, audioChannels);

            AVFormatContext ifmt_ctx = grabber.getFormatContext();
            AVStream inpVideoStream = null,
                inpAudioStream = null;
            if (ifmt_ctx != null) {
                //获取Video的AVStream
                inpVideoStream = ifmt_ctx.streams(grabber.getVideoStream());
                //不能使用 inpVideoStream.sample_aspect_ratio()
                AVRational SAR = inpVideoStream.codecpar().sample_aspect_ratio();
                double a = (double) SAR.num() / (double) SAR.den();
                //默认情况为1:1
                aspectRatio = a == 0.0 ? 1.0 : a;
                int SARden = SAR.den();
                int SARnum = SAR.num();
                log.info("SARden[{}], SARnum[{}]", SARden, SARnum);
            }

            recorder = new FFmpegFrameRecorder(resultPath, width, height, audioChannels);
            // 设置H264编码
//            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            // 设置视频格式
            recorder.setFormat(videoFormat);
            // 使用原始视频的码率，若需要则自行修改码率
            recorder.setVideoBitrate(grabber.getVideoBitrate());
            // 一秒内的帧数，帧率
            recorder.setFrameRate(framerate);
            // 两个关键帧之间的帧数
            recorder.setGopSize(framerate);
            // 设置音频通道数，与视频源的通道数相等
            recorder.setAudioChannels(grabber.getAudioChannels());

            //给录制器设置SAR
            recorder.setAspectRatio(aspectRatio);

            recorder.start();
            int videoFrameNum = 0;
            int audioFrameNum = 0;
            int dataFrameNum = 0;
            // 持续从视频源取帧
            while (null != (frame = grabber.grab())) {
                // 有图像，就把视频帧加一
                if (null != frame.image) {
                    videoFrameNum++;
                    //把已经解码后的视频图像像素塞进过滤器
                    filter.push(frame);
                    //取出过滤器处理后的图像像素数据
                    Frame filterFrame = filter.pullImage();
                    // 取出的每一帧，都保存到视频
                    recorder.record(filterFrame);
                }
                // 有声音，就把音频帧加一
                if (null != frame.samples) {
                    audioFrameNum++;
                    // 取出的每一帧
                    recorder.record(frame);
                }
                // 有数据，就把数据帧加一
                if (null != frame.data) {
                    dataFrameNum++;
                }
            }
            log.info("转码完成，视频帧:{}，音频帧:{}，数据帧:{}", videoFrameNum, audioFrameNum, dataFrameNum);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "error";
        } catch (FFmpegFrameGrabber.Exception e) {
            throw new RuntimeException(e);
        } catch (FFmpegFrameFilter.Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (recorder != null) {
                try {
                    recorder.close();
                } catch (Exception e) {
                    log.info("recorder.close异常" + e);
                }
            }
            try {
                filter.close();
            } catch (FrameFilter.Exception e) {
                log.info("filter.close异常" + e);
            }
            try {
                grabber.close();
            } catch (FrameGrabber.Exception e) {
                log.info("frameGrabber.close异常" + e);
            }
        }
        return newFileName;
    }
}

