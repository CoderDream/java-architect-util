package com.junit;

import static com.coderdream.freeapps.util.other.CdExcelUtil.genProxyInfoList;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.dto.ProxyInfo;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class TestCode {

//    @Test
//    public void test() throws IOException {
//        Document doc = Jsoup.connect("https://www.kanshugee.com/files/article/html/146/146776/38504352.html").get();
//        Elements elements = doc.select("h1");
//        System.out.println((elements.text()).substring(elements.text().indexOf("��")));
//    }

    @Test
    public void test2() {

        List<ProxyInfo> proxyInfos = genProxyInfoList();

        if (CollectionUtils.isNotEmpty(proxyInfos)) {

            for (ProxyInfo proxyInfo : proxyInfos) {
				System.out.println(proxyInfo.toString());
                // 创建代理对象
                String hostname = proxyInfo.getIp();// "61.216.156.222";
                Integer port = proxyInfo.getPort();// 60808;

//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("114.231.82.123", 8888));
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));

                try {
                    // 使用 Jsoup 通过代理抓取网页内容
                    // url = "https://www.zhipin.com/job_detail/62a806a7a0bffe9c1nJ40t68FFM~.html";
                    String url = "https://www.baidu.com";
                    url = "https://www.zhipin.com/job_detail/62a806a7a0bffe9c1nJ40t68FFM~.html";
                    Document document = Jsoup.connect(url)
//				.proxy(proxy) // 设置代理
                        .proxy(hostname, port) // 设置代理
                        .timeout(10000) // 设置超时时间，单位为毫秒
                        .get();

                    // 输出网页标题
                    System.out.println(document.title());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

}
