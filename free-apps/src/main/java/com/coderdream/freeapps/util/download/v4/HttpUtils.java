package com.coderdream.freeapps.util.download.v4;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * @author CoderDream http相关工具类
 */
public class HttpUtils {

    /**
     * 获取下载文件大小
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static long getHttpFileContentLength(String url, Boolean proxyFlag) throws IOException {
        int contentLength;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = getHttpURLConnection(url, proxyFlag);
            contentLength = httpURLConnection.getContentLength();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return contentLength;
    }

    /**
     * 分块下载
     *
     * @param url      下载地址
     * @param startPos 下载文件起始位置
     * @param endPos   下载文件的结束位置
     * @return
     */
    public static HttpURLConnection getHttpURLConnection(String url, long startPos, long endPos, Boolean proxyFlag) throws IOException {
        HttpURLConnection httpURLConnection = getHttpURLConnection(url, proxyFlag);
//        LogUtils.info("下载的区间是：{}-{}", startPos, endPos);

        if (endPos != 0) {
            httpURLConnection.setRequestProperty("RANGE", "bytes=" + startPos + "-" + endPos);
        } else {
            httpURLConnection.setRequestProperty("RANGE", "bytes=" + startPos + "-");
        }

        return httpURLConnection;
    }

    /**
     * 获取HttpURLConnection链接对象
     *
     * @param url 文件的地址
     * @return
     */
    public static HttpURLConnection getHttpURLConnection(String url, Boolean proxyFlag) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = null;

//        URLConnection conn = null;
        if (proxyFlag) {
            //设置代理 , 端口是你自己使用软件代理的本地出口,socks和http代理的端口
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7890);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address); // http 代理
            httpURLConnection = (HttpURLConnection) httpUrl.openConnection(proxy);
        } else {
           httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        }
//        httpURLConnection.setReadTimeout(10000);
//        httpURLConnection.setConnectTimeout(10000);
//        System.setProperty("http.keepAlive", String.valueOf(false));
//        httpURLConnection.setRequestProperty("Connection", "close");
        //向文件所在的服务器发送标识信息
        httpURLConnection.setRequestProperty("User-Agent",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1");
//        httpURLConnection.connect();
        return httpURLConnection;
    }

    /**
     * 获取下载文件的名字
     *
     * @param url
     * @return
     */
    public static String getHttpFileName(String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }
}
