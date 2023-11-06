package com.coderdream.freeapps.util.tts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Authentication {
//    @Resource
//    private RedisCache redisCache;
//
//    public String genAccessToken() {
//        InputStream inSt;
//        HttpsURLConnection webRequest;
//
//        try {
//            String accessToken = redisCache.get(RedisKey.KEY_TTS_ACCESS_TOKEN);
//            if (StringUtils.isEmpty(accessToken)) {
//                webRequest = HttpsConnection.getHttpsConnection(TtsConst.ACCESS_TOKEN_URI);
//                webRequest.setDoInput(true);
//                webRequest.setDoOutput(true);
//                webRequest.setConnectTimeout(5000);
//                webRequest.setReadTimeout(5000);
//                webRequest.setRequestMethod("POST");
//
//                byte[] bytes = new byte[0];
//                webRequest.setRequestProperty("content-length", String.valueOf(bytes.length));
//                webRequest.setRequestProperty("Ocp-Apim-Subscription-Key", TtsConst.API_KEY);
//                webRequest.connect();
//
//                DataOutputStream dop = new DataOutputStream(webRequest.getOutputStream());
//                dop.write(bytes);
//                dop.flush();
//                dop.close();
//
//                inSt = webRequest.getInputStream();
//                InputStreamReader in = new InputStreamReader(inSt);
//                BufferedReader bufferedReader = new BufferedReader(in);
//                StringBuilder strBuffer = new StringBuilder();
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    strBuffer.append(line);
//                }
//
//                bufferedReader.close();
//                in.close();
//                inSt.close();
//                webRequest.disconnect();
//
//                accessToken = strBuffer.toString();
//                //设置accessToken的过期时间为9分钟
//                redisCache.set(RedisKey.KEY_TTS_ACCESS_TOKEN, accessToken, TtsConst.ACCESS_TOKEN_EXPIRE_TIME);
//                log.info("New tts access token {}", accessToken);
//            }
//            return accessToken;
//        } catch (Exception e) {
//            log.error("Generate tts access token failed {}", e.getMessage());
//        }
//        return null;
//    }
}
