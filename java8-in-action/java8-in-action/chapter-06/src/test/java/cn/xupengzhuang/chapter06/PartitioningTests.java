package cn.xupengzhuang.chapter06;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cn.xupengzhuang.chapter06.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * 分区函数测试
 */
@Slf4j
public class PartitioningTests {

    @Test
    public void testPartitionByIsVegetarian(){
        Map<Boolean, List<Dish>> map = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        log.info("{}",map);
    }

    @Test
    public void testPartitioningByAndGroupingBy(){
        Map<Boolean, Map<Dish.Type, List<Dish>>> map = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        log.info("{}",map);
    }

    @Test
    public void testPartitioningByAndMaxBy(){
        Map<Boolean, Dish> map = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        log.info("{}",map);
    }

    @Test
    public void testPartitioningByAnd(){
        Map<Boolean, Map<Boolean, List<Dish>>> map = menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(dish -> dish.getCalories() > 500)));
        log.info("{}",map);
    }

    @Test
    public void testPartitioningCounting(){
        Map<Boolean, Long> map = menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        log.info("{}",map);
    }

    public boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(partitioningBy(a -> isPrime(a)));
    }

    @Test
    public void testIsPrime(){
        Map<Boolean, List<Integer>> map = partitionPrimes(20);
        List<Integer> list = map.get(true);
        log.info("{}",list);
    }

    /**
     * 测试 partitionPrimes 的性能
     */
    @Test
    public void testHarness(){
        long fastest = Integer.MAX_VALUE;
        for (int i=0;i<10;i++){
            long start = System.nanoTime();
            partitionPrimes(1000000);
            //取运行时间的毫秒值
            long duration = (System.nanoTime() -  start) / 1000000;
            if (duration < fastest){
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
        //Fastest execution done in 361 msecs

    }

}
