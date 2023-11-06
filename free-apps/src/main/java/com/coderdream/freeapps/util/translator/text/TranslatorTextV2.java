package com.coderdream.freeapps.util.translator.text;

import java.io.IOException;

import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 *
 *   在翻译过程中检测源语言
 *      如果未在翻译请求中包含 from 参数，则翻译器服务会尝试检测源文本的语言。
 *      在响应中，你将获得检测到的语言 (language) 和置信度分数 (score)。
 *      score 越接近 1.0，意味着检测结果正确的置信度越高。
 * </pre>
 *
 * @author CoderDream
 */
public class TranslatorTextV2 {
    private static String key =  TextTranslatorConstant.API_KEY;// "<YOUR-TRANSLATOR-KEY>";
    public String endpoint = "https://api.cognitive.microsofttranslator.com";
    public String route = "/translate?api-version=3.0&to=en&to=it";
    public String url = endpoint.concat(route);

    // location, also known as region.
// required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
    private static String location = TextTranslatorConstant.LOCATION;// "<YOUR-RESOURCE-LOCATION>";

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
            "[{\"Text\": \"Halo, rafiki! Ulifanya nini leo?\"}]");
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Ocp-Apim-Subscription-Key", key)
            // location required if you're using a multi-service or regional (not global) resource.
            .addHeader("Ocp-Apim-Subscription-Region", location)
            .addHeader("Content-type", "application/json")
            .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static void main(String[] args) {
        try {
            TranslatorTextV2 translateRequest = new TranslatorTextV2();
            String response = translateRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
