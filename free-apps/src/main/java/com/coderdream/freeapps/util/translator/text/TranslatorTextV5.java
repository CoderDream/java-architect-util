package com.coderdream.freeapps.util.translator.text;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 *在不翻译的情况下获取句子长度
 * Translator 服务还允许使用 breaksentence 终结点请求句子长度，无需翻译。
 * </pre>
 *
 * @author CoderDream
 */
public class TranslatorTextV5 {
    private static String key = TextTranslatorConstant.API_KEY; // "<YOUR-TRANSLATOR-KEY>";
    public static String endpoint = "https://api.cognitive.microsofttranslator.com";
    public static String route = "/dictionary/examples?api-version=3.0&from=en&to=es"; // "/dictionary/examples?api-version=3.0&from=en&to=zh-Hans";

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
            "[{\"Text\": \"sunlight\", \"Translation\": \"luz solar\"}]");
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
            TranslatorTextV5 dictionaryExamplesRequest = new TranslatorTextV5();
            String response = dictionaryExamplesRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
