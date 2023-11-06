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
 *     获取句子长度
 * 使用 Translator 服务，你可以获取一个句子或一系列句子的字符计数。
 * 响应以数组的形式返回，包含检测到的每个句子的字符计数。 可以通过 translate 和 breaksentence 终结点获取句子长度。
 *
 * 在翻译过程中获取句子长度
 * 可以使用 translate 终结点获取源文本和翻译输出的字符计数。
 * 若要返回句子长度（srcSenLen 和 transSenLen），必须将 includeSentenceLength 参数设置为 True。
 * </pre>
 *
 * @author CoderDream
 */
public class TranslatorTextV3 {
    private static String key = TextTranslatorConstant.API_KEY; // "<YOUR-TRANSLATOR-KEY>";
    public static String endpoint = "https://api.cognitive.microsofttranslator.com";
    public static String route = "/translate?api-version=3.0&to=es&includeSentenceLength=true";
    public static String url = endpoint.concat(route);

    // location, also known as region.
    // required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
    private static String location = TextTranslatorConstant.LOCATION;// "<YOUR-RESOURCE-LOCATION>";

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
            "[{\"Text\": \"Can you tell me how to get to Penn Station? Oh, you aren\'t sure? That\'s fine.\"}]");
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
            TranslatorTextV3 translateRequest = new TranslatorTextV3();
            String response = translateRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
