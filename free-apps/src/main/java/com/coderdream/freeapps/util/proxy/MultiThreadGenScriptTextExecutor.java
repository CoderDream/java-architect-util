package com.coderdream.freeapps.util.proxy;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.pdf.ReadPdfUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderDream
 */
public class MultiThreadGenScriptTextExecutor {

    public static Integer POOL_SIZE = 50;

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
        boolean test = false;

        List<DownloadInfoEntity> downloadInfoEntityListTemp = new ArrayList<>();
        if (test) {
            DownloadInfoEntity infoEntity = new DownloadInfoEntity();
            String ep = "240404";
            infoEntity.setFileUrl(
                "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_2024/ep-" + ep + "");
            infoEntity.setPath("D:/14_LearnEnglish/6MinuteEnglish/2024/" + ep + "/");
            infoEntity.setFileName(ep + ".html");
            downloadInfoEntityListTemp = Arrays.asList(infoEntity);
        } else {
//            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("txt", "2020","02","06");
            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("txt", "2019");
        }

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "bbc"
                + File.separatorChar;

//        List<String> stringList = FileUtil.readLines(folderPath + "pdf_names.txt", "UTF-8");
//        Map<String, String> pdfNameMap = new LinkedHashMap<>();
////        String key = "";
//        for (String pdfName : stringList) {
//            String key = pdfName.substring(0, 6);
//            pdfNameMap.put(key, pdfName);
//        }

        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
//            String fileName = downloadInfoEntity.getFileName();
            String realPdfName =  findPdf(downloadInfoEntity.getPath());// pdfNameMap.get(fileName.substring(0, fileName.lastIndexOf(".")));
            downloadInfoEntity.setFileName(realPdfName);
            if (realPdfName != null) {
                downloadInfoEntityList.add(downloadInfoEntity);
            }
        }
        System.out.println("#");
    }

    private static String findPdf(String folderPath) {
        List<String> allFileNames = CdFileUtils.getAllFileNames(folderPath);
        if (CollectionUtil.isNotEmpty(allFileNames)) {
            for (String str : allFileNames) {
                int index = str.lastIndexOf("pdf");
                if (-1 != index) {
                    int index2 = str.lastIndexOf("\\");
                    if(index2 != -1) {
                        String s = str.substring(index2+1);
                        return s;
                    }
                }
            }
        }

        return "";
    }

    public class MyThread extends Thread {

        @Override
        public void run() {

            while (!downloadInfoEntityList.isEmpty()) {
                System.out.println(getName() + "start");
                long startTime = System.currentTimeMillis();
                DownloadInfoEntity downloadInfoEntity = downloadInfoEntityList.pop();
//                DownloadUtil.getBbcPageInfoDetailByHtml(downloadInfoEntity.getFileUrl(), downloadInfoEntity.getPath(),
//                    downloadInfoEntity.getFileName(), true);

//                HtmlUtil.genScriptRawByHtml(downloadInfoEntity.getPath(), downloadInfoEntity.getFileName()); // TODO

                ReadPdfUtil.genScriptTxt(downloadInfoEntity.getPath(), downloadInfoEntity.getFileName());

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

        MultiThreadGenScriptTextExecutor test = new MultiThreadGenScriptTextExecutor();
        test.initTestArr();
        test.printByMulThread();
    }
}
