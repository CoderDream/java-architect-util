package com.coderdream.autogenvedio.util;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class GraphicsUtils {
    public static void main(String[] args) {
        String imgPath = "d://image//test1234.png";
        Integer width = 1920;
        Integer height = 1080;
        createImage(imgPath, width, height);

        String qrPath = "D:\\12_iOS_Android\\202212\\20221231\\qr\\1_qr_id1435857951.jpg";

        File qrPic = new File(qrPath);
        BufferedImage backImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        createNewPng(qrPic, backImage);
    }

    public static BufferedImage createImage(String imgPath, Integer width, Integer height) {
        long startTime = System.currentTimeMillis();
        if (imgPath.isEmpty()) {
            imgPath = "C://demo.jpg";
        }
        if (width == 0) {
            width = 1920;
        }
        if (height == 0) {
            height = 1080;
        }

        try {
//            BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //画布
            BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) canvas.getGraphics();
            g.setBackground(Color.WHITE);//设置背景色
            g.clearRect(0, 0, width, height);

            g.drawRect(50, 50, 200, 100);

            g.fillRect(60, 60, 60, 70);

            g.copyArea(40, 50, 60, 70, 200, 200);
//
////            RoundRectangle2D rectRound = new RoundRectangle2D.Double(20,30,130,100,18,15);
////            rectRound.
////            g.draw(rectRound);
//            //左上角是(20，30)，宽是130，高是100，圆角的长轴是18，短轴是15。
//
//            g.fillRoundRect(20, 30, 130, 100, 18, 15);
//
//            // 设置文字抗锯齿
//            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
////            Graphics g = image.createGraphics();
//            g.drawImage(canvas, 0, 0, width, height, null);
//            g.drawLine(3, 3, 50, 50);//在(3,3)与(50,50)之间画一条线段
//            g.drawLine(100, 100, 100, 100);//画一个点

            File outfile = new File(imgPath);
            ImageIO.write(canvas, "png", outfile);

            System.out.println("生成成功！");
            System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
            System.out.println("生成文件路径: " + outfile.getAbsolutePath());
            return canvas;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage drawLine(String imgPath) {
        if (imgPath.isEmpty()) {
            imgPath = "C://demo.jpg";
        }
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(imgPath));

            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void s() {
        /**
         * 定义二维码的参数
         */
        HashMap<EncodeHintType, Object> hints = new HashMap();
        //指定字符编码为“utf-8”
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //指定二维码的纠错等级为中级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置图片的边距
        hints.put(EncodeHintType.MARGIN, 1);
//        /**
//         * 生成二维码
//         */
//        try {
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT, hints);
//            Path file = new File(filePath).toPath();
//            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
//        } catch (Exception e) {
//            log.error("二维码生成出错:/permitDownload: error", e);
//        }
    }

    private static Integer QRCODE_WIDTH = 600;
    private static Integer QRCODE_HEIGHT = 400;

    private static Integer IMAGE_HEIGHT = 400;

    private static Integer DEFAULT_FONT_SIZE = 10;
    private static Integer TEXT_DEFAULT_HEIGHT = 20;


    private static Integer TEMP_PARAM = 20;


    /**
     * 给二维码下方添加说明文字
     *
     * @param image    原二维码
     * @param topText  顶部说明文字
     * @param downText 底部说明文字
     * @return 带说明文字的二维码
     */
    private static BufferedImage addNote(BufferedImage image, String topText, String downText) {
        Image src = image.getScaledInstance(QRCODE_WIDTH, QRCODE_HEIGHT, Image.SCALE_DEFAULT);
        BufferedImage tag = new BufferedImage(QRCODE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = tag.createGraphics();//设置文字
        g2.setColor(Color.BLACK);
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, QRCODE_WIDTH, IMAGE_HEIGHT);
        //设置顶部文本并计算坐标
        // 保证操作系统包含“宋体”字体，如果没有上传字体至JAVA_HOME/jre/lib/fonts下
        FontMetrics fm = getFontByWidth(new Font("宋体", Font.PLAIN, DEFAULT_FONT_SIZE), topText, g2);
        //文字的宽度
        int fontWidth = fm.stringWidth(topText);
        //文字高度
        int fontHeight = fm.getHeight();
        /**
         * 顶部添加文字并居中
         */
        g2.drawString(topText, (QRCODE_WIDTH - fontWidth) / 2, (TEXT_DEFAULT_HEIGHT - fontHeight) / 2 + fm.getFont().getSize());
        /**
         * 绘制二维码
         */
        g2.drawImage(src, 0, TEXT_DEFAULT_HEIGHT, null);
        // 设置底部文字字体并计算坐标
        // 保证操作系统包含“宋体”字体，如果没有上传字体至JAVA_HOME/jre/lib/fonts下
        fm = getFontByWidth(new Font("宋体", Font.PLAIN, DEFAULT_FONT_SIZE), downText, g2);
        //文字的宽度
        fontWidth = fm.stringWidth(downText);
        //文字高度
        fontHeight = fm.getHeight();
        /**
         * 添加底部文字
         */
        g2.drawString(downText, (QRCODE_WIDTH - fontWidth) / 2, QRCODE_HEIGHT + TEXT_DEFAULT_HEIGHT + (TEXT_DEFAULT_HEIGHT - fontHeight) / 2 + fm.getFont().getSize());
        g2.dispose();
        image = tag;
        image.flush();
        return image;
    }

    /**
     * 根据文字长度改变文字大小
     *
     * @param font 默认字体
     * @param note 文字内容
     * @param g2   图像画布
     * @return 处理后的字体封装
     */
    private static FontMetrics getFontByWidth(Font font, String note, Graphics2D g2) {
        FontMetrics fm = g2.getFontMetrics(font);
        int textWidth = fm.stringWidth(note);//文字的宽度
        if (textWidth > QRCODE_WIDTH) {
            int fontSize = (int) ((TEMP_PARAM / textWidth) * font.getSize());
            font = new Font(font.getName(), font.getStyle(), fontSize);
        }
        g2.setFont(font);
        return g2.getFontMetrics(font);
    }

    /**
     * 给二维码图片添加背景图片
     *
     * @param qrPic     二维码
     * @param backImage 背景图片
     */
    private static void createNewPng(File qrPic, BufferedImage backImage) {
        try {
            if (!qrPic.isFile()) {
                System.out.println("二维码临时路径不存在！");
            }
            /**
             * 读取背景图片，并构建绘图对象--画布
             */
            Graphics2D g = backImage.createGraphics();
            /**
             * 读取二维码图片
             */
            BufferedImage qrcodeImage = ImageIO.read(qrPic);
            //开始绘制图片
            g.drawImage(qrcodeImage, 48, 120, qrcodeImage.getWidth(), qrcodeImage.getHeight(), null);
            g.dispose();
            ImageIO.write(backImage, "png", qrPic);
        } catch (Exception e) {
//            log.error("绘制二维码出错！");
        }
    }
}
