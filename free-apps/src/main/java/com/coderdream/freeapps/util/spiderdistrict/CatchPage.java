package com.coderdream.freeapps.util.spiderdistrict;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class CatchPage {

    //根据网页的Url获取网页Document
    public Document catchDocument(String url) throws IOException {
        try {
            return Jsoup.connect(url)
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(5000).get();
            //如果出现了超时问题就继续抓取
        } catch (SocketTimeoutException s) {
            return Jsoup.connect(url)
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(5000).get();
        }
    }
}
