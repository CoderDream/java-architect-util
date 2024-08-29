package com.coderdream.freeapps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class MaiTianTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://www.zhipin.com/job_detail/1e62322779fbc3831HN70tW7EFBY.html";
        Document document = Jsoup.parse(new URL(url), 30000);//获取到了document对象

        Element ele = document.getElementById("J_goodsList");

        Elements elements = ele.getElementsByTag("li");
        for (Element element : elements) {
            //eq(0)--获取当前第一个元素; text()---获取文本
            String price = element.getElementsByClass("p-price").eq(0).text();//书的价格
            String title = element.getElementsByClass("p-name").eq(0).text();//书的标题

            System.out.println("=================");
            System.out.println(price);
            System.out.println(title);
        }
    }
}

