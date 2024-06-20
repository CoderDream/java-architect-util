package com.coderdream.freeapps.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class NeteaseMusicApi {

    private static final String API_URL = "https://api.imjad.cn/cloudmusic/?type=detail&id=";

    public static void main(String[] args) {
        String songId = "32957955"; // 替换为实际的歌曲ID
        try {
            int favoritesCount = getFavoritesCount(songId);
            System.out.println("Favorites Count: " + favoritesCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getFavoritesCount(String songId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(API_URL + songId);

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        JsonObject song = jsonObject.getAsJsonObject("songs").getAsJsonArray("data").get(0).getAsJsonObject();
        int favoritesCount = song.get("st").getAsInt();

        httpClient.close();
        return favoritesCount;
    }
}
