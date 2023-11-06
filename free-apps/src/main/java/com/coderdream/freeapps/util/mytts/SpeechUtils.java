package com.coderdream.freeapps.util.mytts;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.MsTtsEntity;
import com.coderdream.freeapps.util.other.BaseUtils;
import com.coderdream.freeapps.util.other.CdDateTimeUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.mytts.audio.FfmpegCmdDurationUtils;
import com.microsoft.cognitiveservices.speech.AudioDataStream;
import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisOutputFormat;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpeechUtils {



    public static void main(String[] args) {
//        m2();
//        SpeechSynthesisV3.genTodayMp3Bi();
//        SpeechSynthesisV3.genTodayMp3Wx();
//        SpeechUtils.genTodayMp3(30);
//        m1();
//        mm();
//        m2();
        genTodayMp3(30);
    }

    // This example requires environment variables named "SPEECH_KEY" and "SPEECH_REGION"
    private static String speechKey = System.getenv("SPEECH_KEY");
    private static String speechRegion = System.getenv("SPEECH_REGION");

//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        speechKey = Constants.SPEECH_KEY;
//        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
//
////        speechConfig.setSpeechSynthesisVoiceName("en-US-JennyNeural"); // zh-CN-XiaomengNeural\
//
//        speechConfig.setSpeechSynthesisVoiceName("zh-CN-XiaomengNeural"); //
//
//        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);
//
//        // Get text from the console and synthesize to the default speaker.
//        System.out.println("Enter some text that you want to speak >");
//        String text = "大家好，今天是2023年06月03日，"; //new Scanner(System.in).nextLine();
////        if (text.isEmpty()) {
////            return;
////        }
//
//        SpeechSynthesisResult speechSynthesisResult = speechSynthesizer.SpeakTextAsync(text).get();
//
//        if (speechSynthesisResult.getReason() == ResultReason.SynthesizingAudioCompleted) {
//            System.out.println("Speech synthesized to speaker for text [" + text + "]");
//        } else if (speechSynthesisResult.getReason() == ResultReason.Canceled) {
//            SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(
//                speechSynthesisResult);
//            System.out.println("CANCELED: Reason=" + cancellation.getReason());
//
//            if (cancellation.getReason() == CancellationReason.Error) {
//                System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
//                System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
//                System.out.println("CANCELED: Did you set the speech resource key and region values?");
//            }
//        }
//
//        System.exit(0);
//    }

    /**
     *
     */
    public static void mm() {
        // Replace below with your own subscription key.

        speechKey = Constants.SPEECH_KEY;
        String speechSubscriptionKey = speechKey; // "YourSubscriptionKey";
        // Replace below with your own service region (e.g., "westus").
        String serviceRegion = speechRegion; //  "YourServiceRegion";

        // Creates an instance of a speech synthesizer using speech configuration with
        // specified
        // subscription key and service region and default speaker as audio output.
        try (SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion)) {
            // Set the voice name, refer to https://aka.ms/speech/voices/neural for full
            // list.
            config.setSpeechSynthesisVoiceName("en-US-AriaNeural");
            try (SpeechSynthesizer synth = new SpeechSynthesizer(config)) {

                assert (config != null);
                assert (synth != null);

                int exitCode = 1;

//                System.out.println("Type some text that you want to speak...");
//                System.out.print("> ");
                String text = "Hello World";// new Scanner(System.in).nextLine();

                Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);
                assert (task != null);

                SpeechSynthesisResult result = task.get();
                assert (result != null);

                if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                    System.out.println("Speech synthesized to speaker for text [" + text + "]");
                    exitCode = 0;
                } else if (result.getReason() == ResultReason.Canceled) {
                    SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails
                        .fromResult(result);
                    System.out.println("CANCELED: Reason=" + cancellation.getReason());

                    if (cancellation.getReason() == CancellationReason.Error) {
                        System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                        System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                        System.out.println("CANCELED: Did you update the subscription info?");
                    }
                }

                System.exit(exitCode);
            }
        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());

            assert (false);
            System.exit(1);
        }
    }

    public static void m1() {
//        speechKey = Constants.SPEECH_KEY;
//        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
//        System.out.println("speechConfig: " + speechConfig);
//
//        // set the output format
//        speechConfig.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio24Khz160KBitRateMonoMp3);
//
//        speechConfig.setSpeechSynthesisVoiceName("zh-CN-XiaomengNeural"); //
//
//        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig, null);
//        SpeechSynthesisResult result = speechSynthesizer.SpeakText("大家好，今天是2023年06月03日，");
//        AudioDataStream stream = AudioDataStream.fromResult(result);
//        stream.saveToWavFile("file.mp3");

        // Replace below with your own subscription key.

        speechKey = Constants.SPEECH_KEY;
        String speechSubscriptionKey = speechKey; // "YourSubscriptionKey";
        // Replace below with your own service region (e.g., "westus").
        String serviceRegion = speechRegion; //  "YourServiceRegion";

        // Creates an instance of a speech synthesizer using speech configuration with
        // specified
        // subscription key and service region and default speaker as audio output.
        try (SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion)) {
            // Set the voice name, refer to https://aka.ms/speech/voices/neural for full
            // list.
            config.setSpeechSynthesisVoiceName("zh-CN-XiaomengNeural");
            try (SpeechSynthesizer synth = new SpeechSynthesizer(config)) {

                assert (config != null);
                assert (synth != null);

                int exitCode = 1;

//                System.out.println("Type some text that you want to speak...");
//                System.out.print("> ");
                String text = "大家好，今天是2023年06月16日";// new Scanner(System.in).nextLine();

                Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(text);
                assert (task != null);

                SpeechSynthesisResult result = task.get();
                assert (result != null);
                AudioDataStream stream = AudioDataStream.fromResult(result);
                stream.saveToWavFile("0616.mp3");

                if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                    System.out.println("Speech synthesized to speaker for text [" + text + "]");
                    exitCode = 0;
                } else if (result.getReason() == ResultReason.Canceled) {
                    SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails
                        .fromResult(result);
                    System.out.println("CANCELED: Reason=" + cancellation.getReason());

                    if (cancellation.getReason() == CancellationReason.Error) {
                        System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                        System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                        System.out.println("CANCELED: Did you update the subscription info?");
                    }
                }

                System.exit(exitCode);
            }
        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());

            assert (false);
            System.exit(1);
        }
    }

    public static void m2() {
        speechKey = Constants.SPEECH_KEY;
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);

        // set the output format
        speechConfig.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio24Khz160KBitRateMonoMp3);

//        speechConfig.setSpeechSynthesisVoiceName("zh-CN-XiaomengNeural"); //

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig, null);
        String xmlFileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\java\\com\\coderdream\\freeapps\\util\\mytts\\ssml.xml";
        String ssml = xmlToString(xmlFileName);
        SpeechSynthesisResult result = speechSynthesizer.SpeakSsml(ssml);
        AudioDataStream stream = AudioDataStream.fromResult(result);
        stream.saveToWavFile("file2.mp3");
    }

    public static MsTtsEntity genTodayMp3(Integer rate) {
        MsTtsEntity msTtsEntity = new MsTtsEntity();
        long startTime = System.currentTimeMillis();
        String path = BaseUtils.getPath();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String biFileName = path + File.separator + dateStr + "_bi.txt";
        String wxFileName = path + File.separator + dateStr + "_wx.txt";

        String audioPathBi = path + File.separator + dateStr + "_bi";
        File audioPathBiPath = new File(audioPathBi);
        if (!audioPathBiPath.exists()) {
            audioPathBiPath.mkdirs();
        }

        String audioPathWx = path + File.separator + dateStr + "_wx";
        File audioPathWxPath = new File(audioPathWx);
        if (!audioPathWxPath.exists()) {
            audioPathWxPath.mkdirs();
        }

        String audioFileNameBi = audioPathBi + File.separator + dateStr + "_bi.mp3";
        String audioFileNameWx = audioPathWx + File.separator + dateStr + "_wx.mp3";

        speechKey = Constants.SPEECH_KEY;
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);

        // set the output format
        speechConfig.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio24Khz160KBitRateMonoMp3);

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig, null);
        List<String> biStringList = CdFileUtils.readFileContent(biFileName);
        int biContentLength = 0;
        if (CollectionUtils.isNotEmpty(biStringList)) {
            for (String str : biStringList) {
                if (StrUtil.isNotEmpty(str)) {
                    biContentLength += str.length();
                }
            }
        }
        msTtsEntity.setBiContentLength(biContentLength);
        String ssmlBi = genXmlString(biStringList, rate);
//        log.error(ssmlBi);
        SpeechSynthesisResult resultBi = speechSynthesizer.SpeakSsml(ssmlBi);
        AudioDataStream streamBi = AudioDataStream.fromResult(resultBi);
        streamBi.saveToWavFile(audioFileNameBi);
        String biTimeLength = FfmpegCmdDurationUtils.durationFormat(audioFileNameBi);
        log.error(String.format("Bi时长：%s", biTimeLength));
        msTtsEntity.setBiTimeLength(biTimeLength);

        List<String> wxStringList = CdFileUtils.readFileContent(wxFileName);
        String ssmlWx = genXmlString(wxStringList, rate);
        int wxContentLength = 0;
        if (CollectionUtils.isNotEmpty(wxStringList)) {
            for (String str : wxStringList) {
                if (StrUtil.isNotEmpty(str)) {
                    wxContentLength += str.length();
                }
            }
        }

        msTtsEntity.setWxContentLength(wxContentLength);
        SpeechSynthesisResult resultWx = speechSynthesizer.SpeakSsml(ssmlWx);
        AudioDataStream streamWx = AudioDataStream.fromResult(resultWx);
        streamWx.saveToWavFile(audioFileNameWx);
        String wxTimeLength = FfmpegCmdDurationUtils.durationFormat(audioFileNameWx);
        log.error(String.format("Wx时长：%s", wxTimeLength));
        msTtsEntity.setWxTimeLength(wxTimeLength);

        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        msTtsEntity.setCreateTime(new Date());
        String operateTimeLengthStr = CdDateTimeUtils.genMessage(period);
        msTtsEntity.setOperateTimeLength(period);
        msTtsEntity.setOperateTimeLengthStr(operateTimeLengthStr);
        log.error("本次耗时" + operateTimeLengthStr + "。");

        return msTtsEntity;
    }

    public static MsTtsEntity genTodayMp3ByType(Integer rate, String type) {
        MsTtsEntity msTtsEntity = new MsTtsEntity();
        long startTime = System.currentTimeMillis();
        String path = BaseUtils.getPath();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName = path + File.separator + dateStr + "_" + type + ".txt";

        String audioPathBi = path + File.separator + dateStr + "_" + type;
        File audioPathBiPath = new File(audioPathBi);
        if (!audioPathBiPath.exists()) {
            audioPathBiPath.mkdirs();
        }

        String audioFileNameBi = audioPathBi + File.separator + dateStr + "_" + type + ".mp3";

        speechKey = Constants.SPEECH_KEY;
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);

        // set the output format
        speechConfig.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio24Khz160KBitRateMonoMp3);

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig, null);
        List<String> biStringList = CdFileUtils.readFileContent(fileName);
        int biContentLength = 0;
        if (CollectionUtils.isNotEmpty(biStringList)) {
            for (String str : biStringList) {
                if (StrUtil.isNotEmpty(str)) {
                    biContentLength += str.length();
                }
            }
        }
        msTtsEntity.setBiContentLength(biContentLength);
        String ssmlBi = genXmlString(biStringList, rate);
        SpeechSynthesisResult resultBi = speechSynthesizer.SpeakSsml(ssmlBi);
        AudioDataStream streamBi = AudioDataStream.fromResult(resultBi);
        streamBi.saveToWavFile(audioFileNameBi);
        String biTimeLength = FfmpegCmdDurationUtils.durationFormat(audioFileNameBi);
        log.error(String.format("Bi时长：%s", biTimeLength));
        msTtsEntity.setBiTimeLength(biTimeLength);


        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        msTtsEntity.setCreateTime(new Date());
        String operateTimeLengthStr = CdDateTimeUtils.genMessage(period);
        msTtsEntity.setOperateTimeLength(period);
        msTtsEntity.setOperateTimeLengthStr(operateTimeLengthStr);
        log.error("本次耗时" + operateTimeLengthStr + "。");

        return msTtsEntity;
    }

//    public static void genTodayMp3Bi() {
//        long startTime = System.currentTimeMillis();
//        String path = BaseUtils.getPath();
//        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String biFileName = path + File.separator + dateStr + "_bi.txt";
//        String wxFileName = path + File.separator + dateStr + "_wx.txt";
//
//        String audioPathBi = path + File.separator + dateStr + "_bi";
//        File audioPathBiPath = new File(audioPathBi);
//        if (!audioPathBiPath.exists()) {
//            audioPathBiPath.mkdirs();
//        }
//
//        String audioPathWx = path + File.separator + dateStr + "_wx";
//        File audioPathWxPath = new File(audioPathWx);
//        if (!audioPathWxPath.exists()) {
//            audioPathWxPath.mkdirs();
//        }
//
//        String audioFileNameBi = audioPathBi + File.separator + dateStr + "_bi.mp3";
//        String audioFileNameWx = audioPathWx + File.separator + dateStr + "_wx.mp3";
//
//        speechKey = Constants.SPEECH_KEY;
//        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
//
//        // set the output format
//        speechConfig.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio24Khz160KBitRateMonoMp3);
//
////        speechConfig.setSpeechSynthesisVoiceName("zh-CN-XiaomengNeural"); //
//
//        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig, null);
//        String xmlFileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\java\\com\\coderdream\\freeapps\\util\\mytts\\ssml.xml";
//        String ssmlXi = xmlToString(xmlFileName);
////        System.out.println(ssmlXi);
//        String ssmlBi = genXmlString(biFileName);
////        System.out.println(ssmlBi);
////        if (ssmlXi.equals(ssmlBi)) {
////            System.out.println("#####");
////        }
//        SpeechSynthesisResult resultBi = speechSynthesizer.SpeakSsml(ssmlBi);
//        AudioDataStream streamBi = AudioDataStream.fromResult(resultBi);
//        streamBi.saveToWavFile(audioFileNameBi);
//
////        String ssmlWx = genXmlString(wxFileName);
////        SpeechSynthesisResult resultWx = speechSynthesizer.SpeakSsml(ssmlWx);
////        AudioDataStream streamWx = AudioDataStream.fromResult(resultWx);
////        streamWx.saveToWavFile(audioFileNameWx);
//
//        long endTime = System.currentTimeMillis();
//        long period = endTime - startTime;
//
//        log.error("本次耗时" + CdDateTimeUtils.genMessage(period) + "。");
//    }
//
//    public static void genTodayMp3Wx() {
//        long startTime = System.currentTimeMillis();
//        String path = BaseUtils.getPath();
//        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String biFileName = path + File.separator + dateStr + "_bi.txt";
//        String wxFileName = path + File.separator + dateStr + "_wx.txt";
//
//        String audioPathBi = path + File.separator + dateStr + "_bi";
//        File audioPathBiPath = new File(audioPathBi);
//        if (!audioPathBiPath.exists()) {
//            audioPathBiPath.mkdirs();
//        }
//
//        String audioPathWx = path + File.separator + dateStr + "_wx";
//        File audioPathWxPath = new File(audioPathWx);
//        if (!audioPathWxPath.exists()) {
//            audioPathWxPath.mkdirs();
//        }
//
//        String audioFileNameBi = audioPathBi + File.separator + dateStr + "_bi.mp3";
//        String audioFileNameWx = audioPathWx + File.separator + dateStr + "_wx.mp3";
//
//        speechKey = Constants.SPEECH_KEY;
//        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
//
//        // set the output format
//        speechConfig.setSpeechSynthesisOutputFormat(SpeechSynthesisOutputFormat.Audio24Khz160KBitRateMonoMp3);
//
////        speechConfig.setSpeechSynthesisVoiceName("zh-CN-XiaomengNeural"); //
//
//        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig, null);
//        String xmlFileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\java\\com\\coderdream\\freeapps\\util\\mytts\\ssml.xml";
//        String ssmlXi = xmlToString(xmlFileName);
////        System.out.println(ssmlXi);
////        String ssmlBi = genXmlString(biFileName);
//////        System.out.println(ssmlBi);
//////        if (ssmlXi.equals(ssmlBi)) {
//////            System.out.println("#####");
//////        }
////        SpeechSynthesisResult resultBi = speechSynthesizer.SpeakSsml(ssmlBi);
////        AudioDataStream streamBi = AudioDataStream.fromResult(resultBi);
////        streamBi.saveToWavFile(audioFileNameBi);
//
//        String ssmlWx = genXmlString(wxFileName);
//        SpeechSynthesisResult resultWx = speechSynthesizer.SpeakSsml(ssmlWx);
//        AudioDataStream streamWx = AudioDataStream.fromResult(resultWx);
//        streamWx.saveToWavFile(audioFileNameWx);
//
//        long endTime = System.currentTimeMillis();
//        long period = endTime - startTime;
//
//        log.error("本次耗时" + CdDateTimeUtils.genMessage(period) + "。");
//    }

    private static String genXmlString(List<String> stringList, Integer rateValue) {
        String voiceName = "zh-CN-XiaomengNeural";
        String text = "";

        int size = 0;
        for (String str : stringList) {
            size += str.length();
            text += "\r\n        " + str;
        }
        log.error("字数：" + size);
//        text = String.join("\r\n    ", stringList);
        String rate = "+" + rateValue + ".00";
        String lang = "zh-CN";
        lang = "en-US";
//        String xmlString =
//            "<speak xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xmlns:emo=\"http://www.w3.org/2009/10/emotionml\" version=\"1.0\" xml:lang=\""
//                + lang + "\"><voice name=\"" + voiceName
//                + "\"><prosody rate=\"" + rate + "%\">"
//                + text + "</prosody></mstts:express-as></voice></speak>";

        String xmlString =
            "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xml:lang=\""
                + lang + "\">\r\n    <voice name=\"" + voiceName
                + "\">\r\n    <prosody rate=\"" + rate + "%\">"
                + text + "\r\n        </prosody>\r\n     </voice>\r\n</speak>";

        return xmlString;
    }


    private static String xmlToString(String filePath) {
        File file = new File(filePath);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString().trim();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return "File not found.";
        }
    }
}
