package com.coderdream.freeapps.util.spiderdistrict;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * https://blog.csdn.net/qq_38449518/article/details/82875816
 */
public class WormMain {

    //待抓取的Url队列，全局共享
    public static final LinkedBlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();

    public static final Map<String, String> districtMap = new LinkedHashMap<>();

    public static final WormCore wormCore = new WormCore();

    public static void main(String[] args) {
        //要抓取的根URL
        String rootUrl = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2022/index.html";
        //先把根URL加入URL队列
        urlQueue.offer(rootUrl);
        Runnable runnable = new MyRunnable();
        //开启固定大小的线程池，爬取的过程由10个线程完成
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //开始爬取
        for (int i = 0; i < 10; i++) {
            executorService.submit(runnable);
        }
        //关闭线程池
        executorService.shutdown();

        for (Map.Entry<String, String> entry : districtMap.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey + "#@#@#" + mapValue);
        }
    }
}
