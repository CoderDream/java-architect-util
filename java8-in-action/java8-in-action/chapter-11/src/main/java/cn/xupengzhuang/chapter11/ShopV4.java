package cn.xupengzhuang.chapter11;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ShopV4 {
    private String shopName;

    public ShopV4() {
    }

    public ShopV4(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<String> findPrices(String product,List<Shop> shops){
        List<String> list = shops.stream()
                .map(shop -> getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
        return list;
    }


    public List<String> findPricesByCompletable(String product,List<Shop> shops){
        Executor executor = Executors.newFixedThreadPool(Math.min(100,shops.size()));

        List<CompletableFuture<String>> list = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        List<String> strings = list.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return strings;

    }

    public String getPrice(String productName){
        double price = calculatePrice(productName);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        String s = String.format("%s:%.2f:%s", productName, price, code);
        return s;
    }

    public double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
