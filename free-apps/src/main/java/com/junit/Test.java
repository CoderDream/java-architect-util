package com.junit;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;

public class Test {
    public static void main(String args[]) throws ClientProtocolException, IOException {
        APIHttpClient test = new APIHttpClient();
        int i = 0;
        Document document;
        while (true) {
            String url = "https://www.zhipin.com/job_detail/62a806a7a0bffe9c1nJ40t68FFM~.html";
//            document = test.getJsoupDocGet("https://cn.imslp.org/wiki/Apolo_y_Dafne_(Dur%C3%B3n%2C_Sebasti%C3%A1n)");
            document = test.getJsoupDocGet(url);
            i++;
            System.out.println("第：" + i);
            if (document == null) {
                System.err.println("第：" + i + "次error!");
                break;
            } else {
                System.out.println(document);
            }
        }
    }
}
