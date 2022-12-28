package com.example.demo.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.net.URI;

public class HttpUtil {

    public static HttpResponse executeHttpGet(String url) {
        int timeoutConnection = 3000;
        int timeoutSocket = 3000;
        HttpParams httpParameters = null;
        HttpClient client = null;
        HttpGet request = null;
        try {
            httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            client = new DefaultHttpClient(httpParameters);
            request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            //LogUtils.e(TAG_TAG, "executeHttpGet: error=" + e.toString(), e);
            return null;
        } finally {
            httpParameters = null;
            client = null;
            request = null;
        }
    }

}
