package com.coderdream.freeapps.util.video.ffmpeg;

//import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FPS;
//import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_COUNT;
//import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateFileCapture;
//import static com.googlecode.javacv.cpp.opencv_highgui.cvGetCaptureProperty;
//import static com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture;
//
//import java.io.File;
//import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

/**
 * 获取视频时长 *
 *
 * @author chengesheng * @date 2013-5-22 下午11:15:25 * @note VideoFileLength
 */
public final class VideoFileLength {

//    public static void main(String[] argus) {
//        float len = getVideoFileLength("D:\\BaiduNetdiskDownload\\零基础学PS，30堂课从入门到精通（完结）\\视频课\\第01堂课：课程须知 成为PS高手的第一步.MP4");
//        System.out.println("Video length: " + len + " s");
//    }
//
//    public static float getVideoFileLength(String fileName) {
//        File file = new File(fileName);
//        if (!file.exists()) {
//            return 0;
//        }
//        float len = 0;
//        CvCapture capture = cvCreateFileCapture(fileName);
//        try {
//// 获取视频总帧数
//            long frameCount = (long) cvGetCaptureProperty(capture, CV_CAP_PROP_FRAME_COUNT);
//
//// 获取视频每秒帧数
//            long fps = (long) cvGetCaptureProperty(capture, CV_CAP_PROP_FPS);
//
//            len = (float) frameCount / fps;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            cvReleaseCapture(capture);
//        }
//        return len;
//    }
}
