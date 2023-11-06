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
 *     翻译文本
 * Translator 服务的核心操作是翻译文本。 在本部分，你将生成一个请求，该请求采用单个源 (from) 并提供两个输出 (to)。 然后，我们将查看一些参数，这些参数可用于调整请求和响应。
 *
 *
 * </pre>
 * @author CoderDream
 */
public class TranslatorTextV1 {
    private static String key =  TextTranslatorConstant.API_KEY;// "<YOUR-TRANSLATOR-KEY>";
    public String endpoint = "https://api.cognitive.microsofttranslator.com";

//    public String endpoint = "https://trans4cd.cognitiveservices.azure.com/";

    // 语言支持：https://learn.microsoft.com/zh-cn/azure/ai-services/translator/language-support
    public String route = "/translate?api-version=3.0&from=en&to=lzh&to=zh-Hans"; // "/translate?api-version=3.0&from=en&to=sw&to=it";
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
            "[{\"Text\": \"Hello, friend! What did you do today?\"}]");
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
            TranslatorTextV1 translateRequest = new TranslatorTextV1();
            String response = translateRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
