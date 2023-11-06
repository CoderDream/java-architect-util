package cn.xupengzhuang.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AppleTests {

    /**
     * 筛选绿苹果 第一个解决方案
     */
    public List<Apple> filterGreenApples(List<Apple> apples){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: apples){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test1(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> apples = filterGreenApples(inventory);
        log.info("{}",apples);
    }

    /**
     * 筛选绿苹果，并加入颜色参数
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory,
                                                  String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ( apple.getColor().equals(color) ) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test2(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> apples = filterApplesByColor(inventory,"green");
        log.info("{}",apples);
    }

    /**
     * 加入重量
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ( apple.getWeight() > weight ){
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test3(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        List<Apple> apples = filterApplesByWeight(inventory, 150);
        log.info("{}",apples);
    }

    public List<Apple> filter(List<Apple> apples, ApplePredicate applePredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples){
            if (applePredicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test4(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> greenApples = filter(inventory, new AppleGreenColorPredicate());
        List<Apple> heavyApples = filter(inventory, new AppleHeavyWeightPredicate());
        List<Apple> redAndGreenApples = filter(inventory, new AppleRedAndHeavyPredicate());

        log.info("{}",greenApples);
        log.info("{}",heavyApples);
        log.info("{}",redAndGreenApples);
    }

    /**
     * 使用匿名类
     */
    @Test
    public void test5(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> heavyApples = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        });
        log.info("{}",heavyApples);

        List<Apple> greenApples = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        log.info("{}",greenApples);
    }




}
