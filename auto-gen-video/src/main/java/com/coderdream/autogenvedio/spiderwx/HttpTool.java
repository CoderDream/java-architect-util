package com.coderdream.autogenvedio.spiderwx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * Httpclient工具类
 *
 * @author sunnyzyq
 * @since 2019/04/22
 */
public class HttpTool {

    private static final int BYTE_LEN = 102400; // 100KB
    private static final String CHARSET = "UTF-8";  // 编码格式

    /**
     * get请求
     * @param url 请求地址（get请求时参数自己组装到url上）
     * @return 响应文本
     */
    public static String get(String url) {
        // 请求地址，以及参数设置
        HttpGet get = new HttpGet(url);
        // 执行请求，获取相应
        return getRespString(get);
    }

    /**
     * get请求
     * @param url 请求地址（get请求时参数自己组装到url上）
     * @param headerMap 请求头
     * @return 响应文本
     */
    public static String get(String url, Map<String, String> headerMap) {
        // 请求地址，以及参数设置
        HttpGet get = new HttpGet(url);
        if (headerMap != null) {
            for (Entry<String, String> entry : headerMap.entrySet()) {
                get.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求，获取相应
        return getRespString(get);
    }

    /**
     * post 请求
     * @param url 请求地址
     * @param params 请求参数
     * @return 响应文本
     */
    public static String post(String url, Map<String, String> params){
        // 构建post请求
        HttpPost post = new HttpPost(url);
        // 构建请求参数
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (params != null) {
            for (Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(pairs, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(entity);
        // 执行情趣，获取相应
        return getRespString(post);
    }

    /**
     * 文件上传
     * @param url 请求地址
     * @param params 请求参数 （文件类型须为File）
     * @return 响应文本
     */
    public static String postFile(String url, Map<String, Object> params) {
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (params != null) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value == null) {
                    builder.addPart(key, new StringBody("",ContentType.TEXT_PLAIN));
                    continue;
                }
                if (value instanceof File) {
                    builder.addPart(key, new FileBody((File) value));
                } else {
                    builder.addPart(key, new StringBody(value.toString(), ContentType.TEXT_PLAIN));
                }
            }
        }
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        return getRespString(post);
    }

    /**
     * 文件下载
     */
    public static void getFile(String url, String name) {
        // 图片地址
        HttpGet get = new HttpGet(url);
        // 执行请求，获取响应流
        InputStream in = getRespInputStream(get);
        // InputStream 转 File，保存在当前工程中
        File file = new File(name);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte b[] = new byte[BYTE_LEN];
            int j = 0;
            while( (j = in.read(b)) != -1){
                fos.write(b, 0, j);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取响应信息（String）
     */
    public static String getRespString(HttpUriRequest request) {
        // 获取响应流
        InputStream in = getRespInputStream(request);

        StringBuilder sb = new StringBuilder();
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = sb.toString();
        return str;
    }

    /**
     * 获取响应信息（InputStream）
     */
    public static InputStream getRespInputStream(HttpUriRequest request) {
        // 获取响应对象
        HttpResponse response = null;
        try {
            response = HttpClients.createDefault().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        // 获取Entity对象
        HttpEntity entity = response.getEntity();
        // 获取响应信息流
        InputStream in = null;
        if (entity != null) {
            try {
                in =  entity.getContent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return in;
    }
}
