package com.coderdream.freeapps.util.video.javacv;

import java.io.File;
import javax.imageio.ImageIO;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacpp.opencv_imgcodecs;
//import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author CoderDream
 */
public class JavaCvUtils {

    public static void main(String[] args) {
        String filePath = "D:\\202307082232.mp4";
        int n = 4;
        String fileType = "png";
        String targetUrl = "D:\\202307082232_" + n + "." + fileType;
//        JavaCvUtils.getVideoSmallImg(filePath, n, targetUrl, fileType);

        String inputImagePath = targetUrl;
        String outputImagePath = "D:\\202307082232_" + n + "_resizer." + fileType;
        JavaCvUtils.imageResizer(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_rotator." + fileType;
//        JavaCvUtils.imageRotator(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_edges." + fileType;
//        JavaCvUtils.edgeDetector(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_erode." + fileType;
//        JavaCvUtils.imageErode(inputImagePath, outputImagePath);

        inputImagePath = outputImagePath;
        outputImagePath = "D:\\202307082232_" + n + "_dilate." + fileType;
        JavaCvUtils.imageDilate(inputImagePath, outputImagePath);
    }

    /**
     * 图像缩放
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageResizer(String inputImagePath, String outputImagePath) {
        Mat image = opencv_imgcodecs.imread(inputImagePath);
        Mat resizedImage = new Mat();
        Size size = new Size(500, 500);
        opencv_imgproc.resize(image, resizedImage, size);
        opencv_imgcodecs.imwrite(outputImagePath, resizedImage);
    }

    /**
     * 图像旋转
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageRotator(String inputImagePath, String outputImagePath) {
        Mat image = opencv_imgcodecs.imread(inputImagePath);
        Mat rotatedImage = new Mat();
        double angle = 45.0;
        Point2f center = new Point2f(image.cols() / 2, image.rows() / 2);
        Mat rotationMatrix = opencv_imgproc.getRotationMatrix2D(center, angle, 1.0);
        opencv_imgproc.warpAffine(image, rotatedImage, rotationMatrix, image.size());
        opencv_imgcodecs.imwrite(outputImagePath, rotatedImage);
    }

    /**
     * 边缘检测
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void edgeDetector(String inputImagePath, String outputImagePath) {
        Mat image = opencv_imgcodecs.imread(inputImagePath);
        Mat grayImage = new Mat();
        Mat cannyImage = new Mat();
        opencv_imgproc.cvtColor(image, grayImage, opencv_imgproc.COLOR_BGR2GRAY);
        opencv_imgproc.Canny(grayImage, cannyImage, 100, 200);
        opencv_imgcodecs.imwrite(outputImagePath, cannyImage);
    }

    /**
     * 图像腐蚀
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageErode(String inputImagePath, String outputImagePath) {
        Mat image = opencv_imgcodecs.imread(inputImagePath);
        Mat erodedImage = new Mat();
        Mat element = opencv_imgproc.getStructuringElement(opencv_imgproc.MORPH_RECT, new Size(5, 5));
        opencv_imgproc.erode(image, erodedImage, element);
        opencv_imgcodecs.imwrite(outputImagePath, erodedImage);
    }

    /**
     * 图像腐蚀
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageDilate(String inputImagePath, String outputImagePath) {
        try {
            // 加载图像
            BufferedImage img = ImageIO.read(new File(inputImagePath));
            Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
            org.bytedeco.opencv.opencv_core.Mat inputMat = new OpenCVFrameConverter.ToMat().convert(
                java2DFrameConverter.convert(img));

            // 定义核
            org.bytedeco.opencv.opencv_core.Mat kernel = opencv_imgproc.getStructuringElement(opencv_imgproc.MORPH_RECT,
                new org.bytedeco.opencv.opencv_core.Size(3, 3));

            // 膨胀操作
            org.bytedeco.opencv.opencv_core.Mat outputMat = new Mat();
            opencv_imgproc.dilate(inputMat, outputMat, kernel);

            // 保存图像
            Java2DFrameConverter java2DFrameConverterOutput = new Java2DFrameConverter();
            BufferedImage outputImg = java2DFrameConverterOutput.convert(
                new OpenCVFrameConverter.ToMat().convert(outputMat));
            ImageIO.write(outputImg, "png", new File(outputImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取视频的第n帧画面
     * <a href="https://www.cnblogs.com/c2g5201314/p/16634653.html">获取视频的第n帧画面，支持avi、mp4、wmv格式</a>
     */
    public static void getVideoSmallImg(String filePath, int n, String targetUrl, String fileType) {
        try {
            File file = new File(filePath);
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(file.getAbsolutePath());
//            // 设置读取的最大数据，单位字节
//            fFmpegFrameGrabber.setOption("probesize", "10000");
//            // 设置分析的最长时间，单位微秒
//            fFmpegFrameGrabber.setOption("analyzeduration", "10000");
//            fFmpegFrameGrabber.setOption("rtsp_transport", "tcp");
            fFmpegFrameGrabber.start();
            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
            System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);
            //标识
            int flag = 0;
            //Frame对象
            Frame frame = null;
            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
                if (frame != null && flag >= n - 1) {
                    //文件储存对象
                    File outPut = new File(targetUrl);
                    ImageIO.write(new Java2DFrameConverter().getBufferedImage(frame), fileType.replace(".", ""),
                        outPut);
                    break;
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new CustomResultException(ResultEn.FILE_LOADUP_ERROR.getCode(), "视频文件解析缩略图失败:" + ExcBox.getExcMsg(e));
        }
    }
}


