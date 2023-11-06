package cn.xupengzhuang.chapter11;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Slf4j
public class ShopTests {

    @Test
    public void test1(){
        ShopV2 shopV2 = new ShopV2("FamilyMart");
        Future<Double> future = shopV2.getPriceAsync("Water");
        System.out.println("do something else");
        Double aDouble = null;
        try {
            aDouble = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("finish,the price is=" + aDouble);

    }

    @Test
    public void test2(){
        ShopV3 shopV3 = new ShopV3("FamilyMart");
        Future<Double> future = shopV3.getFutureAsync("Water");
        System.out.println("do something else");
        try {
            Double aDouble = future.get();
            System.out.println("finish,the price is=" + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3(){
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("FamilyMart"));
        long start = System.nanoTime();
        List<String> waterList = new Shop().findPrices("Water",shops);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        log.info("duration={}毫秒",duration);
        log.info("waterList={}", JSON.toJSONString(waterList));


    }

    @Test
    public void test4(){
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("FamilyMart"),
                new Shop("7days"),
                new Shop("Apple"),
                new Shop("Microsoft"),
                new Shop("Amazon"),
                new Shop("Huawei"),
                new Shop("Xiaomi"),
                new Shop("Oppo"),
                new Shop("Vivo"),
                new Shop("Sangsung"),
                new Shop("Google"),
                new Shop("Baidu"));
        long start = System.nanoTime();
        List<String> waterList = new Shop().findPricesByParallelStream("Water",shops);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        log.info("duration={}毫秒",duration);
        log.info("waterList={}", JSON.toJSONString(waterList));
    }

    @Test
    public void test5(){
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("FamilyMart"),
                new Shop("7days"),
                new Shop("Apple"),
                new Shop("Microsoft"),
                new Shop("Amazon"),
                new Shop("Huawei"),
                new Shop("Xiaomi"),
                new Shop("Oppo"),
                new Shop("Vivo"),
                new Shop("Sangsung"),
                new Shop("Google"),
                new Shop("Baidu"));
        long start = System.nanoTime();
        List<String> waterList = new Shop().findPricesByCompletableFuture("Water",shops);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        log.info("duration={}毫秒",duration);
        log.info("waterList={}", JSON.toJSONString(waterList));
    }

    @Test
    public void test6(){
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors=" + i);
    }

    @Test
    public void test7(){
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("FamilyMart"),
                new Shop("7days"),
                new Shop("Apple"),
                new Shop("Microsoft"),
                new Shop("Amazon"),
                new Shop("Huawei"),
                new Shop("Xiaomi"),
                new Shop("Oppo"),
                new Shop("Vivo"),
                new Shop("Sangsung"),
                new Shop("Google"),
                new Shop("Baidu"));
        long start = System.nanoTime();
        List<String> waterList = new Shop().findPricesByCompletableFutureAndExecutor("Water",shops);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        log.info("duration={}毫秒",duration);
        log.info("waterList={}", JSON.toJSONString(waterList));
    }



}
