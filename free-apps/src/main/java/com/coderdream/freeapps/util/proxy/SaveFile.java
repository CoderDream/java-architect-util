package com.coderdream.freeapps.util.proxy;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.http.client.methods.HttpGet;

/**
 * ＜p＞Description: 将指定的HTTP网络资源在本地以文件形式存放＜/p＞
 **/
public class SaveFile {

    public final static boolean DEBUG = true; //调试用
    private static int BUFFER_SIZE = 8096; //缓冲区大小
    private Vector vDownLoad = new Vector(); //URL列表
    private Vector vFileList = new Vector(); //下载后的保存文件名列表

    /**
     * 　* 清除下载列表
     */
    public void resetList() {
        vDownLoad.clear();
        vFileList.clear();
    }

    /**
     * 　* 增加下载列表项 　* 　* @param url String 　* @param filename String
     */

    public void addItem(String url, String filename) {
        vDownLoad.add(url);
        vFileList.add(filename);
    }

    /**
     * 　* 根据列表下载资源
     */
    public void downLoadByList() {
        String url = null;
        String filename = null;

//按列表顺序保存资源
        for (int i = 0; i < vDownLoad.size(); i++) {
            url = (String) vDownLoad.get(i);
            filename = (String) vFileList.get(i);

            try {
                saveToFile(url, filename);
            } catch (IOException err) {
                if (DEBUG) {
                    System.out.println("资源[" + url + "]下载失败!!!");
                }
            }
        }

        if (DEBUG) {
            System.out.println("下载完成!!!");
        }
    }

    /**
     * 将HTTP资源另存为文件
     *
     * @param destUrl  String
     * @param fileName String
     * @throws Exception
     */
    public static void saveToFile(String destUrl, String fileName) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

//建立链接
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();
//连接指定的资源
        httpUrl.connect();
//获取网络输入流
        bis = new BufferedInputStream(httpUrl.getInputStream());
//建立文件
        fos = new FileOutputStream(fileName);

        if (DEBUG) {
            System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件[" +
                fileName + "]");
        }
//保存文件
        while ((size = bis.read(buf)) != -1) {
            fos.write(buf, 0, size);
        }

        fos.close();
        bis.close();
        httpUrl.disconnect();
    }


    /**
     * 将HTTP资源另存为文件
     *
     * @param destUrl  String
     * @param fileName String
     * @throws Exception
     */
    public void saveToFile2(String destUrl, String fileName) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
//建立链接
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();

//           String authString = "username" + ":" + "password";
        String authString = "50301" + ":" + "88888888";
        String auth = "Basic ";// +
//            new sun.misc.BASE64Encoder().encode(authString.getBytes());
        httpUrl.setRequestProperty("Proxy-Authorization", auth);

//连接指定的资源
        httpUrl.connect();
//获取网络输入流
        bis = new BufferedInputStream(httpUrl.getInputStream());
//建立文件
        fos = new FileOutputStream(fileName);

        if (DEBUG) {
            System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件[" +
                fileName + "]");
        }
//保存文件
        while ((size = bis.read(buf)) != -1) {
            fos.write(buf, 0, size);
        }

        fos.close();
        bis.close();
        httpUrl.disconnect();
    }

    /**
     * 设置代理服务器
     *
     * @param proxy     String
     * @param proxyPort String
     */
    public void setProxyServer(String proxy, String proxyPort) {
//设置代理服务器
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", proxy);
        System.getProperties().put("proxyPort", proxyPort);
    }

    public void setAuthenticator(String uid, String pwd) {
//        Authenticator.setDefault(new MyAuthenticator());
    }

    /**
     * 主方法(用于测试)
     *
     * @param argv String[]
     */
    public static void main(String argv[]) {
        HttpGet oInstance = new HttpGet();
        try {
//    //增加下载列表（此处用户可以写入自己代码来增加下载列表）
//   oInstance.addItem("http://www.ebook.com/java/网络编程001.zip","./网络编程1.zip");//
//     oInstance.addItem("http://www.ebook.com/java/网络编程002.zip","./网络编程2.zip");
//     oInstance.addItem("http://www.ebook.com/java/网络编程003.zip","./网络编程3.zip");
//     //开始下载
//     oInstance.downLoadByList();
            SaveFile.saveToFile("https://img95.699pic.com/photo/50089/8326.jpg_wh860.jpg", "8326.jpg_wh860.jpg");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
