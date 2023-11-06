package com.coderdream.freeapps.util.multithread;

import cn.hutool.core.thread.ThreadUtil;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.util.other.JSoupUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author CoderDream
 */
public class Demo02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test10();
    }

    public static void test10() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        List<TestUser> userList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TestUser testUser = new TestUser();
            testUser.setUsername("a" + i);
            testUser.setAge(1);
            userList.add(testUser);
        }
        List<Future> futureList = new ArrayList<>();
        for (TestUser testUser : userList) {
            Future<TestUser> future = ThreadUtil.execAsync(new Callable<TestUser>() {
                @Override
                public TestUser call() throws Exception {
//                    Thread.sleep(1000);
                    Thread.sleep(200);
                    testUser.setAge(testUser.getAge() + 1);
                    return testUser;
                }
            });
            futureList.add(future);
        }
        List<TestUser> testUserList = new ArrayList<>();
        for (Future<TestUser> future : futureList) {
            TestUser testUser = future.get();
            testUserList.add(testUser);
        }
        for (TestUser testUser : testUserList) {
            System.out.println(testUser.toString());
        }

        long end = System.currentTimeMillis();
        System.out.println("多线程execAsync方法，耗时：" + (end - start) + "ms");
    }


    public static List<AppEntity> batchCrawlerAppInfo(List<AppEntity> list) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

//        List<TestUser> userList = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            TestUser testUser = new TestUser();
//            testUser.setUsername("a" + i);
//            testUser.setAge(1);
//            userList.add(testUser);
//        }
        List<AppEntity> result = new ArrayList<>();
        List<Future> futureList = new ArrayList<>();
        for (AppEntity appEntity : list) {
            Future<AppEntity> future = ThreadUtil.execAsync(new Callable<AppEntity>() {
                @Override
                public AppEntity call() throws Exception {
//                    Thread.sleep(1000);
//                    Thread.sleep(200);
                    //testUser.setAge(testUser.getAge() + 1);
                    AppEntity appNew = JSoupUtil.crawlerApp(appEntity);
                    BeanUtils.copyProperties(appNew, appEntity);
                    return appEntity;
                }
            });
            futureList.add(future);
        }
//        List<TestUser> testUserList = new ArrayList<>();
        for (Future<AppEntity> future : futureList) {
            AppEntity appEntity  = future.get();
            result.add(appEntity);
        }
        for (AppEntity appEntity : result) {
            System.out.println(appEntity.toString());
        }

        long end = System.currentTimeMillis();
        System.out.println("多线程execAsync方法，耗时：" + (end - start) + "ms");
        return result;
    }

    public static List<PriceHistory> batchCrawlerAppPriceInfo(List<PriceHistory> list)  {
        long start = System.currentTimeMillis();

//        List<TestUser> userList = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            TestUser testUser = new TestUser();
//            testUser.setUsername("a" + i);
//            testUser.setAge(1);
//            userList.add(testUser);
//        }
        List<PriceHistory> result = new ArrayList<>();
        List<Future> futureList = new ArrayList<>();
        for (PriceHistory priceHistory : list) {
            Future<PriceHistory> future = ThreadUtil.execAsync(new Callable<PriceHistory>() {
                @Override
                public PriceHistory call() throws Exception {
//                    Thread.sleep(1000);
//                    Thread.sleep(200);
                    //testUser.setAge(testUser.getAge() + 1);
                    AppEntity appEntityReq = new AppEntity();
                    BeanUtils.copyProperties(priceHistory, appEntityReq);

                    AppEntity appNew = JSoupUtil.crawlerApp(appEntityReq);
                    BeanUtils.copyProperties(appNew, priceHistory);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStr = dateFormat.format(new Date());
                    try {
                        priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
//                        log.info("priceHistory: " + priceHistory);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    return priceHistory;
                }
            });
            futureList.add(future);
        }
//        List<TestUser> testUserList = new ArrayList<>();
        for (Future<PriceHistory> future : futureList) {
            PriceHistory appEntity  = null;
            try {
                appEntity = future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            result.add(appEntity);
        }
        for (PriceHistory appEntity : result) {
            System.out.println(appEntity.toString());
        }

        long end = System.currentTimeMillis();
        System.out.println("多线程execAsync方法，耗时：" + (end - start) + "ms");
        return result;
    }
}

@Data
class TestUser {
    private String username;
    private Integer age;
}
