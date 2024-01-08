package com.coderdream.freeapps.util.download.v3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderDream 线程池的关闭
 */
public class PoolTest03 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = null;
        try {
            //创建线程池对象
            threadPool = new ThreadPoolExecutor(2, 3, 1,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(2));

            //创建任务
            Runnable r = () -> System.out.println(Thread.currentThread().getName());

            //将任务提交给线程池
            for (int i = 0; i < 5; i++) {
                threadPool.execute(r);
            }
        } finally {
            //关闭线程池
            if (threadPool != null) {
//                threadPool.shutdown();//温和
//                threadPool.shutdownNow();//暴力
                threadPool.shutdown();

                if (!threadPool.awaitTermination(1, TimeUnit.MINUTES)) {
                    //等待1分钟，如果线程池没有关闭则会执行进来
                    threadPool.shutdownNow();
                }
            }
        }
    }
}
