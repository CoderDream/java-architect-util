package com.coderdream.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadA {

    public static void main(String[] args) {
//        ThreadA.testNewFixedThreadPool();
//        ThreadA.testNewSingleThreadExecutor();

        ThreadPoolExecutor threadPoolExecutor;
        ThreadA.testNewScheduleThreadExecutor();
    }

    public static void testNewFixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.execute(() -> {
            System.out.println("1号任务：" + Thread.currentThread().getName() + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() -> {
            System.out.println("2号任务：" + Thread.currentThread().getName() + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() -> {
            System.out.println("3号任务：" + Thread.currentThread().getName() + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void testNewSingleThreadExecutor() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "111");
        });
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "222");
        });
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "333");
        });
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "444");
        });
    }


    public static void testNewScheduleThreadExecutor() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

        // 正常执行
//        pool.execute(() -> {
//            System.out.println(Thread.currentThread().getName() + "：1");
//        });

        // 延迟执行，执行当前任务延迟5s后再执行
//        pool.schedule(() -> {
//            System.out.println(Thread.currentThread().getName() + "：2");
//        },5,TimeUnit.SECONDS);

        // 周期执行，当前任务第一次延迟5s执行，然后没3s执行一次
        // 这个方法在计算下次执行时间时，是从任务刚刚开始时就计算。
//        pool.scheduleAtFixedRate(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(System.currentTimeMillis() + "：3");
//        },2,1,TimeUnit.SECONDS);

        // 周期执行，当前任务第一次延迟5s执行，然后没3s执行一次
        // 这个方法在计算下次执行时间时，会等待任务结束后，再计算时间
        pool.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "：3");
        },2,1, TimeUnit.SECONDS);
    }
}
