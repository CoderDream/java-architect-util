package com.example.demo.util;

public class FFmpegUtils {

//    public static void pic2MovByFfmpeg(String mp4SavePath,String picturesPath, int width, int height) throws FFmpegFrameRecorder.Exception {
//        //视频宽高最好是按照常见的视频的宽高  16：9  或者 9：16
//        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(mp4SavePath, width, height);
//        //设置视频编码层模式
//        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//        //设置视频为1帧每秒
//        recorder.setFrameRate(1);
//        //设置视频图像数据格式
//        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
//        recorder.setFormat("mp4");
//
//        File file = new File(picturesPath);
//        File[] flist = file.listFiles();
//        try {
//            recorder.start();
//            Java2DFrameConverter converter = new Java2DFrameConverter();
//            //录制一个22秒的视频
//            for (int i = 0; i < flist.length; i++) {
//                BufferedImage read = ImageIO.read(flist[i]);
//                recorder.record(converter.getFrame(read));
//                //recorder.record(converter.getFrame(read),avutil.AV_PIX_FMT_RGB32_1);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //最后一定要结束并释放资源
//            recorder.stop();
//            recorder.release();
//        }
//    }


}
