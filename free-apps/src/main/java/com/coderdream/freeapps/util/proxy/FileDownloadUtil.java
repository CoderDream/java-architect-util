package com.shwl.navigation.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 多线程下载
 */
public class FileDownloadUtil {

    public static String down(String href, String  path, String  fileName) throws InterruptedException {
        long begin_time = new Date().getTime();
        URL url = null;
        try {
            url = new URL(href);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

//        String fileName = url.getFile();

//        fileName = fileName.substring(fileName.lastIndexOf("/"));
        System.out.println("开始下载>>>");
        int fileSize = conn.getContentLength();
        System.out.println("文件总共大小：" + fileSize + "字节");

        // 设置分块大小
        int blockSize = 1024 * 1024;
        // 文件分块的数量
        int blockNum = fileSize / blockSize;

        if ((fileSize % blockSize) != 0) {
            blockNum += 1;
        }

        System.out.println("分块数->线程数：" + blockNum);

        Thread[] threads = new Thread[blockNum];
        for (int i = 0; i < blockNum; i++) {

            // 匿名函数对象需要用到的变量
            final int index = i;
            final int finalBlockNum = blockNum;
            final String finalFileName = fileName;

            // 创建一个线程
            threads[i] = new Thread() {

                public void run() {
                    URL url = null;
                    try {
                        url = new URL(href);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        // 重新获取连接
                        URLConnection conn = url.openConnection();
                        // 重新获取流
                        InputStream in = conn.getInputStream();
                        // 定义起始和结束点
                        int beginPoint = 0, endPoint = 0;

                        System.out.print("第" + (index + 1) + "块文件：");
                        beginPoint = index * blockSize;

                        // 判断结束点
                        if (index < finalBlockNum - 1) {
                            endPoint = beginPoint + blockSize;
                        } else {
                            endPoint = fileSize;
                        }

                        System.out.println("起始字节数：" + beginPoint + ",结束字节数：" + endPoint);

                        // 将下载的文件存储到一个文件夹中
                        //当该文件夹不存在时，则新建
                        File filePath = new File(path);
                        if (!filePath.exists()) {
                            filePath.mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(
                            new File(path, finalFileName + "_" + (index + 1)));

                        // 跳过 beginPoint个字节进行读取
                        in.skip(beginPoint);
                        byte[] buffer = new byte[1024];
                        int count;
                        // 定义当前下载进度
                        int process = beginPoint;
                        // 当前进度必须小于结束字节数
                        while (process < endPoint) {

                            count = in.read(buffer);
                            // 判断是否读到最后一块
                            if (process + count >= endPoint) {
                                count = endPoint - process;
                                process = endPoint;
                            } else {
                                // 计算当前进度
                                process += count;
                            }
                            // 保存文件流
                            fos.write(buffer, 0, count);
                        }
                        fos.close();
                        in.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            };
            threads[i].start();
        }

        // 当所有线程都结束时才开始文件的合并
        for (Thread t : threads) {
            t.join();
        }

        // 若该文件夹不存在，则创建一个文件夹
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        // 定义文件输出流
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < blockNum; i++) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(path + fileName + "_" + (i + 1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            int count;
            try {
                while ((count = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end_time = new Date().getTime();
        long seconds = (end_time - begin_time) / 1000;
        long minutes = seconds / 60;
        long second = seconds % 60;
        System.out.println("下载完成,用时：" + minutes + "分" + second + "秒");
        return "temp-rainy/" + fileName;
    }

    public static void main(String[] args) {
        try {
            String path = "D:/";
            String fileName =  "fatkun-4c36ca71b3.jpg";
            FileDownloadUtil.down(
                "https://shop-1256119282.file.myqcloud.com/tooools/static/img/index/chrome/fatkun-4c36ca71b3.jpg", path, fileName);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
