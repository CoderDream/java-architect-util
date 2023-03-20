package com.coderdream.freeapps.component;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpsConnection {

    public static HttpsURLConnection getHttpsConnection(String connectingUrl) throws Exception {

        URL url = new URL(connectingUrl);
        return (HttpsURLConnection) url.openConnection();
    }
}
