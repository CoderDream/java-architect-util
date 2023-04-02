package com.coderdream.freeapps.mstts;

import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

@Slf4j
public class MSTTS implements Closeable {

    private TTSConfig ttsConfig = new TTSConfig();
    private WebSocketClient wsClient = null;
    private Map<String, Listen> stringListenMap = new HashMap<>();
    private boolean isRely = false;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


    private void init() throws URISyntaxException, InterruptedException {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String urlStr =
            "wss://eastus.api.speech.microsoft.com/cognitiveservices/websocket/v1?TrafficType=AzureDemo&Authorization=bearer%20undefined&X-ConnectionId="
                + uuid;
        log.info("ws url {}", urlStr);
        wsClient = new WebSocketClient(new URI(urlStr)) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                log.info("ws client is open");
                isRely = true;
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                AudioMp3Part part = new AudioMp3Part(bytes);
                String xRequestId = part.getXRequestId();
                if(stringListenMap.get(xRequestId) != null) {
                    stringListenMap.get(xRequestId).mp3Part(part);
                } else {
                    log.error("listenHashMap.get(xRequestId) is null \t" + xRequestId);
                }
            }

            @Override
            public void onMessage(String message) {
                AudioMessage audioMessage = new AudioMessage(message);
                if ("turn.end".equals(audioMessage.getPath())) {
                    if(stringListenMap.get(audioMessage.getXRequestId()) != null) {
                        stringListenMap.get(audioMessage.getXRequestId()).end(message);
                    } else {
                        log.error("stringListenMap.get(audioMessage.getXRequestId()) is null \t" + audioMessage.getXRequestId());
                    }
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                log.info("onclose:" + s);
                isRely = false;
            }

            @Override
            public void onError(Exception e) {
                log.warn("onError", e);
            }
        };
        addHeads(this.wsClient);
        this.wsClient.connect();
        int i = 0;
        while (!this.wsClient.isOpen()) {
            if (i++ > 10) {
                throw new RuntimeException("ws connect timeout");
            }
            Thread.sleep(1000);
        }
        isRely = true;
    }

    private void addHeads(WebSocketClient client2) {
        client2.addHeader("Origin", "https://azure.microsoft.com");

        client2.addHeader("User-Agent",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:102.0) Gecko/20100101 Firefox/102.0");
        client2.addHeader("Accept", "*/*");
        client2.addHeader("Accept-Language",
            "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        //        client2.addHeader("Accept-Encoding", "gzip, deflate, br");
        //        client2.addHeader("Sec-WebSocket-Version", "13");
        //        client2.addHeader("Sec-WebSocket-Extensions", "permessage-deflate");
        //        client2.addHeader("Sec-WebSocket-Key", "HdTKboPwS0X+XxrNeXVamg==");
        //        client2.addHeader("Connection", "keep-alive, Upgrade");
        //        client2.addHeader("Cookie",
        //                "MC1=GUID=6eacedea1f0046c0a91820f833a07a18&HASH=6eac&LV=202302&V=4&LU=1677545768620; at_check=true; ARRAffinity=701b8b96823e4ea815297366750426b952e25e21364dea3444cef740ecd9d66e; ARRAffinitySameSite=701b8b96823e4ea815297366750426b952e25e21364dea3444cef740ecd9d66e");
        //        client2.addHeader("Sec-Fetch-Dest", "websocket");
        //        client2.addHeader("Sec-Fetch-Mode", "websocket");
        //        client2.addHeader("Sec-Fetch-Site", "same-site");
        //        client2.addHeader("Sec-GPC", "1");
        //        client2.addHeader("Pragma", "no-cache");
        //        client2.addHeader("Cache-Control", "no-cache");
        client2.addHeader("Upgrade", "websocket");
    }

    public byte[] gen(String text) {
        if (!isRely() || !isOpen()) {
            try {
                this.close();
                this.init();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        CountDownLatch latch = new CountDownLatch(1);
        String requestID = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        Listen dlisent = new Dlisent(requestID, latch);
        stringListenMap.put(requestID, dlisent);
        sendText(wsClient, text);
        send1(wsClient, requestID);
        send2(wsClient, requestID);
        send3(wsClient, requestID, text);
        try {
            latch.await(20, TimeUnit.SECONDS);
            byte[] mp3 = dlisent.getMP3();
            stringListenMap.remove(requestID);
            return mp3;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRely() {
        return isRely;
    }

    public boolean isOpen() {
        return wsClient != null && wsClient.isOpen();
    }

    public class Dlisent implements Listen {

        private String requestId;
        CountDownLatch latch;

        private AudioMp3 audioMp3 = new AudioMp3();

        public Dlisent(String requestId, CountDownLatch countDownLatch) {
            this.requestId = requestId;
            this.latch = countDownLatch;
        }

        @Override
        public void mp3Part(AudioMp3Part part) {
            audioMp3.add(part);
        }

        @Override
        public void end(String message) {
            latch.countDown();
        }

        @Override
        public byte[] getMP3() {
            return audioMp3.getMp3Byte();
        }
    }

    private void sendText(WebSocketClient client, String text) {
        String requestID = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        send1(client, requestID);
        send2(client, requestID);
        send3(client, requestID, text);
    }

    private void send1(WebSocketClient client, String requestID) {
        String timestamp = dateFormat.format(new Date());
        //        String test= "Path: speech.config\r\nX-RequestId: "+requestID+"\r\nX-Timestamp: "+timestamp+"\r\nContent-Type: application/json\r\n\r\n{\"context\":{\"system\":{\"name\":\"SpeechSDK\",\"version\":\"1.19.0\",\"build\":\"JavaScript\",\"lang\":\"JavaScript\",\"os\":{\"platform\":\"Browser/Linux x86_64\",\"name\":\"Mozilla/5.0 (X11; Linux x86_64; rv:78.0) Gecko/20100101 Firefox/78.0\",\"version\":\"5.0 (X11)\"}}}}";

        String test =
            "Path: speech.config\r\n" + "X-RequestId: " + requestID + "\r\n" + "X-Timestamp: "
                + timestamp + "\r\n" + "Content-Type: application/json\r\n" + "\r\n"
                + "{\"context\":{\"system\":{\"name\":\"SpeechSDK\",\"version\":\"1.19.0\",\"build\":\"JavaScript\",\"lang\":\"JavaScript\"},\"os\":{\"platform\":\"Browser/Linux x86_64\",\"name\":\"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:102.0) Gecko/20100101 Firefox/102.0\",\"version\":\"5.0 (X11)\"}}}";
        log.debug("send1:{}", test);
        client.send(test);
    }

    private void send2(WebSocketClient client, String requestID) {
        String timestamp = dateFormat.format(new Date());
        //        String test= "Path: synthesis.context\r\nX-RequestId: "+requestID+"\r\nX-Timestamp: "+timestamp+"\r\nContent-Type: application/json\r\n\r\n{\"synthesis\":{\"audio\":{\"metadataOptions\":{\"sentenceBoundaryEnabled\":false,\"wordBoundaryEnabled\":false},\"outputFormat\":\"audio-24khz-160kbitrate-mono-mp3\"}}}";
        String test = "Path: synthesis.context\r\n" + "X-RequestId: " + requestID + "\r\n"
            + "X-Timestamp: " + timestamp + "\r\n" + "Content-Type: application/json\r\n"
            + "\r\n"
            + "{\"synthesis\":{\"audio\":{\"metadataOptions\":{\"bookmarkEnabled\":false,\"sentenceBoundaryEnabled\":false,\"visemeEnabled\":false,\"wordBoundaryEnabled\":false},\"outputFormat\":\"audio-24khz-96kbitrate-mono-mp3\"},\"language\":{\"autoDetection\":false}}}";
        log.debug("send2:{}", test);
        client.send(test);
    }

    private void send3(WebSocketClient client, String requestID, String text) {
        String timestamp = dateFormat.format(new Date());
        String test = "Path: ssml\r\n" + "X-RequestId: " + requestID + "\r\n" + "X-Timestamp: "
            + timestamp + "\r\n" + "Content-Type: application/ssml+xml\r\n" + "\r\n"
            + "<speak xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xmlns:emo=\"http://www.w3.org/2009/10/emotionml\" version=\"1.0\" xml:lang=\""
            + ttsConfig.getLanguage().getCode() + "\"><voice name=\"" + ttsConfig.getVoice().getCode()
            + "\"><mstts:express-as style=\"" + ttsConfig.getVoiceStyle().getCode()
            + "\" ><prosody rate=\"0%\" pitch=\"0%\">"
            + text + "</prosody></mstts:express-as></voice></speak>";
        log.debug("send3:{}", test);
        client.send(test);
    }

    public void setLang(LanguageEnum language) {
        this.ttsConfig.setLanguage(language);
    }

    public void setv(VoiceEnum voice) {
        this.ttsConfig.setVoice(voice);
    }

    public void setvs(VoiceStyle voiceStyle) {
        this.ttsConfig.setVoiceStyle(voiceStyle);
    }


    @Override
    public void close() {
        if (wsClient != null) {
            wsClient.close();
        }
    }

}
