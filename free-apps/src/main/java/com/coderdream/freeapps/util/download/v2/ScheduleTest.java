package com.coderdream.freeapps.util.download.v2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 *  ScheduledExecutorService
 * @author CoderDream
 */
public class ScheduleTest {
    public static void main(String[] args) {
        //获取对象
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);

//        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
//
//        ScheduledExecutorService s = (ScheduledExecutorService) new ThreadPoolExecutor(
//            2,
//            5,
//            3,
//            TimeUnit.SECONDS,
//            new LinkedBlockingDeque<>(3),
//            Executors.defaultThreadFactory(),
//            new DiscardOldestPolicy());
    // ScheduledExecutorService
//         s = new ScheduledThreadPoolExecutor(1,
//            new BasicThreadFactory.
//                Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());



        //延时2秒后开始执行任务，每间隔3秒再执行任务
        s.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis());
            //模拟耗时操作
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, 3, TimeUnit.SECONDS);

    }

    public static void schedule() {
        //获取对象
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);

        //延时2秒之后再执行任务
        s.schedule(() -> System.out.println(Thread.currentThread().getName()), 2, TimeUnit.SECONDS);

        //关闭
//        s.shutdown();
    }
}
