package cn.xupengzhuang.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static cn.xupengzhuang.chapter05.Dish.*;
import static java.util.stream.Collectors.*;

/**
 * 筛选
 */
@Slf4j
public class FilterTests {

    @Test
    public void test1(){
        List<Dish> list = menu.stream().filter(Dish::isVegetarian).collect(toList());
        log.info("{}",list);
    }

    /**
     * 筛选偶数，并去重
     */
    @Test
    public void test2(){
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list = nums.stream().filter(i -> i % 2 == 0).distinct().collect(toList());
        log.info("{}",list);
    }

    /**
     * 截断流
     */
    @Test
    public void test3(){
        List<Dish> list = menu.stream().filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        log.info("{}",list);
    }

    /**
     * 跳过元素
     */
    @Test
    public void test4(){
        List<Dish> list = menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
        log.info("{}",list);
    }

}
