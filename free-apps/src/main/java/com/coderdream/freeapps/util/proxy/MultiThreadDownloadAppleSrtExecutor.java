package com.coderdream.freeapps.util.proxy;

import cn.hutool.core.collection.CollectionUtil;
import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.apple.event.GetSrtUtil;
import com.coderdream.freeapps.util.other.DownloadUtil;
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
public class MultiThreadDownloadAppleSrtExecutor {

    public static Integer POOL_SIZE = 50;

    public static LinkedBlockingDeque<String> urlCnList = new LinkedBlockingDeque<>();

    public static LinkedBlockingDeque<String> urlEnList = new LinkedBlockingDeque<>();

    public static LinkedBlockingDeque<SubtitleBaseEntity> cnEntityList = new LinkedBlockingDeque<>();

    public static LinkedBlockingDeque<SubtitleBaseEntity> enEntityList = new LinkedBlockingDeque<>();

    private Integer corePoolSize = POOL_SIZE;

    private Integer maximumPoolSize = POOL_SIZE;

    private Integer keepAliveTime = 10;

    private static long startTime;

    private TimeUnit unit = TimeUnit.MILLISECONDS;

    private BlockingDeque workQueue = new LinkedBlockingDeque();

    private static List<SubtitleBaseEntity> subtitleBaseEntityListCn = new ArrayList<>();

    private static List<SubtitleBaseEntity> subtitleBaseEntityListEn = new ArrayList<>();

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

        Integer SIZE = 964;

        // 先试前10个
        SIZE = 20;

//        SIZE = 974;

//        String urlBase = "https://events-delivery.apple.com/1505clvgxdwlbjrjhxtjdgcdxaiabvuf/vod_main_BveVQvhWftXzpUakjHjEUkbmUYLbRdcV/";

        int startCn = 0;// 960;
        int startEn = 0;
        List<String> urlCnListInit = GetSrtUtil.genUrlCnList(startCn, SIZE);
        List<String> urlEnListInit = GetSrtUtil.genUrlCnList(startEn, SIZE);
        urlCnList.addAll(urlCnListInit);
        urlEnList.addAll(urlEnListInit);
    }

    public class MyThread extends Thread {

        @Override
        public void run() {

            while (!urlCnList.isEmpty()) {
//                System.out.println(getName() + "start");
                long startTime = System.currentTimeMillis();
                String urlCn = urlCnList.pop();
                List<SubtitleBaseEntity> subtitleBaseEntitiesCn = GetSrtUtil.m1(urlCn);
                System.out.println("SIZE##" + subtitleBaseEntitiesCn.size() + ":" + urlCn);
                subtitleBaseEntityListCn.addAll(subtitleBaseEntitiesCn);

                String urlEn = urlEnList.pop();
                List<SubtitleBaseEntity> subtitleBaseEntitiesEn = GetSrtUtil.m1(urlEn);
                System.out.println("SIZE##" + subtitleBaseEntitiesCn.size() + ":" + urlEn);
                subtitleBaseEntityListEn.addAll(subtitleBaseEntitiesEn);

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

    public void printT() {
        if (CollectionUtil.isNotEmpty(subtitleBaseEntityListCn)) {
            for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityListCn) {
                System.out.println("Cn：" + subtitleBaseEntity);
            }
        }

        System.out.println("####");
        if (CollectionUtil.isNotEmpty(subtitleBaseEntityListEn)) {
            for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityListEn) {
                System.out.println("En：" + subtitleBaseEntity);
            }
        }

    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        MultiThreadDownloadAppleSrtExecutor test = new MultiThreadDownloadAppleSrtExecutor();
        test.initTestArr();
        test.printByMulThread();
        test.printT();
    }
}

