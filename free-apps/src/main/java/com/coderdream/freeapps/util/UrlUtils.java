package com.coderdream.freeapps.util;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class UrlUtils {

    public static void main(String[] args) {
        String shortUrl = "https://sourl.cn/zT2q2i";
        String redirectUrl = UrlUtils.tranAppUrl(shortUrl);
        System.out.println(redirectUrl);
    }

    public static String tranAppUrl(String shortUrl) {
        //
        String redirectUrl = null;
        HttpClient client = new DefaultHttpClient();
        HttpParams params = client.getParams();
        params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        HttpContext context = new BasicHttpContext();
        HttpGet get = new HttpGet(shortUrl);
        try {
            HttpResponse response = client.execute(get, context);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                Header[] headers = response.getHeaders("Location");
                if (headers != null && headers.length > 0) {
                    redirectUrl = headers[0].getValue();
//                    System.out.println(redirectUrl);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redirectUrl;
    }

}
