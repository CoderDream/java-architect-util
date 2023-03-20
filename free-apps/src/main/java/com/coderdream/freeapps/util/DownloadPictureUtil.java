package com.coderdream.freeapps.util;


import com.coderdream.freeapps.service.impl.AppServiceImpl;
import com.coderdream.freeapps.util.qr.QRCodeEvents;
import org.slf4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class DownloadPictureUtil {


    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(
        DownloadPictureUtil.class);

    public static void main(String[] args) {
        String[] pictureUrls = new String[]{
            "https://is5-ssl.mzstatic.com/image/thumb/Purple125/v4/9d/7d/7d/9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa/pr_source.jpg/600x0w.jpg"};

        // https://mmbiz.qpic.cn/mmbiz_png/kDfQpHqFP1mgR11uFTibicswEPdb8UA5zwuU9Eic8V81SlDAe4n1ib9gNqlX7ZL90HwpbQ2KRFq1YOZ9hB5X0SN87w/640?wx_fmt=png
        pictureUrls = new String[]{
            "https://mmbiz.qpic.cn/mmbiz_png/kDfQpHqFP1mgR11uFTibicswEPdb8UA5zwuU9Eic8V81SlDAe4n1ib9gNqlX7ZL90HwpbQ2KRFq1YOZ9hB5X0SN87w/640?wx_fmt=png"};
        String path = "id1443533088";
        path = "2023-02-13_05";
        String filename = path + ".png";
        String filenameQr = path + "_qr.png";
        String[] fileNames = new String[]{"9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa_pr_source.jpg"};
        fileNames = new String[]{filename};
        DownloadPictureUtil.downloadPicture(pictureUrls, path, fileNames);
        File srcImageFile = new File(path + "/" + filename);
        File destImageFile = new File(path + "/" + filenameQr);
        CutImageUtils.cutForQr(srcImageFile, destImageFile);
        String qrUrl = QRCodeEvents.parseQRCode(path + "/" + filenameQr);
        System.out.println(qrUrl);
    }

    /**
     * @param pictureUrls 多个图片地址     path 图片下载存放目录  fileNames 多个文件名称 数量与图片地址数量保持一致
     * @return void
     * @throws
     * @Title: downloadPicture
     * @Description: 下载图片
     */
    public static void downloadPicture(String[] pictureUrls, String path, String[] fileNames) {
        try {
            //多个图片下载地址
            for (int i = 0; i < pictureUrls.length; i++) {
                //根据图片地址构建URL
                URL url = new URL(pictureUrls[i]);
                URLConnection conn = url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.connect();
                DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
                //创建目录和图片
                File pathFile = new File(path);
                File file = new File(path + File.separator + fileNames[i]);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                    file.createNewFile();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                //通过流复制图片
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                dataInputStream.close();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pictureUrl 多个图片地址     path 图片下载存放目录  fileNames 多个文件名称 数量与图片地址数量保持一致
     * @return void
     * @throws
     * @Title: downloadPicture
     * @Description: 下载图片
     */
    public static void downloadPicture(String pictureUrl, String path, String filename) {
        int tryTimes = 5;
        int tryTime = 0;
        boolean tryFlag = false;
        do {
            tryTime++;
            try {
                //根据图片地址构建URL
                URL url = new URL(pictureUrl);
                URLConnection connection = url.openConnection();
//                conn.setReadTimeout(10000);
//                conn.setConnectTimeout(10000);
//                conn.connect();
                InputStream inputStream = connection.getInputStream();
                //创建目录和图片
                File pathFile = new File(path);
                File file = new File(path + File.separator + filename);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                    file.createNewFile();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                //通过流复制图片
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] bs = new byte[1024000];
                int len = 0;
                while ((len = inputStream.read(bs)) != -1) {
                    outputStream.write(bs, 0, len);
                }
                inputStream.close();
                outputStream.close();
                logger.info(" =========== 操作成功");
            } catch (Exception e) {
                if (tryTime < tryTimes) {
                    tryFlag = true;
                }
                e.printStackTrace();
                logger.info("========== 图片下载失败");
                Integer period = new Random().nextInt(1000) + 300;
                try {
                    Thread.sleep(period);   // 休眠3秒
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }
            }
        } while (tryFlag);
    }


    public static void downloadPictureBackup(String pictureUrl, String path, String filename) {
        int tryTimes = 5;
        int tryTime = 0;
        boolean tryFlag = false;
        do {
            tryTime++;
            try {
                //根据图片地址构建URL
                URL url = new URL(pictureUrl);
                URLConnection conn = url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.connect();
                DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
                //创建目录和图片
                File pathFile = new File(path);
                File file = new File(path + File.separator + filename);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                    file.createNewFile();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                //通过流复制图片
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                dataInputStream.close();
                fileOutputStream.close();
                Integer period = new Random().nextInt(1000) + 300;
                try {
                    Thread.sleep(period);   // 休眠3秒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                if (tryTime < tryTimes) {
                    tryFlag = true;
                }
                e.printStackTrace();
                continue;
            }
            continue;
        } while (tryFlag);
    }
}
