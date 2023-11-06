package cn.xupengzhuang.chapter01;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AppleTests {

    /**
     * 筛选出所有的绿苹果
     */
    @Test
    public void test1(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : inventory){
            if ("green".equals(apple.getColor())){
                greenApples.add(apple);
            }
        }
        log.info("all green apples={}",greenApples);
    }

    /**
     * 筛选重量超过150g的苹果
     */
    @Test
    public void test2(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> heavyApples = new ArrayList<>();
        for (Apple apple : inventory){
            if (apple.getWeight() > 150){
                heavyApples.add(apple);
            }
        }
        log.info("heavy apples={}",heavyApples);
    }

    /**
     * 采用Java8的写法，将条件代码作为参数进行传递
     */
    @Test
    public void test3(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> greenApples = filterApples(inventory, Apple::isGreenApple);
        log.info("greenApples={}",greenApples);

        List<Apple> heavyApples = filterApples(inventory, Apple::isHeavyApple);
        log.info("heavyApples={}",heavyApples);
    }


    public List<Apple> filterApples(List<Apple> inventory,Predicate<Apple> p){
        List<Apple> filterApples = new ArrayList<>();
        for (Apple apple:inventory){
            boolean test = p.test(apple);
            if (test){
                filterApples.add(apple);
            }
        }
        return filterApples;
    }

    @Test
    public void test4(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        List<Apple> heavyApples = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        log.info("heavyApples={}",heavyApples);

        List<Apple> greenApples = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        log.info("greenApples={}",greenApples);

    }

    /**
     * 使用库方法filter
     */
    @Test
    public void test5(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> heavyApples = inventory.stream().filter(a -> a.getWeight() > 150).collect(Collectors.toList());
        log.info("heavyApples={}",heavyApples);
    }


}
