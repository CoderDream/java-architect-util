package com.coderdream.freeapps.util.other;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupWechatUtilV2 {

    //获取文章封面图片
    public static String getCoverUrl(String informationUrl) throws IOException {
        String picUrl = null;
        int flag;
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        String htmlString = doc.toString();
        flag = htmlString.indexOf("msg_cdn_url");
        while (htmlString.charAt(flag) != '\"') {
            flag++;
        }
        int beginIndex = ++flag;
        while (htmlString.charAt(flag) != '\"') {
            flag++;
        }
        int endIndex = --flag;
        picUrl = htmlString.substring(beginIndex, endIndex);
        return picUrl;
    }

    //获取公众号名称
    public static String getName(String informationUrl) throws IOException {
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        Element names = doc.getElementById("js_name");
        String name = names.text();
        return name;
    }

    //获取文章时间
    public static String getTime(String informationUrl) throws IOException {
        String time = null;
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        Elements scripts = doc.select("script");
        for (Element script : scripts) {
            String html = script.html();
            if (html.contains("document.getElementById(\"publish_time\")")) {
                int fromIndex = html.indexOf("s=\"");
                time = html.substring(fromIndex + 3, fromIndex + 13);
                return time;
            }
        }
        return time;
    }

    //获取文章标题
    public static String getTitle(String informationUrl) throws IOException {
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        Elements titles = doc.getElementsByClass("rich_media_title");
        String title = titles.text();
        return title;
    }

    //获取公众号
    public static String getOfficialAccount(String informationUrl) throws IOException {
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        Elements metaValues = doc.getElementsByClass("profile_meta_value");
        String account = metaValues.get(0).text();
        return account;
    }

    //获取公众号文章内容
    public static String getContent(String informationUrl) throws IOException {
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        Element metaValues = doc.getElementById("js_content");
        //String content = metaValues.html();//此行获取HTML
        String content = metaValues.text();
        return content;
    }

    //获取公众号真实链接
    public static String getTureUrl(String informationUrl) throws IOException {
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        String tureUrl = doc.select("meta[property=og:url]").get(0).attr("content");
        return tureUrl;
    }

    //获取公众号作者
    public static String getAuthor(String informationUrl) throws IOException {
        Document doc = Jsoup.connect(informationUrl).timeout(3000).get();
        String tureUrl = doc.select("meta[property=og:article:author]").get(0).attr("content");
        return tureUrl;
    }

    public static void main(String[] args) throws IOException {
        String url = "https://mp.weixin.qq.com/s/f927osOgrZImWvWLzuOSpg";
        System.out.println(getTime(url));
        System.out.println(getTitle(url));
        System.out.println(getOfficialAccount(url));
        System.out.println(getContent(url));
        System.out.println(getAuthor(url));
    }
}

