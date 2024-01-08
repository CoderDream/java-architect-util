package com.coderdream.freeapps.util.download.v3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * jdk提供的便捷的创建线程池的方式 阿里巴巴开发文档中不建议使用这些方式创建线程池
 *
 * @author CoderDream
 */
public class PoolTest05 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    }
}
