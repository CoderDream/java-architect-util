package com.junit;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Random;


public class JsoupDemo {

    // 代理隧道验证信息

    final static String ProxyUser = "yunfeng.li";

    final static String ProxyPass = "N@601910259095";


    // 代理服务器
    final static String ProxyHost = "proxy.neusoft.com";

    final static Integer ProxyPort = 8080;


    // 设置IP切换头
    final static String ProxyHeadKey = "Proxy-Tunnel";

    public static Document getUrlProxyContent(String url) {

        Authenticator.setDefault(new Authenticator() {

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ProxyUser, ProxyPass.toCharArray());
            }

        });

        // 设置Proxy-Tunnel
        Random random = new Random();
        int tunnel = random.nextInt(10000);
        String ProxyHeadVal = String.valueOf(tunnel);

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyHost, ProxyPort));
        Document doc = null;

        try {
//            HttpsUtil.trustEveryone();
            // 处理异常、其他参数
            doc = Jsoup.connect(url).ignoreContentType(true).timeout(3000).header(ProxyHeadKey, ProxyHeadVal).proxy(proxy).get();
            if (doc != null) {
                System.out.println(doc.body().html());
            }
        } catch (IOException e) {

            e.printStackTrace();

        }

        return doc;
    }


    public static void main(String[] args) throws Exception {

        // 要访问的目标页面

        //String targetUrl = "http://httpbin.org/ip";
        //String targetUrl = "https://3g.dxy.cn/newh5/view/pneumonia";
        //String targetUrl = "http://httpbin.org/";
        String targetUrl = "https://zhuanlan.zhihu.com/p/113856433";


        getUrlProxyContent(targetUrl);
    }
}

