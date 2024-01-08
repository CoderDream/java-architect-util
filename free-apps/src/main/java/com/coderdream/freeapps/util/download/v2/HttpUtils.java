package com.coderdream.freeapps.util.download.v2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author CoderDream http相关工具类
 */
public class HttpUtils {

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
