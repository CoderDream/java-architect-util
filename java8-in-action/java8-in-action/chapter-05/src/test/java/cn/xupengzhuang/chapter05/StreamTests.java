package cn.xupengzhuang.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamTests {

    @Test
    public void test1(){
        Stream<String> java8 = Stream.of("Java8", "In", "Action");
        java8.forEach(System.out::println);
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5};
        IntStream intStream = Arrays.stream(arr);
        intStream.forEach(System.out::println);
    }

    @Test
    public void test3(){
        Stream.iterate(1,x -> x + 1).limit(10).forEach(a -> System.out.println(a));
    }

    @Test
    public void test4(){
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
