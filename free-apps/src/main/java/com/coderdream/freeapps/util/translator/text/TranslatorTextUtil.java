package com.coderdream.freeapps.util.translator.text;

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
 * <pre>
 *     翻译文本
 * Translator 服务的核心操作是翻译文本。 在本部分，你将生成一个请求，该请求采用单个源 (from) 并提供两个输出 (to)。 然后，我们将查看一些参数，这些参数可用于调整请求和响应。
 *
 *
 * </pre>
 *
 * @author CoderDream
 */
public class TranslatorTextUtil {

    private static String key = TextTranslatorConstant.API_KEY;// "<YOUR-TRANSLATOR-KEY>";
    public String endpoint = "https://api.cognitive.microsofttranslator.com";

//    public String endpoint = "https://trans4cd.cognitiveservices.azure.com/";

    // 语言支持：https://learn.microsoft.com/zh-cn/azure/ai-services/translator/language-support
    public String route = "/translate?api-version=3.0&from=en&to=zh-Hans"; // "/translate?api-version=3.0&from=en&to=sw&to=it";
    public String url = endpoint.concat(route);

    // location, also known as region.
    // required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
    private static String location = TextTranslatorConstant.LOCATION;// "<YOUR-RESOURCE-LOCATION>";

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String process(String text) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
            "[{\"Text\": \"" + text + "\"}]");
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

    public static List<String> parserTrans(String json_text) {
        List<String> result = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        if (json instanceof JsonArray) { // translations -> {JsonArray@1903} "[{"text":"嘿，朋友！今天你做什么了？","to":"zh-Hans"}]"
            JsonArray arr = (JsonArray) json;
            if (arr != null && arr.size() == 1) {
                Object obj = arr.get(0);
                if (obj instanceof JsonObject) {
                    JsonObject jsonObject = (JsonObject) obj;
                    Object objContent = jsonObject.get("translations");
                    if (objContent instanceof JsonArray) {
                        JsonArray resultArr = (JsonArray) objContent;
                        if (resultArr != null && resultArr.size() == 1) {
//                            for (Object objInt : resultArr) {
////                                System.out.println(objInt.getClass());
//                                if (objInt instanceof JsonPrimitive) {
//                                    JsonPrimitive jsonPrimitive = (JsonPrimitive) objInt;
//                                    if (jsonPrimitive.isNumber()) {
//                                        result.add(jsonPrimitive.getAsInt());
//                                    }
//                                }
//                            }
                            Object objTextMap = resultArr.get(0);
                            if (objTextMap instanceof JsonObject) {
                                JsonObject jsonObjectText = (JsonObject) objTextMap;
                                Object objText = jsonObjectText.get("text");
                                if (objText instanceof JsonPrimitive) {
                                    JsonPrimitive jsonPrimitive = (JsonPrimitive) objText;
                                    if (jsonPrimitive.isString()) {
                                        result.add(jsonPrimitive.getAsString());
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

    public static List<String> translatorText(String text) {
        List<String> result = new ArrayList<>();
        try {
            TranslatorTextUtil translatorTextUtil = new TranslatorTextUtil();

            String response = translatorTextUtil.process(text);
//            System.out.println(prettify(response));
            List<String> contextList = parserTrans(response);
            result.addAll(contextList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public static void main(String[] args) {
//        String content = "Hello, friend! What did you do today?";
//        List<String> stringList = new ArrayList<>(); // translatorText(content);
//        for (String str : stringList) {
//            System.out.println(str);
//        }
//
//        String text = "How are you? I am fine. What did you do today?";
////        breakSentence(text);
//
//        String fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\230914\\script_raw.txt";
//        stringList = CdFileUtils.readFileContent(fileName);
//
//        text = stringList.stream().map(String::valueOf).collect(Collectors.joining("   "));
//        List<String> stringList1 = translatorText(text);
//        for (String str : stringList1) {
//            System.out.println(str);
//        }

//        String str = "6分钟英语 中级水平 结交男性朋友 第 230914 集 / 14 Sep 2023 简介 人们通常认为男性在交朋友和保持朋友方面比女性差得多。这有多真实？尼尔和贝丝讨论了这个问题，并教你一些有用的词汇。     本周的问题 根据牛津大学认知人类学研究所的数据，我们的心理健康需要多少亲密的朋友？     是：a）五个吗？  b） 十个？  或者，c）二十？     听节目听听答案。     词汇时间是生活中麻烦、不快乐或经济困难的艰难时期 度过（短语动词） 设法度过困境 渐行渐远 渐行渐远 与某人越来越远，直到你与他们的关系破裂 比利 无伴侣（俚语） 一个没有朋友的人 双刃剑 有不利和有利后果的东西 外出非常友好;喜欢与人见面和交谈 成绩单 注意：这不是逐字逐句的成绩单。     尼尔你好。这是BBC学习英语的6分钟英语。我是尼尔。     贝丝，我是贝丝。有一句著名的英语谚语，“有需要的朋友确实是朋友”，这是真的 - 每个人都需要朋友来分享生活的起起落落。你有很多朋友吗，尼尔？     尼尔 是的，我有一些亲密的朋友，但也许没有我想要的那么多。     贝丝 这很有趣，因为通常是女性有很多朋友，而男性发现更难保持牢固的友谊，尤其是随着年龄的增长。事实上，根据最近的一项调查，只有27%的英国男性表示他们至少有六个亲密的朋友。     尼尔 那么，男人真的很难交到朋友吗？我们将听到马克斯·迪金斯（Max Dickins）的来信，他是一本关于男性友谊的新书的作者，名为“比利无伴侣”，像往常一样，我们也将学习一些有用的新词汇。     贝丝 但首先我有一个问题要问你。我们知道亲密的朋友很重要，不仅是为了玩得开心，也是为了良好的心理健康。因此，根据牛津大学认知人类学研究所的研究，我们的心理健康需要多少亲密的朋友？是：a）五个吗？     b） 十个？ 或者，c）二十？     尼尔：我会说我们至少需要五个亲密的朋友。     贝丝 好的，尼尔。我将在程序结束时透露答案。现在，无论你有多少朋友，人们都认为女性在结交和保持亲密朋友方面比男性更好。以下是克劳迪娅·哈蒙德（Claudia Hammond）为BBC Radio 4节目“All in the Mind”概述了问题：克劳迪娅·哈蒙德（Claudia Hammond） 现在，当时代艰难时，朋友往往是帮助我们度过难关的人，他们在那里倾听，安慰，也许会建议我们，如果这是我们想要的。那么，为什么我们有时会发现很难交到朋友，或者我们曾经拥有的朋友似乎已经以某种方式消失了？现在，有一种观点认为，女性更善于维持友谊，男性更有可能与周围的任何人交往，而不是培养这些关系，我们想知道这是否真的是真的，或者这只是一种刻板印象？     尼尔·克劳迪娅（Neil Claudia）使用“时代艰难”一词来描述麻烦，不快乐或经济困难的情况。朋友帮助我们度过了生活中的这些困难时期。短语动词，通过，有几个含义，但在这里它意味着设法度过一段不愉快的时期。     贝丝：问题可能是你的朋友已经渐行渐远——逐渐远离，直到你与他们的联系中断。对于男性来说尤其如此。     尼尔 没错。当作家马克斯·迪金斯（Max Dickins）结婚时，他意识到自己没有任何亲密的男性朋友可以要求成为他的“伴郎”，即在婚礼上帮助新郎的人。这促使他写了一本名为《比利无伴侣》（Billy No-Mates）的书，研究为什么他没有任何亲密的男性朋友。这是克劳迪娅·哈蒙德再次与马克斯谈论 BBC Radio 4 节目，一切都在心中：克劳迪娅·哈蒙德·马克斯有趣的是，如果你愿意的话，你有点公开了这件事......你的书甚至被称为“比利无伴侣”，你知道，我们很多人都害怕成为的东西。决定公开说这句话并不容易......     马克斯·迪金斯 不，这是一把真正的双刃剑，是一本名为“比利无伴侣”的书的面孔，我不得不说......但我认为..所以寂寞看起来不像我。我30岁出头到中期，我很外向，我很快就买了我的回合，它应该不像我，但越来越像我。所以寂寞不再只是老年人，而是年轻人......     贝丝·麦克斯（Beth Max）称他的书为《比利无伴侣》（Billy No-Mates），这是对没有朋友的人的俚语。这是一个令人难忘的书名，但马克斯说，成为一本名为“比利无伴侣”的书的公众面孔是一把双刃剑 - 既有不利的后果，也有有利的后果。     尼尔 事实上，马克斯看起来不像一个没有朋友的人：他年轻、慷慨、外向——这是一个形容词，形容一个友好且喜欢结识新朋友的人。但是，孤独感越来越多地影响着年轻男性，这在一定程度上要归功于社交媒体，这似乎每个人都在和他们的伴侣一起度过愉快的时光，除了你！     贝丝·马克斯（Beth Max）认为，答案是走出去，在“第三空间”与人会面，比如体育俱乐部或阅读小组，这些地方与家庭或工作分开。     尼尔 所有这些都有助于更接近良好心理健康所需的神奇朋友数量。我想是时候透露你问题的答案了，贝丝。     贝丝 是的，我问我们需要多少亲密的朋友来维持我们的心理健康。你说是五个，那是...正确答案！根据牛津大学的罗宾·邓巴教授的说法，我们需要一个由五个亲密朋友组成的核心圈子，再加上一个由大约十个人组成的更广泛的支持网络，为了良好的心理健康，总共有十五个朋友。好的，让我们回顾一下节目中的词汇，从短语“时代很艰难”开始，它描述了生活中的麻烦或困难时期。     尼尔 如果你度过了难关，你就能度过一个艰难的境地。     贝丝 渐行渐远意味着逐渐远离某人，直到你和他们的关系最终结束。     Neil Billy No-Mates是没有朋友的人的俚语。     贝丝 一把双刃剑描述了既有不利后果又有利后果的东西。     尼尔 最后，形容词外向描述了一个非常友好并喜欢与人交谈的人。再一次，我们的六分钟结束了。暂时再见！     贝丝再见！";
//        String[] tempList = str.split("   ");
//        if (tempList != null && tempList.length > 0) {
//            for (String s : tempList) {
//
//                String[] tempList2 = s.split(" ");
//                if (tempList2 != null && tempList2.length > 0) {
//                    for (String s2 : tempList2) {
//                        System.out.println(s2);
//                    }
//                } else {
//                    System.out.println(s);
//                }
//            }
//        }
    }
}
