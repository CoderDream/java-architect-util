package com.coderdream.freeapps.util.download.v4;


/*
    主类
 */
public class Main {

    public static void main(String[] args) {
//        m1();
        m3();
    }

    public static void m1() {
        //下载地址
        String url = "https://down.qq.com/qqweb/PCQQ/PCQQ_EXE/PCQQ2021.exe";
        DownloaderUtil downloader = new DownloaderUtil();
        String path = "E:\\mytest\\";
        String filename = "PCQQ2021.exe";
        downloader.download(url, path, filename, false);
    }

    public static void m2() {
        //下载地址
        String url = "http://downloads.bbc.co.uk/learningenglish/features/6min/220106_6_minute_english_futuristic_tech_download.mp3";

        DownloaderUtil downloader = new DownloaderUtil();
        String path = "D:\\14_LearnEnglish\\6MinuteEnglish\\2020\\200102\\";
        String filename = "220106_6_minute_english_futuristic_tech_download_2.mp3";
        downloader.download(url, path, filename, Constant.PROXY_FLAG);
    }

    //


    public static void m3() {
        //下载地址
        String url = "http://open.live.bbc.co.uk/mediaselector/6/redir/version/2.0/mediaset/audio-nondrm-download-low/proto/http/vpid/p0ds0nvx.mp3";

        DownloaderUtil downloader = new DownloaderUtil();
        String path = "D:\\14_LearnEnglish\\6MinuteEnglish\\2022\\221229\\";
        String filename = "221229_united_against_food_waste.mp3";
        downloader.download(url, path, filename, Constant.PROXY_FLAG);
    }
}
