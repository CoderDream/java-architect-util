package com.coderdream.freeapps.util.mytts;

import com.microsoft.cognitiveservices.speech.*;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class SpeechSynthesisBackup {

    // This example requires environment variables named "SPEECH_KEY" and "SPEECH_REGION"
    private static String speechKey = System.getenv("SPEECH_KEY");
    private static String speechRegion = System.getenv("SPEECH_REGION");

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        speechKey = "7849c9d958604bf8bce7afb707b3cc1b";
        System.getenv().forEach((key, value) -> {
            if(key.equals("SPEECH_KEY")) {
                System.out.println("###########");
            }
            System.out.println(key + ":" + value);
        });

        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);

        speechConfig.setSpeechSynthesisVoiceName("en-US-JennyNeural");

        SpeechSynthesizer speechSynthesizer = new SpeechSynthesizer(speechConfig);

        // Get text from the console and synthesize to the default speaker.
        System.out.println("Enter some text that you want to speak >");
        String text = new Scanner(System.in).nextLine();
        if (text.isEmpty()) {
            return;
        }

        SpeechSynthesisResult speechSynthesisResult = speechSynthesizer.SpeakTextAsync(text).get();

        if (speechSynthesisResult.getReason() == ResultReason.SynthesizingAudioCompleted) {
            System.out.println("Speech synthesized to speaker for text [" + text + "]");
        } else if (speechSynthesisResult.getReason() == ResultReason.Canceled) {
            SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(
                speechSynthesisResult);
            System.out.println("CANCELED: Reason=" + cancellation.getReason());

            if (cancellation.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                System.out.println("CANCELED: Did you set the speech resource key and region values?");
            }
        }

        System.exit(0);
    }
}
