package com.coderdream.freeapps.util.download.v5;


import com.coderdream.freeapps.util.download.v5.core.Downloader;

/**
 * 主类
 *
 * @author CoderDream
 */
public class Main {

    public static void main(String[] args) {
        //下载地址
        String url = "https://down.qq.com/qqweb/PCQQ/PCQQ_EXE/PCQQ2021.exe";

        Downloader downloader = new Downloader();
        downloader.download(url);
    }
}
