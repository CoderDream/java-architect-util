package com.coderdream.freeapps.util.boss;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;

public class BossCookiesNoLogin {
    public static void main(String[] args) {
        // 创建cookie存储空间
        CookieStore cookieStore = new BasicCookieStore();

        // 创建HttpClient
        HttpClient client = HttpClients.custom()
            .setDefaultCookieStore(cookieStore)
            .build();

        try {
            // 以GET方式访问Boss直聘首页，此URL须更改为实际网址
            HttpGet httpGet = new HttpGet("https://www.zhipin.com/");
            HttpResponse httpResponse = client.execute(httpGet);

            // 输出状态码
            System.out.println("Status Code: " + httpResponse.getStatusLine().getStatusCode());

            // 输出访问首页后获取的Cookie
            for (org.apache.http.cookie.Cookie c : cookieStore.getCookies()) {
                System.out.println(c.getName() + ": " + c.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
