package cn.xupengzhuang.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static cn.xupengzhuang.chapter05.Dish.*;
import static java.util.stream.Collectors.*;

/**
 * 映射
 * 流支持map方法，map接收一个函数作为参数，这个函数会映射到流中的每个元素上面。
 */
@Slf4j
public class MapTests {

    @Test
    public void test1(){
        List<String> list = menu.stream().map(Dish::getName).collect(toList());
        log.info("{}",list);
    }

    /**
     * 求每个单词的长度
     */
    @Test
    public void test2(){
        List<String> strings = Arrays.asList("Java", "In", "Action");
        List<Integer> list = strings.stream().map(String::length).collect(toList());
        log.info("{}",list);
    }

    /**
     * 求每道菜名字的长度
     */
    @Test
    public void test3(){
        List<Integer> list = menu.stream().map(Dish::getName).map(String::length).collect(toList());
        log.info("{}",list);
    }

    /**
     * 流的扁平化
     */
    @Test
    public void test4(){
        List<String> strings = Arrays.asList("hello", "world");
        List<String> list = strings
                .stream()
                .map(s -> s.split(""))
                //.flatMap((String[] array) -> Arrays.stream(array))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        log.info("{}",list);
    }

    /**
     * 给定两个数表，返回所有的数对
     */
    @Test
    public void test5(){
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(4, 5, 6);
        List<int[]> numArr = num1.stream().flatMap(i -> num2.stream().map(j -> new int[]{i, j})).collect(toList());
        numArr.forEach(a -> System.out.print("("+a[0]+","+a[1]+")"));
    }



}
