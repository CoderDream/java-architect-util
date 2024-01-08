package com.coderdream.freeapps.util.bbc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author CoderDream
 */
public class ReadContentByUrl {

    public static void main(String[] args) {

        String urlStr = "https://www.baidu.com";
        readFileByUrl(urlStr);
    }

    public static String readFileByUrl(String urlStr) {
        String res=null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            res = readInputStream(inputStream);
        } catch (Exception e) {
//            logger.error("通过url地址获取文本内容失败 Exception：" + e);
        }
        return res;
    }

    /**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        System.out.println(new String(bos.toByteArray(),"utf-8"));
        return new String(bos.toByteArray(),"utf-8");
    }
}
