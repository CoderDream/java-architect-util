package cn.xupengzhuang.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.*;

@Slf4j
public class ReduceTests {

    /**
     * 给定一个数字列表，求和
     */
    @Test
    public void test1(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        log.info("{}",sum);
    }

    /**
     * 求最大值和最小值
     */
    @Test
    public void test2(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> maxVal = numbers.stream().reduce((a,b) -> a > b ? a : b);
        maxVal.ifPresent(a -> System.out.println(a));

        Optional<Integer> minVal = numbers.stream().reduce((a, b) -> a < b ? a : b);
        minVal.ifPresent(a -> System.out.println(a));

        //写法二
        Optional<Integer> max = numbers.stream().max((a, b) -> a > b ? 1 : -1);
        max.ifPresent(a -> System.out.println(a));
    }


}
