package com.coderdream.freeapps.util.apple.event;



public class GetSrtUtil {

    private final static String SRT_PRIO = "https://events-delivery.apple.com/1008yunfyueoofvvgxpjmmvcblgnjsel/vod_main_bTVAkbqTcJuRovcmMRYungakLuiQyTWT/cc/";

    private final static  String END = ".webvtt";
    private final static  String CN_PRIO = "zh/zh_";
    private final static  String EN_PRIO = "en/en_";

    private final static  String CN_END = "https://events-delivery.apple.com/1008yunfyueoofvvgxpjmmvcblgnjsel/vod_main_bTVAkbqTcJuRovcmMRYungakLuiQyTWT/cc/zh/zh_0.webvtt";

    private  final static String EN_END = "https://events-delivery.apple.com/1008yunfyueoofvvgxpjmmvcblgnjsel/vod_main_bTVAkbqTcJuRovcmMRYungakLuiQyTWT/cc/zh/zh_0";

    // zh/zh_0.webvtt
    public static void main(String[] args) {
        for (int i = 0; i < 820; i++) {
            System.out.println(SRT_PRIO + CN_PRIO + i + END);
        }

        for (int i = 0; i < 820; i++) {
            System.out.println(SRT_PRIO + EN_PRIO + i + END);
        }


    }

}
