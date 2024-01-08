package com.coderdream.freeapps.util.download.v4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderDream
 *     线程池对象的创建
 */
public class PoolTest01 {
    public static void main(String[] args) {
        //创建线程池对象
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3, 1,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(2));

        //创建任务
        Runnable r = () -> System.out.println(Thread.currentThread().getName());

        //将任务提交给线程池
        for (int i = 0; i < 5; i++) {
            threadPool.execute(r);
        }
    }
}
