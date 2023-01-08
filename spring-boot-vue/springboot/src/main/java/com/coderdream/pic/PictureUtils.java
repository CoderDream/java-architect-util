package com.coderdream.pic;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;

public class PictureUtils {
    public static void main(String[] args) {
        snapHttp();
    }


    public static void splitPicture() {

        //原图片
        String sourcePicPath = "D:/File/javaPic.png";
        File sourcePic = new File(sourcePicPath);
        try {
            BufferedImage pic1 = ImageIO.read(sourcePic);
            int width = pic1.getWidth();
            int height = pic1.getHeight();
            //参数依次为，截取起点的x坐标，y坐标，截取宽度，截取高度
            BufferedImage pic2 = pic1.getSubimage(width / 5, height / 5, width - width / 5, height - height / 5);

            //将截取的子图另行存储
            File desImage = new File("D:/subjavaPic.png");
            ImageIO.write(pic2, "png", desImage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void  snapHttp() {
        if (!java.awt.Desktop.isDesktopSupported()) {
            System.err.println("Desktop is not supported (fatal)");
            System.exit(1);
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            System.err.println("Desktop doesn't support the browse action (fatal)");
            System.exit(1);
        }

        try {
            URI uri = URI.create("https://apps.apple.com/cn/app/id1435857951");
            desktop.browse(uri);
            Thread.sleep(8000); // 8 seconds is enough to load the any page.
            Robot robot = new Robot();
            // Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize() );
            Rectangle rectangle = new Rectangle(300, 90, 1000, 720);
            BufferedImage image = robot.createScreenCapture(rectangle);
            File outputfile = new File("D:\\test.jpg");
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
