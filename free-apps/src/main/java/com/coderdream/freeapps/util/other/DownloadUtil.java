package com.coderdream.freeapps.util.other;


import com.coderdream.freeapps.util.bbc.BbcConstants;
import com.coderdream.freeapps.util.proxy.HtmlUtil;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class DownloadUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(
        DownloadUtil.class);

    public static void main(String[] args) {
//        String[] pictureUrls = new String[]{
//            "https://is5-ssl.mzstatic.com/image/thumb/Purple125/v4/9d/7d/7d/9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa/pr_source.jpg/600x0w.jpg"};
//
//        // https://mmbiz.qpic.cn/mmbiz_png/kDfQpHqFP1mgR11uFTibicswEPdb8UA5zwuU9Eic8V81SlDAe4n1ib9gNqlX7ZL90HwpbQ2KRFq1YOZ9hB5X0SN87w/640?wx_fmt=png
//        pictureUrls = new String[]{
//            "https://mmbiz.qpic.cn/mmbiz_png/kDfQpHqFP1mgR11uFTibicswEPdb8UA5zwuU9Eic8V81SlDAe4n1ib9gNqlX7ZL90HwpbQ2KRFq1YOZ9hB5X0SN87w/640?wx_fmt=png"};
//        String path = "id1443533088";
//        path = "2023-02-13_05";
//        String filename = path + ".png";
//        String filenameQr = path + "_qr.png";
//        String[] fileNames = new String[]{"9d7d7d1f-001f-ff93-46aa-7adfef8fdaaa_pr_source.jpg"};
//        fileNames = new String[]{filename};
//        DownloadUtil.downloadPicture(pictureUrls, path, fileNames);
//        File srcImageFile = new File(path + "/" + filename);
//        File destImageFile = new File(path + "/" + filenameQr);
//        CutImageUtils.cutForQr(srcImageFile, destImageFile);
//        String qrUrl = QRCodeEvents.parseQRCode(path + "/" + filenameQr);
//        System.out.println(qrUrl);
        String pageUrl = "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english/ep-200102";


        String path = "D:\\14_LearnEnglish\\6MinuteEnglish\\2020\\200102\\";


        String filename = "200102.html";
        Boolean proxyFlag = true;
        DownloadUtil.downloadPageToFile(pageUrl, path, filename, proxyFlag);
    }

    /**
     * @param pictureUrls 多个图片地址     path 图片下载存放目录  fileNames 多个文件名称 数量与图片地址数量保持一致
     * @return void
     * @throws
     * @Title: downloadPicture
     * @Description: 下载图片
     */
    public static void downloadFile(String[] pictureUrls, String path, String[] fileNames) {
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
     * @param pictureUrls 多个图片地址     path 图片下载存放目录  fileNames 多个文件名称 数量与图片地址数量保持一致
     * @return void
     * @throws
     * @Title: downloadPicture
     * @Description: 下载图片
     */
    public static void downloadFile(String[] pictureUrls, String path, String[] fileNames, Boolean proxyFlag) {
        try {
            //多个图片下载地址
            for (int i = 0; i < pictureUrls.length; i++) {
                //根据图片地址构建URL
                URL url = new URL(pictureUrls[i]);
                URLConnection conn = null;
                if (proxyFlag) {
                    //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
                    InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7890);
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
                    conn = url.openConnection(proxy);
                } else {
                    conn = url.openConnection();
                }

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

    public static void downloadFile(String[] pictureUrls, String[] paths, String[] fileNames, Boolean proxyFlag) {
        try {
            //多个图片下载地址
            for (int i = 0; i < pictureUrls.length; i++) {
                //根据图片地址构建URL
                URL url = new URL(pictureUrls[i]);
                URLConnection conn = null;
                if (proxyFlag) {
                    //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
                    InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7890);
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
                    conn = url.openConnection(proxy);
                } else {
                    conn = url.openConnection();
                }

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.connect();
                DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
                //创建目录和图片
                File pathFile = new File(paths[i]);
                File file = new File(paths[i] + File.separator + fileNames[i]);
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
                System.out.println(paths[i] + "\t|\t" + fileNames[i] + "\t|\t" + pictureUrls[i]);
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
    public static void downloadFile(String pictureUrl, String path, String filename) {
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

                    logger.info(" =========== 创建文件夹 " + path);
                    pathFile.mkdirs();
                    file.createNewFile();
                }
                if (!file.exists()) {
                    logger.info(" =========== 创建文件 " + filename);
                    file.createNewFile();
                } else {
                    continue;
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
                logger.info(" =========== 操作成功" + pictureUrl);
            } catch (Exception e) {
                if (tryTime < tryTimes) {
                    logger.info(" =========== 图片下载失败，重试：" + tryTime);
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

    public static void downloadFile(String fileUrl, String path, String filename, Boolean proxyFlag) {
        int tryTimes = 5;
        int tryTime = 0;
        boolean tryFlag = false;
        do {
            tryTime++;
            try {
                //根据图片地址构建URL
                URL url = new URL(fileUrl);
                URLConnection conn = null;
                if (proxyFlag) {
                    //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
                    InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7890);
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
                    conn = url.openConnection(proxy);
                } else {
                    conn = url.openConnection();
                }
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                System.setProperty("http.keepAlive", String.valueOf(false));
                conn.setRequestProperty("Connection", "close");
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
                Integer period = new Random().nextInt(2000) + 300;
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


    public static String downloadPageToFile(String pageUrl, String path, String filename, Boolean proxyFlag) {
        //转化数据流变数据本
        StringBuilder sb1 = new StringBuilder();
        int tryTimes = 5;
        int tryTime = 0;
        boolean tryFlag = false;
        do {
            tryTime++;
            try {
                //设置请求访问的地址
                URL url = new URL(pageUrl);

                //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
                URLConnection conn = null;
                if (proxyFlag) {
                    //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
                    InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7890);
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
                    conn = url.openConnection(proxy);
                } else {
                    conn = url.openConnection();
                }
                //此处是浏览器的请求头. 一般是为了防止对面设置的反爬
                conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
                conn.connect();

                //连接获取数据流
                InputStream inputStream = conn.getInputStream();

//            byte[] buffer = new byte[1024*1024*8*10*10];
                byte[] buffer = new byte[1024 * 1024 * 8 * 10];
                int len;
                int index = 0;
                List<String> contentList = new ArrayList<>();
                String tempStr = "";
                while ((len = inputStream.read(buffer)) != -1) {
//                    System.out.println("len: " + len);
                    tempStr = new String(buffer, 0, len, "UTF-8");
//                System.out.println("tempStr: \t" + tempStr);
                    sb1.append(tempStr);
                    contentList.add(tempStr);
                    index++;
//                    System.out.println("#################: " + index);
                }
                //输出文本至屏幕
//            System.out.println(sb1);
                String name = pageUrl.substring(pageUrl.lastIndexOf("/") + 1);
                String fileNameCn = BbcConstants.ROOT_FOLDER_NAME + name + ".html";
                fileNameCn = path + filename;
                // 写中文翻译文本
//            CdFileUtils.writeToFile(fileNameCn, contentList);
                CdFileUtils.writeToFile(fileNameCn, Arrays.asList(sb1.toString()));

                Integer period = new Random().nextInt(1000) + 300;
                try {
                    Thread.sleep(period);   // 休眠3秒
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }

            } catch (Exception e) {
                if (tryTime < tryTimes) {
                    logger.info(" =========== 图片下载失败，重试：" + tryTime);
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
        return sb1.toString();
    }

    public static void getBbcPageInfoDetailByHtml(String pageUrl, String path, String filename,
        Boolean proxyFlag) {
        DownloadUtil.downloadPageToFile(pageUrl, path, filename, proxyFlag);
        HtmlUtil.genScriptRawByHtml(path, filename);
    }
}
