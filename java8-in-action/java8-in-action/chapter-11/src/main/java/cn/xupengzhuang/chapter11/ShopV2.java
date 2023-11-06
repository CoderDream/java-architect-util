package cn.xupengzhuang.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ShopV2 {

    private String shopName;

    public ShopV2(String shopName) {
        this.shopName = shopName;
    }

    public Future<Double> getPriceAsync(String productName){
        //创建CompletableFuture对象，他会包含计算的结果
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        //在另一个线程中以异步方式执行计算
        new Thread(() -> {
            try {
                Double aDouble = calculatePrice(productName);
                completableFuture.complete(aDouble);
            } catch (Exception e) {
                // 如果不使用completableFuture.completeExceptionally(e),
                // 用于提示错误的异常会被限制在试图计算商品价格的当前线程的范围内，最终会杀死该线程，
                // 而这会导致等待get方法返回结果的客户端永久地被阻塞。
                completableFuture.completeExceptionally(e);
            }
        }).start();
        return completableFuture;
    }

    public Double calculatePrice(String product){
        deley();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public void deley(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
