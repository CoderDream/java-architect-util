package com.coderdream.freeapps.util.bbc;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Description : 谷歌翻译  描述
 * @program: demo      程序
 * @ClassName Translate.java       类名
 * @author: Mr.Wang               作者
 * @date: 2021-12-16 14:50 // 生成时间
 **/
public class Translate {

    // 谷歌地址
    private static final String PATH="https://translate.googleapis.com/translate_a/single";
    private static final String CLIENT="gtx";

    private static final String USER_AGENT="Mozilla/5.0";

    private static final Map<String,String> LANGUAGE_MAP=new HashMap();

    private static Translate _instance = null;


    public static void main(String[] args) throws Exception {
        System.out.println("-----开始翻译-----");
        String txt="今天有点大呦";
        Translate g = Translate.getInstance();
        String translateText = g.translateText(txt, "auto", "en");
        System.out.println(translateText);
        System.out.println( g.translateText(translateText,"auto","zh_cn"));
    }


    /**
     * 获取单例
     * @return
     */
    public static Translate getInstance() {
        if( null == _instance){
            _instance = new Translate();
            _instance.init();
        }
        return _instance;
    }

    /**
     * 初始化语言类
     */
    private void init(){
        LANGUAGE_MAP.put("auto","Automatic");
        LANGUAGE_MAP.put("af","Afrikaans");
        LANGUAGE_MAP.put("sq","Albanian");
        LANGUAGE_MAP.put("am","Amharic");
        LANGUAGE_MAP.put("ar","Arabic");
        LANGUAGE_MAP.put("hy","Armenian");
        LANGUAGE_MAP.put("az","Azerbaijani");
        LANGUAGE_MAP.put("eu","Basque");
        LANGUAGE_MAP.put("be","Belarusian");
        LANGUAGE_MAP.put("bn","Bengali");
        LANGUAGE_MAP.put("bs","Bosnian");
        LANGUAGE_MAP.put("bg","Bulgarian");
        LANGUAGE_MAP.put("ca","Catalan");
        LANGUAGE_MAP.put("ceb","Cebuano");
        LANGUAGE_MAP.put("ny","Chichewa");
        LANGUAGE_MAP.put("zh_cn","Chinese Simplified");
        LANGUAGE_MAP.put("zh_tw","Chinese Traditional");
        LANGUAGE_MAP.put("co","Corsican");
        LANGUAGE_MAP.put("hr","Croatian");
        LANGUAGE_MAP.put("cs","Czech");
        LANGUAGE_MAP.put("da","Danish");
        LANGUAGE_MAP.put("nl","Dutch");
        LANGUAGE_MAP.put("en","English");
        LANGUAGE_MAP.put("eo","Esperanto");
        LANGUAGE_MAP.put("et","Estonian");
        LANGUAGE_MAP.put("tl","Filipino");
        LANGUAGE_MAP.put("fi","Finnish");
        LANGUAGE_MAP.put("fr","French");
        LANGUAGE_MAP.put("fy","Frisian");
        LANGUAGE_MAP.put("gl","Galician");
        LANGUAGE_MAP.put("ka","Georgian");
        LANGUAGE_MAP.put("de","German");
        LANGUAGE_MAP.put("el","Greek");
        LANGUAGE_MAP.put("gu","Gujarati");
        LANGUAGE_MAP.put("ht","Haitian Creole");
        LANGUAGE_MAP.put("ha","Hausa");
        LANGUAGE_MAP.put("haw","Hawaiian");
        LANGUAGE_MAP.put("iw","Hebrew");
        LANGUAGE_MAP.put("hi","Hindi");
        LANGUAGE_MAP.put("hmn","Hmong");
        LANGUAGE_MAP.put("hu","Hungarian");
        LANGUAGE_MAP.put("is","Icelandic");
        LANGUAGE_MAP.put("ig","Igbo");
        LANGUAGE_MAP.put("id","Indonesian");
        LANGUAGE_MAP.put("ga","Irish");
        LANGUAGE_MAP.put("it","Italian");
        LANGUAGE_MAP.put("ja","Japanese");
        LANGUAGE_MAP.put("jw","Javanese");
        LANGUAGE_MAP.put("kn","Kannada");
        LANGUAGE_MAP.put("kk","Kazakh");
        LANGUAGE_MAP.put("km","Khmer");
        LANGUAGE_MAP.put("ko","Korean");
        LANGUAGE_MAP.put("ku","Kurdish (Kurmanji)");
        LANGUAGE_MAP.put("ky","Kyrgyz");
        LANGUAGE_MAP.put("lo","Lao");
        LANGUAGE_MAP.put("la","Latin");
        LANGUAGE_MAP.put("lv","Latvian");
        LANGUAGE_MAP.put("lt","Lithuanian");
        LANGUAGE_MAP.put("lb","Luxembourgish");
        LANGUAGE_MAP.put("mk","Macedonian");
        LANGUAGE_MAP.put("mg","Malagasy");
        LANGUAGE_MAP.put("ms","Malay");
        LANGUAGE_MAP.put("ml","Malayalam");
        LANGUAGE_MAP.put("mt","Maltese");
        LANGUAGE_MAP.put("mi","Maori");
        LANGUAGE_MAP.put("mr","Marathi");
        LANGUAGE_MAP.put("mn","Mongolian");
        LANGUAGE_MAP.put("my","Myanmar (Burmese)");
        LANGUAGE_MAP.put("ne","Nepali");
        LANGUAGE_MAP.put("no","Norwegian");
        LANGUAGE_MAP.put("ps","Pashto");
        LANGUAGE_MAP.put("fa","Persian");
        LANGUAGE_MAP.put("pl","Polish");
        LANGUAGE_MAP.put("pt","Portuguese");
        LANGUAGE_MAP.put("ma","Punjabi");
        LANGUAGE_MAP.put("ro","Romanian");
        LANGUAGE_MAP.put("ru","Russian");
        LANGUAGE_MAP.put("sm","Samoan");
        LANGUAGE_MAP.put("gd","Scots Gaelic");
        LANGUAGE_MAP.put("sr","Serbian");
        LANGUAGE_MAP.put("st","Sesotho");
        LANGUAGE_MAP.put("sn","Shona");
        LANGUAGE_MAP.put("sd","Sindhi");
        LANGUAGE_MAP.put("si","Sinhala");
        LANGUAGE_MAP.put("sk","Slovak");
        LANGUAGE_MAP.put("sl","Slovenian");
        LANGUAGE_MAP.put("so","Somali");
        LANGUAGE_MAP.put("es","Spanish");
        LANGUAGE_MAP.put("su","Sundanese");
        LANGUAGE_MAP.put("sw","Swahili");
        LANGUAGE_MAP.put("sv","Swedish");
        LANGUAGE_MAP.put("tg","Tajik");
        LANGUAGE_MAP.put("ta","Tamil");
        LANGUAGE_MAP.put("te","Telugu");
        LANGUAGE_MAP.put("th","Thai");
        LANGUAGE_MAP.put("tr","Turkish");
        LANGUAGE_MAP.put("uk","Ukrainian");
        LANGUAGE_MAP.put("ur","Urdu");
        LANGUAGE_MAP.put("uz","Uzbek");
        LANGUAGE_MAP.put("vi","Vietnamese");
        LANGUAGE_MAP.put("cy","Welsh");
        LANGUAGE_MAP.put("xh","Xhosa");
        LANGUAGE_MAP.put("yi","Yiddish");
        LANGUAGE_MAP.put("yo","Yoruba");
        LANGUAGE_MAP.put("zu","Zulu");
    }
    /**
     * 判断语言是否支持
     * @param language
     * @return
     */
    public boolean isSupport(String language){
        if( null == LANGUAGE_MAP.get( language )){
            return false;
        }
        return true;
    }

    /** 获取 语言代码
     * ISO 639-1 code
     * @param desiredLang 语言
     * @return 如果返回null则标示不支持
     */
    public String getCode(String desiredLang){
        if( null != LANGUAGE_MAP.get(desiredLang)){
            return desiredLang;
        }
        String tmp = desiredLang.toLowerCase();
        for(Map.Entry<String, String> enter : LANGUAGE_MAP.entrySet() ){
            if( enter.getValue().equals( tmp)){
                return enter.getKey();
            }
        }

        return null;
    }


    /**
     * 翻译文本
     * @param text  文本内容
     * @param sourceLang  文本所属语言。如果不知道，可以使用auto
     * @param targetLang  目标语言。必须是明确的有效的目标语言
     * @return
     * @throws Exception
     */
    public String translateText(String text,String sourceLang, String targetLang) throws Exception{


        String retStr="";
        if( !( isSupport(sourceLang) || isSupport(targetLang) ) ){
            throw new Exception("不支持的语言类型");
        }

        List<NameValuePair> nvps = new ArrayList();
        nvps.add(new BasicNameValuePair("client", CLIENT));
        nvps.add(new BasicNameValuePair("sl", sourceLang));
        nvps.add(new BasicNameValuePair("tl", targetLang));
        nvps.add(new BasicNameValuePair("dt", "t"));
        nvps.add(new BasicNameValuePair("q", text));

        String resp =  postHttp( PATH,nvps);
        if( null == resp ){
            throw  new Exception("网络异常");
        }

        JSONArray jsonObject = JSONArray.parseArray( resp );
        for (Iterator<Object> it = jsonObject.getJSONArray(0).iterator(); it.hasNext(); ) {
            JSONArray a = (JSONArray) it.next();
            retStr += a.getString(0);
        }

        return retStr;
    }


    /**
     * post 请求
     * @param url 请求地址
     * @param nvps 参数列表
     * @return
     * @throws
     */
    private   String postHttp( String url ,List<NameValuePair> nvps){
        String responseStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost( url );
        //重要！！必须设置 http 头，否则返回为乱码
        httpPost.setHeader("User-Agent",USER_AGENT);
        CloseableHttpResponse response2 = null;
        try {
            // 重要！！ 指定编码，对中文进行编码
            httpPost.setEntity( new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8"))  );
            response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            responseStr = EntityUtils.toString(entity2);
            EntityUtils.consume(entity2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response2) {
                try {
                    response2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( null != httpclient ){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }


}
