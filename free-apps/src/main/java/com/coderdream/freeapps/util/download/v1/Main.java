package com.coderdream.freeapps.util.download.v1;


import java.util.Scanner;

/*
    主类
 */
public class Main {
    public static void main(String[] args) {
        //下载地址
        String url = null;
        url = "https://down.qq.com/qqweb/PCQQ/PCQQ_EXE/PCQQ2021.exe";


        Downloader downloader = new Downloader();
        downloader.download(url);
    }
}
