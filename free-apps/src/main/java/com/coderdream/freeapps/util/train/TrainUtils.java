package com.coderdream.freeapps.util.train;

import com.coderdream.freeapps.model.NewTrain;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class TrainUtils {
    public static List<NewTrain> getmsg(String startCity,String endCity,int isAdult) throws Exception{

        List<NewTrain> trains = new ArrayList<NewTrain>();

        String sstartCity = "";//CityCode.format(startCity);
        String sendCity = "";//CityCode.format(endCity);

//        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        String type = "ADULT";
        if(isAdult == 1){
            type = "0X00";
        }

        String urlStr = "https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes="+type+"&queryDate=2014-04-27&from_station="+sstartCity+"&to_station="+sendCity;

        URL url = new URL(urlStr);

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(ssf);

        InputStreamReader in = new InputStreamReader(con.getInputStream(),"utf-8");

        BufferedReader bfreader = new BufferedReader(in);

        StringBuffer sb = new StringBuffer();

        String line = "";

        while ((line = bfreader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        return null;
    }
}
