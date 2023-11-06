package com.coderdream.freeapps.util.video.javacv;

//import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
//import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
//
//import org.bytedeco.opencv.global.opencv_imgcodecs;
//import org.bytedeco.opencv.global.opencv_imgproc;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Rect;

import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_core.addWeighted;
import static org.bytedeco.opencv.global.opencv_core.flip;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.opencv.core.CvType.CV_8UC1;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;


/**
 * @author CoderDream
 *
 * <a href="https://blog.csdn.net/ayou_llf/article/details/129222628">javacv从入门到精通——第四章：图像处理</a>
 */
public class OpenCvUtils {

    public static void main(String[] args) {
//        String filePath = "D:\\202307082232.mp4";
//        int n = 4;
//        String fileType = "png";
//        String targetUrl = "D:\\202307082232_" + n + "." + fileType;
////        JavaCvUtils.getVideoSmallImg(filePath, n, targetUrl, fileType);
//
//        String inputImagePath = targetUrl;
//        String outputImagePath = "D:\\202307082232_" + n + "_resizer." + fileType;
//        OpenCvUtils.imageResizer(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_rotator." + fileType;
//        JavaCvUtils.imageRotator(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_edges." + fileType;
//        JavaCvUtils.edgeDetector(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_erode." + fileType;
//        JavaCvUtils.imageErode(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_cut." + fileType;
//        OpenCvUtils.imageCut(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_resizer_opencv." + fileType;
//        OpenCvUtils.imageResizer(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_imageWarpAffine." + fileType;
//        OpenCvUtils.imageWarpAffine(inputImagePath, outputImagePath);

//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_imageFlip." + fileType;
//        OpenCvUtils.imageFlip(inputImagePath, outputImagePath);
        //
//        inputImagePath = outputImagePath;
//        outputImagePath = "D:\\202307082232_" + n + "_imageAddWatermark." + fileType;
//        OpenCvUtils.imageAddWatermark(inputImagePath, outputImagePath);

        String inputImagePath = "D:\\71_PS_Lesson\\生日图片_1920.png";
        String outputImagePath = "D:\\71_PS_Lesson\\生日图片_1920_1080.png";
        int x = (1200 - 1080) / 2;
        int y = 0;
        int width = 1920;
        int height = 1080;
        imageCut( inputImagePath,  outputImagePath,  x,  y,  width,  height);
    }


    /**
     * 裁剪图像
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageCut(String inputImagePath, String outputImagePath) {
        // 读取图像
        Mat src = imread(inputImagePath);
        // 定义矩形区域
        org.bytedeco.opencv.opencv_core.Rect roi = new org.bytedeco.opencv.opencv_core.Rect(10, 10, 100, 100);
        // 裁剪图像
        org.bytedeco.opencv.opencv_core.Mat cropped = new org.bytedeco.opencv.opencv_core.Mat(src, roi);
        // 保存图像
        imwrite(outputImagePath, cropped);
    }

    /**
     * 裁剪图像
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageCut(String inputImagePath, String outputImagePath, int x, int y, int width, int height) {
        // 读取图像
        Mat src = imread(inputImagePath);
        // 定义矩形区域
        org.bytedeco.opencv.opencv_core.Rect roi = new org.bytedeco.opencv.opencv_core.Rect(x, y, width, height);
        // 裁剪图像
        org.bytedeco.opencv.opencv_core.Mat cropped = new org.bytedeco.opencv.opencv_core.Mat(src, roi);
        // 保存图像
        imwrite(outputImagePath, cropped);
    }

    /**
     * 图像缩放
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageResizer(String inputImagePath, String outputImagePath) {
        // 读取图像
        Mat src = imread(inputImagePath);

        // 缩放图像
        Mat dst = new Mat();
        resize(src, dst, new org.bytedeco.opencv.opencv_core.Size(0, 0), 0.5, 0.5, INTER_LINEAR);

        // 保存图像
        imwrite(outputImagePath, dst);
    }

    /**
     * 图像缩放
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageWarpAffine(String inputImagePath, String outputImagePath) {
        // 读取图像
        Mat src = imread(inputImagePath);

        // 定义旋转角度
        double angle = 45;

        // 计算旋转矩阵
        Mat rot = getRotationMatrix2D(new Point2f(src.cols() / 2, src.rows() / 2), angle, 1);

        // 旋转图像
        Mat dst = new Mat();
        warpAffine(src, dst, rot, new org.bytedeco.opencv.opencv_core.Size(src.cols(), src.rows()));

        // 保存图像
        imwrite(outputImagePath, dst);
    }

    /**
     * 翻转图像
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageFlip(String inputImagePath, String outputImagePath) {
        // 读取图像
        Mat src = imread(inputImagePath);

        // 定义旋转角度
        double angle = 45;

        // 计算旋转矩阵
        Mat rot = getRotationMatrix2D(new Point2f(src.cols() / 2, src.rows() / 2), angle, 1);

        // 翻转图像
        Mat dst = new Mat();
        flip(src, dst, 1);

        // 保存图像
        imwrite(outputImagePath, dst);
    }

    //

    /**
     * 翻转图像
     *
     * @param inputImagePath  待处理的图片
     * @param outputImagePath 处理后的图片
     */
    public static void imageAddWatermark(String inputImagePath, String outputImagePath) {
//        // 读取图像
//        Mat image = imread(inputImagePath);
//
//        // 读取水印图像
//        Mat watermark = imread("D:\\logo.png");
//
//        // 设置水印图像的透明度
//        double alpha = 0.5;
//
//        // 将水印图像复制到原始图像上
//        Mat imageWithWatermark = image.clone();
//        addWeighted(image, 1 - alpha, watermark, alpha, 0, imageWithWatermark);
//
//        org.opencv.core.Mat logo = imread("D:\\logo.png");
//
////将logo也变为灰度图
//        Mat logoGray = Mat(logo.rows, logo.cols, CV_8UC1);
//
//        cvtColor(logo, logoGray, CV_BGR2GRAY);
//
////修改addWeighted,将logo变为logoGray
//
//        addWeighted(imageROI(Rect(15, 20, logo.cols, logo.rows)), 1.0,
//
//            logoGray, 0.5, 0,
//
//            imageROI(Rect(15, 20, logo.cols, logo.rows)));
//
//        // 保存图像
//        imwrite(outputImagePath, imageWithWatermark);
    }
}


