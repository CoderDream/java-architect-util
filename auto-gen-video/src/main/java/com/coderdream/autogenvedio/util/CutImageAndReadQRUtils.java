package com.coderdream.autogenvedio.util;

import com.beust.ah.A;
import com.coderdream.autogenvedio.entity.AppBrief;
import com.coderdream.autogenvedio.util.qr.QRCodeEvents;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CutImageAndReadQRUtils {

    public static void main(String[] args) {

        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = BaseUtils.getPath();

        String snapshotPath = path + File.separator + "wechat_snapshot" + File.separator + dateStr + ".png";

        String filename = dateStr;


        List<AppBrief> appBriefList = genImages(SnapshotWechatUtils.appAmount, snapshotPath, path, filename);


        CutPicture.getSnapshotTemp(appBriefList);
    }

    public static List<AppBrief> genImages(int amount, String snapshotPath, String path, String fileName) {
        List<AppBrief> appBriefList = new ArrayList<>();
        AppBrief appBrief;
//        List<String> appUrlList = new ArrayList<>();
        int width;
        int height;
        int x;
        int y;
        int z = 0;
        int qr_baseX = 2090;// 2099;
        int qr_baseY = 826+125 +64; // 826 1502 1038 +464   1081
        int baseX = 1000;
        int oneSize = 663;
        Integer[] lineNumber = new Integer[amount];
        lineNumber[0] = 2;
        lineNumber[1] = 2;
        lineNumber[2] = 2;
        lineNumber[3] = 2;
        lineNumber[4] = 2;
        lineNumber[5] = 2;
        lineNumber[6] = 2;
//        lineNumber[7] = 2;

        Integer[] lineNumberCount = new Integer[amount];
        for(int o = 0; o < amount; o++) {

            lineNumberCount[o] = 0;
        }
//        lineNumberCount[0] = 0;
//        lineNumberCount[1] = 0;
//        lineNumberCount[2] = 0;
//        lineNumberCount[3] = 0;
//        lineNumberCount[4] = 0;
//        lineNumberCount[5] = 0;
//        lineNumberCount[6] = 0;
//        lineNumberCount[7] = 0;
        for (int j= 0; j < amount; j++) {
            if(j == 0) {
                lineNumberCount[j] += lineNumber[0];
            } else {
                lineNumberCount[j] = lineNumber[j] + lineNumberCount[j - 1];
            }
        }

        for (int i = 0; i < amount; i++) {
            z = i + 1;
            File file = new File(snapshotPath);
            if (file.exists()) {
//                System.out.println(snapshotPath);
                x = baseX;// + oneSize * i;// 大：右移
                y = 160; // 大：下移
                width = 250;
                height = 250;
                // 保存应用图标
            //    cut(new File(snapshotPath), x, y, width, height, new File(path + File.separator + String.format("%02d", z) + "_" + "_icon.png"));

                x = 1405;// 大：右移
                y = 160; // 大：下移
                width = 1000;
                height = 1000;
                // 保存应用详情
           //     cut(new File(snapshotPath), x, y, width, height, new File(path + File.separator + String.format("%02d", z) + "_" + "_brief.png"));

                x = qr_baseX;// 大：右移
                y = qr_baseY + oneSize * i + lineNumberCount[i] * 29; // 大：下移
                width = 150;
                height = 180;
                System.out.println(i + " y: " + y);
                // 保存应用详情
                String filePath = path + File.separator + String.format("%02d", z) + "_" + "_qr.png";
                cut(new File(snapshotPath), x, y, width, height, new File(filePath));

                String qrUrl = QRCodeEvents.parseQRCode(filePath);
                System.out.println(qrUrl);
                // 识别二维码，写入App地址列表
//                appUrlList.add(qrUrl);
                appBrief = new AppBrief();
                appBrief.setUrl(qrUrl);
                appBrief.setUrlCn(qrUrl);

                String dateStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                appBrief.setFilename(dateStr);
                appBriefList.add(appBrief);

                x = 1405;// 大：右移
                y = 160; // 大：下移
                width = 1000;
                height = 1000;
                // 保存应用详情
           //     cut(new File(snapshotPath), x, y, width, height, new File(path + File.separator + String.format("%02d", z) + "_" + "_snapshot.png"));
            } else {
                System.out.println("File not exist " + snapshotPath);
            }
        }
        return appBriefList;
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
