package com.coderdream.freeapps.util.translator.text;

import com.coderdream.freeapps.util.other.CdFileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author CoderDream
 */
public class BreakSentenceUtil {

    /*  Configure the local environment:
     * Set the TRANSLATOR_TEXT_SUBSCRIPTION_KEY and TRANSLATOR_TEXT_ENDPOINT environment
     * variables on your local machine using the appropriate method for your
     * preferred shell (Bash, PowerShell, Command Prompt, etc.).
     *
     * For TRANSLATOR_TEXT_ENDPOINT, use the same region you used to get your
     * subscription keys.
     *
     * If the environment variable is created after the application is launched
     * in a console or with Visual Studio, the shell (or Visual Studio) needs to be
     * closed and reloaded to take the environment variable into account.
     */
    private static String subscriptionKey = TextTranslatorConstant.API_KEY; // "<YOUR-TRANSLATOR-KEY>";
    private static String endpoint = "https://api.cognitive.microsofttranslator.com";  // System.getenv("TRANSLATOR_TEXT_ENDPOINT");
    String url = endpoint + "/breaksentence?api-version=3.0&language=en";
//    String url = endpoint + "/breaksentence?api-version=3.0";

    private static String location = TextTranslatorConstant.LOCATION;// "<YOUR-RESOURCE-LOCATION>";

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
            "[{\n\t\"Text\": \"How are you? I am fine. What did you do today?\"\n}]");
        Request request = new Request.Builder()
            .url(url).post(body)
            .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
            .addHeader("Ocp-Apim-Subscription-Region", location)
            .addHeader("Content-type", "application/json").build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String process(String content) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
            .url(url).post(body)
            .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
            .addHeader("Ocp-Apim-Subscription-Region", location)
            .addHeader("Content-type", "application/json").build();
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

    public static List<Integer> parserLen(String json_text) {
        List<Integer> result = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        if (json instanceof JsonArray) {
            JsonArray arr = (JsonArray) json;
            if (arr != null && arr.size() == 1) {
                Object obj = arr.get(0);
                if (obj instanceof JsonObject) {
                    JsonObject jsonObject = (JsonObject) obj;
                    Object objContent = jsonObject.get("sentLen");
                    if (objContent instanceof JsonArray) {
                        JsonArray resultArr = (JsonArray) objContent;
                        if (resultArr != null && resultArr.size() > 0) {
                            for (Object objInt : resultArr) {
//                                System.out.println(objInt.getClass());
                                if (objInt instanceof JsonPrimitive) {
                                    JsonPrimitive jsonPrimitive = (JsonPrimitive) objInt;
                                    if (jsonPrimitive.isNumber()) {
                                        result.add(jsonPrimitive.getAsInt());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return result;
    }

    public static List<String> breakSentence(String text) {
        List<String> result = new ArrayList<>();

        try {
            BreakSentenceUtil breakSentenceRequest = new BreakSentenceUtil();
            String content = "[{\n\t\"Text\": \"" + text + "\"\n}]";
            String response = breakSentenceRequest.process(content);
//            System.out.println(prettify(response));
            List<Integer> indexes = parserLen(response);
            int beginIndex = 0;
            int endIndex = 0;
            for (int i = 0; i < indexes.size(); i++) {
                if (i == 0) {
                    endIndex = indexes.get(i);
                } else {
                    beginIndex = endIndex;// + indexes.get(i - 1);
                    endIndex = beginIndex + indexes.get(i);// - indexes.get(i - 1);
                }
//                System.out.println(beginIndex + ":" + endIndex);
                String tempStr = text.substring(beginIndex, endIndex);
                System.out.println(tempStr);
                result.add(tempStr);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public static void main(String[] args) {
//        try {
//            BreakSentenceUtil breakSentenceRequest = new BreakSentenceUtil();
//            String response = "";
//            breakSentenceRequest.Post();
////            System.out.println(prettify(response));
//
////            String content = "[{\n\t\"Text\": \"How are you? I am fine. What did you do today?\"\n}]";
//
//            String text = "How are you? I am fine. What did you do today?";
//            String content = "[{\n\t\"Text\": \"" + text + "\"\n}]";
//            response = breakSentenceRequest.process(content);
//            System.out.println(prettify(response));
//            parserLen(response);
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        String text = "How are you? I am fine. What did you do today?";
//        breakSentence(text);

        String fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\230914\\script_raw.txt";
        List<String> stringList = CdFileUtils.readFileContent(fileName);
        for (String txt : stringList) {
            breakSentence(txt);
        }
//        text = stringList.stream().map(String::valueOf).collect(Collectors.joining(" "));

//        breakSentence(text);
    }
}
