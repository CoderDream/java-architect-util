package com.coderdream.pic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class CutPicture {


    public static void main(String[] args) {

    }

    public static void cutImage(String url, File pathname) throws Exception {
        System.out.println("url===>" + url);
        System.out.println("pathname==>" + String.valueOf(pathname));

        Desktop.getDesktop().browse(new URL(url).toURI());
        Robot robot = new Robot();
        robot.delay(1000);//使程序暂停一段时间，类似于线程的sleep()方法
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        // 最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(100);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        // 保存图片
        ImageIO.write(bi, "jpg", new File(String.valueOf(pathname) + "\\page.jpg"));

    }
}
