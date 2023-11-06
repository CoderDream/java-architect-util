package cn.xupengzhuang.chapter11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * 同步方式API
 */
public class Shop {

    private String shopName;

    public Shop() {
    }

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getPrice(String productName){
        return calculatePrice(productName);
    }

    public double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在给定的shops中查找指定的商品的价格
     * @param product
     * @param shops
     * @return
     */
    public List<String> findPrices(String product,List<Shop> shops){
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",shop.getShopName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesByParallelStream(String product,List<Shop> shops){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",shop.getShopName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesByCompletableFuture(String product,List<Shop> shops){
        List<CompletableFuture<String>> futureList = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product))))
                .collect(Collectors.toList());

        return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public List<String> findPricesByCompletableFutureAndExecutor(String product,List<Shop> shops){
        /*Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100),(Runnable r) -> {
            Thread t = new Thread();
            //守护线程，不会阻止程序的关停
            t.setDaemon(true);
            return t;
        });*/

        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100));

        List<CompletableFuture<String>> futureList = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)),executor))
                .collect(Collectors.toList());

        return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }




}
