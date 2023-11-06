package com.coderdream.freeapps.util.multithread;

import cn.hutool.core.thread.ThreadUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.ThreadUtils;
import org.checkerframework.checker.units.qual.A;

/**
 * @author CoderDream
 */
public class MyFuture {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        8,
        16,
        15,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(15),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy()
    );

//    private Archi

    public void search(final String keywords) {

        Future<List<String>> future = threadPoolExecutor.submit(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                //ThreadUtils.sleep(2);
                Thread.sleep(2000);
                Random random = new Random(10);
                return Collections.singletonList(random.nextInt(10) + "_" + keywords);
            }
        });
        // doOtherThings(); // 当上面正在搜索的时候，此处也可以做点别的事情

        try {

            System.out.println(future.isDone()); // false

//        List<String> result = future.get(); // 从future中获取结果

            List<String> result = future.get(3000, TimeUnit.MILLISECONDS);

            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MyFuture myFuture = new MyFuture();
        List<String> listStr = new ArrayList<>(); //Arrays.asList("abc","efg",);
        for (int i = 0; i < 1000; i++) {
            listStr.add("temp_" + i);
        }
        for (String str : listStr) {
            myFuture.search(str);
        }

    }
}
