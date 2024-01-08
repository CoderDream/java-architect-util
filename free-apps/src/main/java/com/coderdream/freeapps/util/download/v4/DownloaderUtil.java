package com.coderdream.freeapps.util.download.v4;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 下载器
 *
 * @author CoderDream
 */
public class DownloaderUtil {

    public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    /**
     * 线程池对象
     */
    public ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Constant.THREAD_NUM, Constant.THREAD_NUM, 0,
        TimeUnit.SECONDS, new ArrayBlockingQueue<>(Constant.THREAD_NUM));

    private CountDownLatch countDownLatch = new CountDownLatch(Constant.THREAD_NUM);

    public void download(String fileUrl, String path, String fileName, Boolean proxyFlag) {
        //获取文件名
        String httpFileName = fileName; //HttpUtils.getHttpFileName(fileUrl);
        //文件下载路径
        httpFileName = path + httpFileName;

        //获取本地文件的大小
        long localFileLength = FileUtils.getFileContentLength(httpFileName);

        //获取连接对象
        HttpURLConnection httpURLConnection = null;
        DownloadInfoThread downloadInfoThread = null;
        try {
            httpURLConnection = HttpUtils.getHttpURLConnection(fileUrl, proxyFlag);
            //获取下载文件的总大小
            int contentLength = httpURLConnection.getContentLength();

            //判断文件是否已下载过
            if (localFileLength >= contentLength) {
                LogUtils.info("{}已下载完毕a，无需重新下载", httpFileName);
                return;
            }

            //创建获取下载信息的任务对象
            downloadInfoThread = new DownloadInfoThread(contentLength);
            //将任务交给线程执行，每隔1秒执行一次
            scheduledExecutorService.scheduleAtFixedRate(downloadInfoThread, 1, 1, TimeUnit.SECONDS);

            //切分任务
            ArrayList<Future> list = new ArrayList<>();
            spilt(fileUrl, list, path, fileName, proxyFlag);

            countDownLatch.await();

            //合并文件
            if (merge(httpFileName)) {
                //清除临时文件
                clearTemp(httpFileName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
    public void spilt(String url, ArrayList<Future> futureList, String path, String fileName, Boolean proxyFlag) {

        try {
            //获取下载文件大小
            long contentLength = HttpUtils.getHttpFileContentLength(url, proxyFlag);

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
                DownloaderTask downloaderTask = new DownloaderTask(url, startPos, endPos, i, countDownLatch, path,
                    fileName, proxyFlag);

                //将任务提交到线程池中
                Future<Boolean> future = poolExecutor.submit(downloaderTask);

                futureList.add(future);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件合并
     *
     * @param fileName
     * @return
     */
    public boolean merge(String fileName) {
        LogUtils.info("开始合并文件{}", fileName);
        byte[] buffer = new byte[Constant.BYTE_SIZE];
        int len = -1;
        try (RandomAccessFile accessFile = new RandomAccessFile(fileName, "rw")) {
            for (int i = 0; i < Constant.THREAD_NUM; i++) {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName + ".temp" + i))) {
                    while ((len = bis.read(buffer)) != -1) {
                        accessFile.write(buffer, 0, len);
                    }
                }
            }
            LogUtils.info("文件合并完毕{}" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 清除临时文件
     */
    public boolean clearTemp(String fileName) {
        for (int i = 0; i < Constant.THREAD_NUM; i++) {
            File file = new File(fileName + ".temp" + i);
            file.delete();
        }

        return true;
    }
}
