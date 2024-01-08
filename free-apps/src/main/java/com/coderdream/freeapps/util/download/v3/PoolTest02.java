package com.coderdream.freeapps.util.download.v3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderDream 线程池对象的创建
 */
public class PoolTest02 {

    public static void main(String[] args) throws InterruptedException {
        //创建线程池对象
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3, 1,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

        //创建任务
        Runnable r = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };

        //将任务提交给线程池
        for (int i = 0; i < 5; i++) {
            threadPool.execute(r);
        }

        System.out.println(threadPool);

        TimeUnit.SECONDS.sleep(10);
        System.out.println(threadPool);
    }
}
