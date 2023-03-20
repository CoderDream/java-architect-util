package com.coderdream.freeapps.util;

public class TtsConst {
    /**
     * 音频合成类型（亲测这种效果最佳，其他的你自己去试试）
     */
    public static final String AUDIO_24KHZ_48KBITRATE_MONO_MP3 = "audio-24khz-48kbitrate-mono-mp3";
    /**
     * 授权url
     */
    public static final String ACCESS_TOKEN_URI = "https://eastasia.api.cognitive.microsoft.com/sts/v1.0/issuetoken";
    /**
     * api key
     */
    public static final String API_KEY = "你自己的 api key";
    /**
     * 设置accessToken的过期时间为9分钟
     */
    public static final Integer ACCESS_TOKEN_EXPIRE_TIME = 9 * 60;
    /**
     * 性别
     */
    public static final String MALE = "Male";
    /**
     * tts服务url
     */
    public static final String TTS_SERVICE_URI = "https://eastasia.tts.speech.microsoft.com/cognitiveservices/v1";

}
