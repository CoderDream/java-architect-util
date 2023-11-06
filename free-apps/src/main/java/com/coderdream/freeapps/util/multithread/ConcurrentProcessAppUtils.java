package com.coderdream.freeapps.util.multithread;

import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.util.other.JSoupUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * https://blog.csdn.net/zhanglu1236789/article/details/107199023 java.util.concurrent多线程并行处理返回处理结果（计算一个list集合）
 */
public class ConcurrentProcessAppUtils {

    public static Integer THREAD_SIZE = 10;

    public static void main(String[] args) {
        List<PriceHistory> priceHistoryList = new ArrayList<>();
        for (int i = 1; i <= 21; i++) {
            PriceHistory priceHistory = new PriceHistory();
            priceHistoryList.add(priceHistory);
        }
        List<PriceHistory> priceHistories = ConcurrentProcessAppUtils.process(priceHistoryList);
        for (PriceHistory priceHistory : priceHistories) {
            System.out.println(priceHistory);
        }
    }

    /**
     * 多线程并发遍历list源数据 将每个线程处理结果返回
     *
     * @throws Exception
     */
    public static List<PriceHistory> process(List<PriceHistory> priceHistoryList) {
        List<PriceHistory> result = new ArrayList<>();
        //创建操作源数据 list集合
//        List<PriceHistory> list = Lists.newArrayList();

        //创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_SIZE);
        CompletionService<List<PriceHistory>> cpiService = new ExecutorCompletionService<>(exec);
        int f = 0;
        int i = 0;

        //分发线程
        while (true) {
            f = f + 1;
            int g = (i + THREAD_SIZE) > priceHistoryList.size() ? (priceHistoryList.size()) : (i + THREAD_SIZE);

            testCallable callable = new testCallable(f, priceHistoryList.subList(i, g));
            if (!exec.isShutdown()) {
                cpiService.submit(callable);
            }
            i = (g);
            if (i >= (priceHistoryList.size())) {
                break;
            }
        }
        System.out.println("f:" + f + ";i:" + i + ";size:" + priceHistoryList.size());
        //获取线程处理结果
        List<PriceHistory> priceHistories = null;
        for (int h = 0; h < f; h++) {
            try {
                priceHistories = cpiService.take().get();
                result.addAll(priceHistories);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
        //关闭多线程池
        exec.shutdown();

        return result;
    }

    //创建多线程处理类
    static class testCallable implements Callable<List<PriceHistory>> {

        private int flag;
        private List<PriceHistory> priceHistories;

        public testCallable(int flag, List<PriceHistory> priceHistories) {
            this.flag = flag;
            this.priceHistories = priceHistories;
        }

        public testCallable() {
        }

        @Override
        public List<PriceHistory> call() throws Exception {
            priceHistories.stream().forEach(e -> printPriceHistory(flag, e));
            return priceHistories;
        }

        public void printPriceHistory(int i, PriceHistory priceHistory) {
            PriceHistory priceHistoryNew = JSoupUtil.crawlerAppPrice(priceHistory.getAppId(), priceHistory.getUsFlag());
//            System.out.println(priceHistoryNew.getAppId() + "#########" + priceHistoryNew.getPriceStr());
            priceHistory.setPriceStr(priceHistoryNew.getPriceStr());
//            System.out.println("call-" + i + "-[" + priceHistory + "]");
        }
    }
}

