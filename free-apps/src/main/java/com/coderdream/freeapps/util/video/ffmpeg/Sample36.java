package com.coderdream.freeapps.util.video.ffmpeg;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
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

public class Sample36 {

    public static void main(String[] args) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("D:\\lpl\\lpl.mp4");
        try {
            grabber.start();

            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("D:\\hls\\index.m3u8", grabber.getImageWidth(),
                grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setFormat("hls");
            recorder.setOption("hls_time", "10");
            recorder.setOption("hls_playlist_type", "vod");
            recorder.setOption("hls_list_size", "0");
            recorder.setOption("hls_segment_filename", "D:\\hls\\video_%04d.ts");
            recorder.start(grabber.getFormatContext());

            AVPacket packet = null;
            while ((packet = grabber.grabPacket()) != null) {
                recorder.recordPacket(packet);
                avcodec.av_packet_unref(packet);
            }

            recorder.close();
            grabber.close();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (FFmpegFrameGrabber.Exception e) {
            throw new RuntimeException(e);
        } catch (FrameGrabber.Exception e) {
            throw new RuntimeException(e);
        }
    }
}
