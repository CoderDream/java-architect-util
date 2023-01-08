package com.feng.echartsUtils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpUtil
 * @Description TODO
 * @Author admin
 * @Date 2021/4/6 14:34
 * @Version 1.0
 */
public class HttpUtil {
    /**
     * @Author fengfanli
     * @Description //TODO post 请求
     * @Date 16:09 2021/4/8
     * @Param [url, params, charset]
     * @return java.lang.String
     **/
    public static String setPost(String url, Map<String, String> params, String charset)
            throws IOException {
        String responseEntity = "";
        // 创建CloseableHttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 生成请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        // 将参数添加到post请求中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
        // 发送请求，获取结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        // 获取响应实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换结果实体为String类型
            responseEntity = EntityUtils.toString(entity, charset);
        }
        // 释放资源
        EntityUtils.consume(entity);
        response.close();
        return responseEntity;
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @Author fengfanli
     * @Description //TODO 获取二维码
     * @Date 17:00 2021/3/31
     * @Param [url, content]
     * @return java.io.InputStream
     **/
    public static InputStream sendPostForErWeiMa(String url, String content) {
        URLConnection conn = null;
        try {
            conn = new URL(url).openConnection();
            StringBuffer sb = null;
            InputStream inputStream = null;
            if (conn != null) {
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Cache-Control", "no-cache");
                conn.setRequestProperty("Content-Length", "" + content.length());
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Connection", "Keep-Alive");
                //app-870f9b3f1be74affabe4c2a5761b0cf8
                DataOutputStream stream = new DataOutputStream(conn.getOutputStream());
                stream.write(content.getBytes("UTF-8"));
                stream.close();
                inputStream = conn.getInputStream();
            }
            return inputStream;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
