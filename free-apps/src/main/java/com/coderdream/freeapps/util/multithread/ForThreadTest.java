package com.coderdream.freeapps.util.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.Data;

public class ForThreadTest {

    public static void main(String[] args) {
        List<User> list = getList();
        test1(list);
        test2(list);
    }

    //常规的单线程处理方法
    static void test1(List<User> list) {
        long start = System.currentTimeMillis();
        for (User user : list) {
            sendRequest(user);
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程方法，耗时：" + (end - start) + "ms");
    }

    /**
     *
     多线程
     */
    static void test2(List<User> list) {
        long start = System.currentTimeMillis();

        // https://blog.csdn.net/hello_cmy/article/details/118895056
        // 手动创建线程池，效果会更好哦
//        ExecutorService executor = new ThreadPoolExecutor(
//            2,
//            10,
//            3,
//            TimeUnit.SECONDS,
//            new LinkedBlockingDeque<>(3),
//            Executors.defaultThreadFactory(),
//            new ThreadPoolExecutor.DiscardOldestPolicy());

        ExecutorService executor = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (User user : list) {
            executor.execute(new Task(user, countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {

        } finally {
            executor.shutdown();
        }
        long end = System.currentTimeMillis();
        System.out.println("多线程方法，耗时：" + (end - start) + "ms");
    }


    //假设有500条数据
    static List<User> getList() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 501; i++) {
            User user = new User();
            user.setIdCard(i + "");
            list.add(user);
        }
        return list;
    }

    //模拟请求第三方接口：根据身份证号获取姓名
    static void sendRequest(User user){
        //模拟接口请求耗时
        try {
            Thread.sleep(10);
        } catch (Exception e){}
        //模拟获取请求结果后做的一些事
        user.setName("张三-" + user.getIdCard());
        System.out.println("=====> 发送请求： name=" + user.getName());
    }

    static class Task implements  Runnable {

        private User user;
        private CountDownLatch countDownLatch;

        public Task(User user, CountDownLatch countDownLatch) {
            this.user = user;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                sendRequest(user);
            } catch (Exception e) {

            } finally {
                countDownLatch.countDown();
            }
        }
    }
}

@Data
class User {
    private String idCard;
    private String name;
}
