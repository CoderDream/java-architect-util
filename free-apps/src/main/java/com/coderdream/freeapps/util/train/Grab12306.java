package com.coderdream.freeapps.util.train;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * @author AZHELL E-mail:r776379772@Gmail.com
 * @version 创建时间：2017年11月6日 下午8:28:42
 * @ClassDescription 类说明:
 */
public class Grab12306 {
    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            SslUtils.ignoreSsl();
            HttpGet httpGet =  new HttpGet("https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2023-04-28&leftTicketDTO.from_station=WCN&leftTicketDTO.to_station=HAN&purpose_codes=ADULT");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity,  "UTF-8");
            System.out.println(content);
            Gson gson =  new Gson();
//            Object12306 object12306 = gson.fromJson(content, Object12306.class);
//            // System.out.println(object12306);
//
//            // 处理得到的对象
//            Data data = object12306.getData();
//            List<String> result = data.getResult();
//            for (String schedule : result) {
//                // System.out.println(string);
//                // 分隔符函数一定记得转义的重要习惯
//                String[] array =  schedule.split( "\\|");
//                for (int i = 0; i < array.length; i++) {
//                    System.out.println(array[i]);
//                }
//            }
        }  catch (ClientProtocolException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
