package com.coderdream.freeapps.util.proxy;

import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.util.bbc.DictUtils;
import com.coderdream.freeapps.util.bbc.GenSixMinutePptx;
import com.coderdream.freeapps.util.sqliteutil.SqliteTitleUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderDream
 */
public class MultiThreadTranlateVocExecutor {

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
            String ep = "220728";
            String year = ep.substring(0, 2);
            infoEntity.setFileUrl(
                "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_20" + year + "/ep-" + ep + "");
            infoEntity.setPath("D:/14_LearnEnglish/6MinuteEnglish/20" + year + "/" + ep + "/");
            infoEntity.setFileName(ep + ".html");
            downloadInfoEntityListTemp = Arrays.asList(infoEntity);
        } else {
            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("pdf", "2022", "04");
        }

        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
            downloadInfoEntityList.add(downloadInfoEntity);
        }

        System.out.println("#");
    }

    public class MyThread extends Thread {

        @Override
        public void run() {

            while (!downloadInfoEntityList.isEmpty()) {
                System.out.println(getName() + "start");
                long startTime = System.currentTimeMillis();
                DownloadInfoEntity downloadInfoEntity = downloadInfoEntityList.pop();

                // 生成 script_dialog和voc文件
                String folderName = downloadInfoEntity.getFileName().substring(0, 6);
                DictUtils.processVoc(folderName);

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

        MultiThreadTranlateVocExecutor test = new MultiThreadTranlateVocExecutor();
        test.initTestArr();
        test.printByMulThread();
    }
}
