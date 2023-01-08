package com.coderdream.autogenvedio.util;

import cn.hutool.core.util.StrUtil;
import com.coderdream.autogenvedio.entity.AppBrief;
import com.swetake.util.Qrcode;
import org.springframework.util.CollectionUtils;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 二维码图像工具类
 */
public class ImageUtils {


    public static void main(String[] args) {

        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String basePath = "D:" + File.separator + "12_iOS_Android" + File.separator + monthStr + File.separator + dateStr;


        createSingleImage(basePath);

        // path D:\12_iOS_Android\202301\20230102
//        String txtFileName = File.separator + basePath + File.separator + dateStr + ".txt";
//        try {
//            String link = "https://blog.csdn.net/chenmissyu";
//            String eurl = "E:\\QRcode.png";
//            ImageUtils.createQRCode(link, eurl);
//            String bigurl = "E:\\test1234.png";           //主图片路径
//            String ewmPath = "E:\\finalImage.png";         //合成路径
//
//            java.util.List<String> urlCnList = new ArrayList<>();
//            List<AppBrief> appBriefList = GenerateAppInfo.BaseUtils.genBrief();
//            if (!CollectionUtils.isEmpty(appBriefList)) {
////                AppBrief appBrief0 = appBriefList.get(0);
////                ewmPath = path + File.separator + "final" + File.separator + appBrief0.getAppId() + ".png";
////
////                String urlCn = appBrief0.getUrlCn();
////                String qrUrl = path + File.separator + "qr" + appBrief0.getAppId() + ".png";
////
////                ImageUtils.compoundImage(bigurl, urlCn, qrUrl, appBrief0.getContent().toString(), ewmPath);
//
//                for (AppBrief appBrief : appBriefList) {
////                urlCn = entity.getUrlCn();
////                if (-1 != urlCn.lastIndexOf("https://apps.apple.com/cn/app/id")) {
////                    urlCnList.add(entity.getUrlCn());
////                    System.out.println(entity.getUrlCn());
////                }
//                    String finalDir = basePath + File.separator + "final";
//                    File finalFile = new File(finalDir);
//                    if (!finalFile.exists()) {
//                        finalFile.mkdirs();
//                    }
//
//                    ewmPath = finalDir + File.separator + appBrief.getAppId() + ".png";
//
//                    String urlCn = appBrief.getUrlCn();
//                    String qrUrl = basePath + File.separator + "qr" + appBrief.getAppId() + ".png";
//
//                    ImageUtils.compoundImage(bigurl, qrUrl, appBrief);
//                }
//            }
//
////            String urlCn = "";
////            for (AppBrief entity : appBriefList) {
////                urlCn = entity.getUrlCn();
////                if (-1 != urlCn.lastIndexOf("https://apps.apple.com/cn/app/id")) {
////                    urlCnList.add(entity.getUrlCn());
////                    System.out.println(entity.getUrlCn());
////                }
////            }
//        } catch (Exception e) {
//        }
    }

    public static void createSingleImage(String basePath) {
        try {
            AppBrief appBrief = new AppBrief();
            appBrief.setAppId("id1095142462");
            appBrief.setPrice(30);
            appBrief.setUrlCn("https://apps.apple.com/cn/app/id1095142462");
            appBrief.setContent(new ArrayList<>(Arrays.asList("把你的iPhone或iPad变成你的Mac的无线闪存盘。","完全访问你的Mac文件-使用你的iOS设备流媒体视频，查看照片和文件，在家里的任何地方。")));

            String singleUrl = basePath + File.separator + "single_" + appBrief.getAppId() + ".png";
//            File Explorer & Player [Pro]
//            ¥30➱0
//            把你的iPhone或iPad变成你的Mac的无线闪存盘。
//            完全访问你的Mac文件-使用你的iOS设备流媒体视频，查看照片和文件，在家里的任何地方。
//            https://apps.apple.com/cn/app/id1095142462
//            String link = "https://blog.csdn.net/chenmissyu";
            String qrcodePath = "E:\\QRcode.png";
//            createQRCode(link, qrcodePath);
            String bigurl = "D:\\12_iOS_Android\\550_1.png";           //主图片路径
//            singleUrl = "E:\\test\\singleImage.png";         //合成路径
//            appBrief.setUrlCn(link);
            compoundSingleImage(bigurl, singleUrl, qrcodePath, appBrief);
        } catch (Exception e) {
        }
    }

    /**
     * 生成二维码
     *
     * @param backLink 扫码回调链接
     * @param savePath 二维码保存路径
     * @throws IOException
     */
    public static void createQRCode(String backLink, String savePath) throws IOException {
        //计算二维码图片的高宽比
        int v = 6;
        int width = 67 + 12 * (v - 1);
        int height = 67 + 12 * (v - 1);

        Qrcode x = new Qrcode();
        x.setQrcodeErrorCorrect('L');
        x.setQrcodeEncodeMode('B');//注意版本信息 N代表数字 、A代表 a-z,A-Z、B代表 其他)
        x.setQrcodeVersion(v);

        byte[] d = backLink.getBytes("utf-8");//汉字转格式需要抛出异常

        //缓冲区
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        //绘图
        Graphics2D gs = bufferedImage.createGraphics();

        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);

        //偏移量
        int pixoff = 2;
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = x.calQrcode(d);
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }
        gs.dispose();
        bufferedImage.flush();
        //设置图片格式，与输出的路径
//        String url = savePath;
        ImageIO.write(bufferedImage, "png", new File(savePath));
        System.out.println("二维码生成完毕");
//        return savePath;
    }

    /**
     * 图片合成
     *
     * @param mainImgPath 主图片路径
     * @param qrcodePath  二维码保存
     * @param appBrief    对象
     * @return 保存路径
     */
    public static void compoundSingleImage(String mainImgPath, String singleUrl, String qrcodePath, AppBrief appBrief) {

        try {
            //1、获取主图片
            BufferedImage big = ImageIO.read(new File(mainImgPath));
//            URL url = new URL(urlPath); String urlPath,
            //2、拿到二维码
            ImageUtils.createQRCode(appBrief.getUrlCn(), qrcodePath);
            BufferedImage erweima = ImageIO.read(new File(qrcodePath));
            int bgIndex = 6;
            String bgImageFileName = "D:\\12_iOS_Android" + File.separator + "bg_" + bgIndex + ".png";
            // 背景图片
            BufferedImage bgImage = ImageIO.read(new File(bgImageFileName));

            int width = 550;
            int height = 1200;
            Image image = big.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            //3、开始绘图
            Graphics2D g = bufferedImage2.createGraphics();
            g.drawImage(image, 0, 0, null);
//            g.drawImage(bgImage, 184, 144, 400, 900, null);
            g.drawImage(erweima, 124, 812, 300, 300, null);


            Font font = new Font("微软雅黑", Font.BOLD, 38);
            g.setFont(font);
            g.setPaint(Color.BLACK);
            //4、设置位置
            int wordWidth = ImageUtils.getWordWidth(font, appBrief.getContent().toString());
            int i = width / 2;
            int i1 = (i - wordWidth) / 2;
            int numWidth = i + i1;
            g.drawString(appBrief.getContent().toString(), numWidth - 10, 310 + 28);

            g.dispose();

            ImageIO.write(bufferedImage2, "jpg", new File(singleUrl));
            System.out.println("图片生成完毕：" + singleUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文本分行
     * @param context 原文本
     * @param windowWidth 文本框宽度
     * @param windowHeight 文本框高度
     * @param fontMetrics 文本字体格式
     * @return 分行后的文本list
     */
    private List<String> multiRows(String context, Integer windowWidth, Integer windowHeight, FontMetrics fontMetrics ){

        Integer contextWidth = fontMetrics.stringWidth(context);
        Integer fontHeight = fontMetrics.getHeight();
        List<String> rowsContext = new ArrayList<>();


        if (contextWidth < windowWidth && !context.contains("\n")){
            rowsContext.add(context);
            return rowsContext;
        }else{
            int row = 0;
            int i = 0;
            for (; i < context.length(); i ++){

                if(fontHeight * row > windowHeight)
                    break;

                while (i < context.length() && fontMetrics.stringWidth(context.substring(0,i)) <= windowWidth){
                    if (context.substring(i, i + 1).equals("\n")){
                        break;
                    }
                    i++;
                }
                if(i == context.length()){
                    break;
                }

                rowsContext.add(context.substring(0,i));
                row ++;

                if (i == context.length() - 1){
                    if (context.substring(i, i + 1).equals("\n")){
                        context = "";
                        break;
                    }else{
                        rowsContext.add(context.substring(i));
                        context = "";
                        break;
                    }
                }else {
                    if(context.substring(i, i + 1).equals("\n")){
                        context = context.substring(i + 1);
                    }else {
                        context = context.substring(i);
                    }
                }
                i = 0;
            }
            if (StrUtil.isNotEmpty(context) && fontHeight * row < windowHeight) {
                rowsContext.add(context);
            }
        }
        return rowsContext;
    }

    /**
     * 图片合成
     *
     * @param mainImgPath 主图片路径
     * @param qrcodePath  二维码保存
     * @param appBrief    对象
     * @return 保存路径
     */
    public static void compoundImage(String mainImgPath, String qrcodePath, AppBrief appBrief) {
        String ewmurl = "";
        try {
            //1、获取主图片
            BufferedImage big = ImageIO.read(new File(mainImgPath));
//            URL url = new URL(urlPath); String urlPath,
            //2、拿到二维码
            ImageUtils.createQRCode(appBrief.getUrlCn(), qrcodePath);
            BufferedImage erweima = ImageIO.read(new File(qrcodePath));
            int bgIndex = 6;
            String bgImageFileName = "D:\\12_iOS_Android" + File.separator + "bg_" + bgIndex + ".png";
            // 背景图片
            BufferedImage bgImage = ImageIO.read(new File(bgImageFileName));

            int width = 1920;
            int height = 1080;
            Image image = big.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            //3、开始绘图
            Graphics2D g = bufferedImage2.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.drawImage(bgImage, 184, 144, 400, 900, null);
            g.drawImage(erweima, 184, 344, 300, 300, null);


            Font font = new Font("微软雅黑", Font.BOLD, 38);
            g.setFont(font);
            g.setPaint(Color.BLACK);
            //4、设置位置
            int wordWidth = ImageUtils.getWordWidth(font, appBrief.getContent().toString());
            int i = width / 2;
            int i1 = (i - wordWidth) / 2;
            int numWidth = i + i1;
            g.drawString(appBrief.getContent().toString(), numWidth - 10, 310 + 28);
            g.dispose();

            ImageIO.write(bufferedImage2, "jpg", new File(ewmurl));
            System.out.println("图片生成完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文字的px宽度
     *
     * @param font    文字字体
     * @param content 文字内容
     * @return px宽度
     */
    public static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

}
