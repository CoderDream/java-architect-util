package com.coderdream.freeapps.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class CutImageUtils {

    /**
     * JAVA裁剪图片
     *
     * @param srcImageFile  需要裁剪的图片
     * @param x             裁剪时x的坐标（左上角）
     * @param y             裁剪时y的坐标（左上角）
     * @param width         裁剪后的图片宽度
     * @param height        裁剪后的图片高度
     * @param destImageFile 裁剪后的图片
     * @return
     */
    public static boolean cutForQr(File srcImageFile, File destImageFile) {
        try {
            int x = 884;
            int y = 86;
            int width = 180;
            int height = 180;
            //使用ImageIO的read方法读取图片
            BufferedImage read = ImageIO.read(srcImageFile);
            //调用裁剪方法
            BufferedImage image = read.getSubimage(x, y, width, height);
            //获取到文件的后缀名
            String fileName = srcImageFile.getName();
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //使用ImageIO的write方法进行输出
            ImageIO.write(image, formatName, destImageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * JAVA裁剪图片
     *
     * @param srcImageFile  需要裁剪的图片
     * @param x             裁剪时x的坐标（左上角）
     * @param y             裁剪时y的坐标（左上角）
     * @param width         裁剪后的图片宽度
     * @param height        裁剪后的图片高度
     * @param destImageFile 裁剪后的图片
     * @return
     */
    public static boolean cut(File srcImageFile, int x, int y, int width, int height, File destImageFile) {
        try {
            //使用ImageIO的read方法读取图片
            BufferedImage read = ImageIO.read(srcImageFile);
            //调用裁剪方法
            BufferedImage image = read.getSubimage(x, y, width, height);
            //获取到文件的后缀名
            String fileName = srcImageFile.getName();
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //使用ImageIO的write方法进行输出
            ImageIO.write(image, formatName, destImageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
