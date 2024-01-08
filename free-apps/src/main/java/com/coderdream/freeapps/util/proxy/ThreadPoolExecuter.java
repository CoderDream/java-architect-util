package com.coderdream.freeapps.util.proxy;

import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.util.other.DownloadUtil;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecuter {

    public static Integer POOL_SIZE = 500;

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
        List<DownloadInfoEntity> downloadInfoEntityListTemp = HtmlUtil.getDownloadImgInfo();
        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
            downloadInfoEntityList.add(downloadInfoEntity);
        }
    }

    public class MyThread extends Thread {

        @Override
        public void run() {

            while (!downloadInfoEntityList.isEmpty()) {
                System.out.println(getName() + "start");
                long startTime = System.currentTimeMillis();
                DownloadInfoEntity downloadInfoEntity = downloadInfoEntityList.pop();
                DownloadUtil.downloadFile(downloadInfoEntity.getFileUrl(), downloadInfoEntity.getPath(),
                    downloadInfoEntity.getFileName(), true);
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

        ThreadPoolExecuter test = new ThreadPoolExecuter();
        test.initTestArr();
        test.printByMulThread();

    }
}
