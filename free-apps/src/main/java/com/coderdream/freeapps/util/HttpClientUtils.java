package com.coderdream.freeapps.util;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;

//import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class HttpClientUtils {

    public static void main(String[] args) {

//        String url = "https://mergeek.com/free/apps?last_id=9LDakA2oeoWzQ48v";
        String url = "https://mergeek.com/free/apps";
        url = "https://sourl.cn/zT2q2i";
        Map<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("last_id", "Q8LDRAkqB7mp40P3");
        JSONObject jsonObject =  HttpClientUtils.getParamMap(url, paramMap);
        System.out.println(jsonObject);
    }

    /**
     * 有参数请求的get请求
     *
     * @param url      请求接口
     * @param paramMap 请求参数Map对象
     * @return
     */
    public static JSONObject getParamMap(String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            List<NameValuePair> pairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            CloseableHttpResponse response;
            URIBuilder builder = new URIBuilder(url).setParameters(pairs);
            // 执行get请求.
            HttpGet httpGet = new HttpGet(builder.build());

            httpGet.setHeader("referer", "https://mergeek.com/free/apps?ref=menu&auth=1&mode=woa");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
            httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpGet.setHeader("Accept-Language","zh-cn,zh;q=0.5");
//            httpGet.setHeader("Host","www.yourdomain.com");
//            httpGet.setHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//            httpGet.setHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            httpGet.setHeader("x-requested-with","XMLHttpRequest"); // :

            response = httpClient.execute(httpGet);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String jsonString = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(jsonString);
                System.out.println("get请求数据成功！\n" + jsonObject);
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 发送post请求，参数用map接收
     *
     * @param url    地址
     * @param object 请求的对象
     * @return 返回值
     */

    public static JSONObject postMap(String url, Object object) {
        //获取json字符串
        String json = JSON.toJSONString(object);
        System.out.println(json);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response;
        try {
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String jsonString = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(jsonString);
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}


