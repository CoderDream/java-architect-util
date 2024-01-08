package com.coderdream.freeapps.util.proxy;

import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.util.download.v4.Constant;
import com.coderdream.freeapps.util.download.v4.DownloaderUtil;
import com.coderdream.freeapps.util.hutool.xml.RssParserUtil;
import com.coderdream.freeapps.util.other.DownloadUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadDownloadMp3Executer {

    public static Integer POOL_SIZE = 5;

    public static LinkedBlockingDeque<DownloadInfoEntity> downloadInfoEntityList = new LinkedBlockingDeque<>();

    private Integer corePoolSize = POOL_SIZE;

    private Integer maximumPoolSize = POOL_SIZE;

    private Integer keepAliveTime = 10;

    private static long startTime;

    private TimeUnit unit = TimeUnit.MILLISECONDS;

    private BlockingDeque workQueue = new LinkedBlockingDeque();

    private RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    };

    public ThreadPoolExecutor coreThreadPool = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        unit,
        workQueue,
        handler);

    public void initTestArr() {
        boolean test = true;
        List<DownloadInfoEntity> downloadInfoEntityListTemp = new ArrayList<>();
        if(test) {
            DownloadInfoEntity infoEntity = new DownloadInfoEntity();
            String folderName = "231207";
            infoEntity.setFileUrl(
                "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_2023/ep-" + folderName);
            infoEntity.setPath("D:/14_LearnEnglish/6MinuteEnglish/2023/" + folderName + "/");
            infoEntity.setFileName(folderName + ".html");
            downloadInfoEntityListTemp = Arrays.asList(infoEntity);
        } else {
            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("2023", "01", "02");
        }

        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
            downloadInfoEntityList.add(downloadInfoEntity);
        }
    }

    public class MyThread extends Thread {

        @Override
        public void run() {

            while (!downloadInfoEntityList.isEmpty()) {
//                System.out.println(getName() + "start");
                long startTime = System.currentTimeMillis();
                DownloadInfoEntity downloadInfoEntity = downloadInfoEntityList.pop();
//                System.out.println("####" + downloadInfoEntity);

                Map<String, String> audioMap = RssParserUtil.getAudioMap();
                Map<String, DownloadInfoEntity> listBbcPageInfoListByHtml = HtmlUtil.getListBbcPageInfoListByHtml(downloadInfoEntity.getPath(), downloadInfoEntity.getFileName(), audioMap);
//                DownloadInfoEntity downloadInfoEntityPdf = listBbcPageInfoListByHtml.get("pdf");
//                DownloadUtil.downloadFile(downloadInfoEntityPdf.getFileUrl(), downloadInfoEntityPdf.getPath(),
//                    downloadInfoEntityPdf.getFileName(), true);

                DownloadInfoEntity downloadInfoEntityMp3 = listBbcPageInfoListByHtml.get("mp3");
                if(downloadInfoEntityMp3 != null) {
//                    DownloadUtil.downloadFile(downloadInfoEntityMp3.getFileUrl(), downloadInfoEntityMp3.getPath(),
//                        downloadInfoEntityMp3.getFileName(), true);

                    //下载地址
//                    String url = "http://downloads.bbc.co.uk/learningenglish/features/6min/220106_6_minute_english_futuristic_tech_download.mp3";
//
//                    String path = "D:\\14_LearnEnglish\\6MinuteEnglish\\2020\\200102\\";
//                    String filename = "220106_6_minute_english_futuristic_tech_download_2.mp3";
                    DownloaderUtil downloader = new DownloaderUtil();
                    downloader.download(downloadInfoEntityMp3.getFileUrl(), downloadInfoEntityMp3.getPath(),
                        downloadInfoEntityMp3.getFileName(), true);
                }

                Integer period2= new Random().nextInt(4000) + 300;
                try {
                    Thread.sleep(period2);   // 休眠3秒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

//                System.out.println("Thread" + getName() + "  " + downloadInfoEntityList.pop());
                long period = System.currentTimeMillis() - startTime;
                System.out.println("耗时毫秒数：\t" + period);
            }
            if (coreThreadPool.getActiveCount() == 1) {
                coreThreadPool.shutdown();
                long current = System.currentTimeMillis();
                long period = current - startTime;
                System.out.println("总耗时毫秒数：\t" + period);
            }
        }
    }

    public void printByMulThread() {
        for (int i = 0; i < POOL_SIZE; i++) {
            MyThread newThread = new MyThread();
            coreThreadPool.execute(newThread);
        }
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        MultiThreadDownloadMp3Executer test = new MultiThreadDownloadMp3Executer();
        test.initTestArr();
        test.printByMulThread();
    }
}
