package cn.xupengzhuang.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造函数引用测试
 */
@Slf4j
public class CtorRefTests {

    @Test
    public void test1(){
        Supplier<Apple> s = Apple::new;
        Apple apple = s.get();
        apple.setColor("green");
        apple.setWeight(200);
        log.info("{}",apple);
    }

    @Test
    public void test2(){
        BiFunction<String,Integer,Apple> bf = Apple::new;
        Apple yellowApple = bf.apply("yellow", 220);
        log.info("{}",yellowApple);
    }

    @Test
    public void test3(){
        List<Integer> weights = Arrays.asList(50, 80, 40, 150);
        List<Apple> map = map(weights, Apple::new);
        log.info("{}",map);
    }

    public static List<Apple> map(List<Integer> weights, Function<Integer,Apple> f){
        List<Apple> result = new ArrayList<>();
        for (Integer weight :weights){
            Apple apple = f.apply(weight);
            result.add(apple);
        }
        return result;
    }

}
