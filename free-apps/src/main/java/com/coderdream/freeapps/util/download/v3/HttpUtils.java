package com.coderdream.freeapps.util.download.v3;

import java.io.IOException;
import java.net.HttpURLConnection;
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
    public static long getHttpFileContentLength(String url) throws IOException {
        int contentLength;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = getHttpURLConnection(url);
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
    public static HttpURLConnection getHttpURLConnection(String url, long startPos, long endPos) throws IOException {
        HttpURLConnection httpURLConnection = getHttpURLConnection(url);
        LogUtils.info("下载的区间是：{}-{}", startPos, endPos);

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
    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        //向文件所在的服务器发送标识信息
        httpURLConnection.setRequestProperty("User-Agent",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1");
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
