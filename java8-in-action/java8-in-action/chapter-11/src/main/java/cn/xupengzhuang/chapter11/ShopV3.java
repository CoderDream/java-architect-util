package cn.xupengzhuang.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ShopV3 {
    private String shopName;

    public ShopV3(String shopName) {
        this.shopName = shopName;
    }

    public Future<Double> getFutureAsync(String productName){
        return CompletableFuture
                .supplyAsync(() -> calculatePrice(productName));
    }

    public Double calculatePrice(String product){
        deley();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void deley(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
