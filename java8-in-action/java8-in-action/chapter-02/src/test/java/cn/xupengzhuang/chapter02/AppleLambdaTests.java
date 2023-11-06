package cn.xupengzhuang.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AppleLambdaTests {

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
    public void test1(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> heavyApples = filter(inventory, (Apple apple) -> apple.getWeight() > 150);
        log.info("{}",heavyApples);
        List<Apple> greenApples = filter(inventory, (Apple apple) -> "green".equals(apple.getColor()));
        log.info("{}",greenApples);

    }

    /**
     * 进一步抽象filter方法
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T t : list){
            if (p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    @Test
    public void test2(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        List<Apple> heavyApples = filter(inventory, (Apple apple) -> apple.getWeight() > 150);
        log.info("{}",heavyApples);
    }

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> res = filter(list, (Integer i) -> i % 2 == 0);
        log.info("{}",res);
    }




}
