package com.feng.controller;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Test1_CreateImage {

    /**
     * @return void
     * @Author fengfanli
     * @Description //TODO 生成一个白色的图片
     * @Date 15:51 2021/4/8
     * @Param [args]
     **/
    public static void main(String[] args) throws Exception {
        BufferedImage bufIma = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufIma.createGraphics();
        g2d.setBackground(new Color(255, 255, 255));
        g2d.clearRect(0, 0, 50, 50);
        ImageIO.write(bufIma, "png", new File("d:\\6.png"));
    }
}
