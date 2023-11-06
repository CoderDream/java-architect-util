package cn.xupengzhuang.chapter11;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ShopV4Tests {

    @Test
    public void test1(){
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("FamilyMart"),
                new Shop("7days"));
        long start = System.nanoTime();
        List<String> waterList = new ShopV4().findPrices("Water",shops);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        log.info("duration={}毫秒",duration);
        log.info("waterList={}", JSON.toJSONString(waterList));
    }

    @Test
    public void test2(){
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("FamilyMart"),
                new Shop("7days"));
        long start = System.nanoTime();
        List<String> waterList = new ShopV4().findPricesByCompletable("Water",shops);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        log.info("duration={}毫秒",duration);
        log.info("waterList={}", JSON.toJSONString(waterList));
    }

}
