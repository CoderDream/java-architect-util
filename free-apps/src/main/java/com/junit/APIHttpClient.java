package com.junit;

import static com.coderdream.freeapps.util.other.CdExcelUtil.genProxyInfoList;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.dto.ProxyInfo;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 使用httpclient调用API接口
 */
public class APIHttpClient {

    private String useIp;
    private String usePort;

    /*
     * 先初始化 APIHttpClient name=new APIHttpClient(); 直接调用这个函数 Document
     * doc=name.getJsoupDocGet(url);
     *
     */
    public Document getJsoupDocGet(String url) throws ClientProtocolException, IOException {

        Document doc = null;
        Random random = new Random();
        int i = 0;
        while (i < 2 && doc == null) {
            try {
                trustEveryone();
                getRandomIP();
                // System.out.print(useIp);
                // System.out.print(usePort);
                Thread.sleep(random.nextInt(100) + 100);
                doc = Jsoup.connect(url).proxy(useIp, Integer.valueOf(usePort)).header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Referer", "https://www.baidu.com/")
                    .header("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(1000 * 60).get();
                if (doc != null) {
                    System.out.println("代理正确");
                    return doc;
                }
            } catch (Exception e) {

            }
            i++;
        }
        if (doc == null) {
            try {
                Thread.sleep(random.nextInt(100) + 100);
                doc = Jsoup.connect(url).header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Referer", "https://www.baidu.com/")
                    .header("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(1000 * 60).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return doc;
    }

    /*
     * 从一个或者几个独享IP中进行随机获取
     */
    public void getRandomIP() throws ClientProtocolException, IOException {
        List<ProxyInfo> proxyInfos = genProxyInfoList();
        Map<String, String> ipAndPort = new HashMap<String, String>();
        ipAndPort.clear();
//        ipAndPort.put("IP号", "端口");// 添加独享IP，还可以多put几个

        if (CollectionUtils.isNotEmpty(proxyInfos)) {

            for (ProxyInfo proxyInfo : proxyInfos) {
                System.out.println(proxyInfo.toString());
                // 创建代理对象
                String ip = proxyInfo.getIp();// "61.216.156.222";
                Integer port = proxyInfo.getPort();// 60808;
                ipAndPort.put(ip, port + "");// 添加独享IP，还可以多put几个
            }
        }

        String[] keys = ipAndPort.keySet().toArray(new String[0]);
        Random random = new Random();

        int index = random.nextInt(keys.length);

        useIp = keys[index];
        usePort = ipAndPort.get(useIp);
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
