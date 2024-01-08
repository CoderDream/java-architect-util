package com.coderdream.freeapps.util.download.v3;


import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 下载器
 */
public class Downloader {

    public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    /**
     * 线程池对象
     */
    public ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Constant.THREAD_NUM, Constant.THREAD_NUM, 0,
        TimeUnit.SECONDS, new ArrayBlockingQueue<>(Constant.THREAD_NUM));

    public void download(String url) {
        //获取文件名
        String httpFileName = HttpUtils.getHttpFileName(url);
        //文件下载路径
        httpFileName = Constant.PATH + httpFileName;

        //获取本地文件的大小
        long localFileLength = FileUtils.getFileContentLength(httpFileName);

        //获取连接对象
        HttpURLConnection httpURLConnection = null;
        DownloadInfoThread downloadInfoThread = null;
        try {
            httpURLConnection = HttpUtils.getHttpURLConnection(url);
            //获取下载文件的总大小
            int contentLength = httpURLConnection.getContentLength();

            //判断文件是否已下载过
            if (localFileLength >= contentLength) {
                LogUtils.info("{}已下载完毕，无需重新下载", httpFileName);
                return;
            }

            //创建获取下载信息的任务对象
            downloadInfoThread = new DownloadInfoThread(contentLength);
            //将任务交给线程执行，每隔1秒执行一次
            scheduledExecutorService.scheduleAtFixedRate(downloadInfoThread, 1, 1, TimeUnit.SECONDS);

            //切分任务
            ArrayList<Future> list = new ArrayList<>();
            spilt(url, list);

            list.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.print("\r");
            System.out.print("下载完成");
            //关闭连接对象
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

            //关闭
            scheduledExecutorService.shutdownNow();

            //关闭线程池
            poolExecutor.shutdown();
        }

    }

    /**
     * 文件切分
     *
     * @param url
     * @param futureList
     */
    public void spilt(String url, ArrayList<Future> futureList) {

        try {
            //获取下载文件大小
            long contentLength = HttpUtils.getHttpFileContentLength(url);

            //计算切分后的文件大小
            long size = contentLength / Constant.THREAD_NUM;

            //计算分块个数
            for (int i = 0; i < Constant.THREAD_NUM; i++) {
                //计算下载起始位置
                long startPos = i * size;
                //计算结束位置
                long endPos;

                if (i == Constant.THREAD_NUM - 1) {
                    //下载最后一块，下载剩余的部分
                    endPos = 0;
                } else {
                    endPos = startPos + size;
                }

                //如果不是第一块，起始位置要+1
                if (startPos != 0) {
                    startPos++;
                }

                //创建任务对象
                DownloaderTask downloaderTask = new DownloaderTask(url, startPos, endPos, i);

                //将任务提交到线程池中
                Future<Boolean> future = poolExecutor.submit(downloaderTask);

                futureList.add(future);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
