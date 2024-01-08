package com.coderdream.freeapps.util.other;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author CoderDream
 */
public class MultiThreadDownloadUtil {


    static int ThreadCount = 3; //线程的个数
    static String path = "https://zenlayer.dl.sourceforge.net/project/qbittorrent/qbittorrent-win32/qbittorrent-4.5.5/qbittorrent_4.5.5_x64_setup.exe";//  "http://192.168.0.102:8080/QQ.exe"; //确定下载地址

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //发送get请求，请求这个地址的资源
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200) {
                //获取到请求资源文件的长度
                int length = conn.getContentLength();
                File file = new File("QQ.exe");
                //创建随机存储文件
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //设置临时文件的大小
                raf.setLength(length);
                //关闭raf
                raf.close();
                //计算出每一个线程下载多少字节

                int size = length / MultiThreadDownloadUtil.ThreadCount;

                for (int i = 0; i < MultiThreadDownloadUtil.ThreadCount; i++) {
                    //startIndex,endIndex分别代表线程的开始和结束位置
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    if (i == ThreadCount - 1) {
                        //如果是最后一个线程，那么结束位置写死
                        endIndex = length - 1;
                    }
                    System.out.println("线程" + i + "的下载区间是" + startIndex + "到" + endIndex);
                    new DownLoadThread(startIndex, endIndex, i).start(); //创建线程下载数据
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

class DownLoadThread extends Thread {

    int startIndex;
    int endIndex;
    int threadId;

    public DownLoadThread(int startIndex, int endIndex, int threadId) {
        super();
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        //使用http请求下载安装包文件
        URL url;
        try {
            url = new URL(MultiThreadDownloadUtil.path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //设置请求数据的区间
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
            //请求部分数据的响应码是206
            if (conn.getResponseCode() == 206) {
                //获取一部分数据来读取
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                int total = 0;
                //拿到临时文件的引用
                File file = new File("QQ.exe");
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //更新文件的写入位置，startIndex
                raf.seek(startIndex);
                while ((len = is.read(b)) != -1) {
                    //每次读取流里面的数据，同步吧数据写入临时文件
                    raf.write(b, 0, len);
                    total += len;
                    System.out.println("线程" + threadId + "下载了" + total);
                }
                System.out.println("线程" + threadId + "下载过程结束===========================");
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ;
}
