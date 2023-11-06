package cn.xupengzhuang.chapter11;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShopV5 {

    private String shopName;

    public ShopV5() {
    }

    public ShopV5(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private static final Random random = new Random();

    public Stream<CompletableFuture<String>> findPricesStream(String product,List<Shop> shops){
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100));

        Stream<CompletableFuture<String>> stream = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

        return stream;
    }

    public String getPrice(String productName){
        double price = calculatePrice(productName);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        String s = String.format("%s:%.2f:%s", productName, price, code);
        return s;
    }

    public Double calculatePrice(String product){
        randomDelay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void randomDelay(){
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
