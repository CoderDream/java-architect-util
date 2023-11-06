package cn.xupengzhuang.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static cn.xupengzhuang.chapter05.Dish.*;

@Slf4j
public class NumericStreamTests {

    @Test
    public void test1(){
        int sum = menu.stream().mapToInt(m -> m.getCalories()).sum();
        log.info("{}",sum);
    }

    @Test
    public void test2(){
        IntStream intStream = menu.stream().mapToInt(d -> d.getCalories());
        Stream<Integer> boxed = intStream.boxed();
    }

    @Test
    public void test3(){
        OptionalInt max = menu.stream().mapToInt(d -> d.getCalories()).max();
        max.ifPresent(a -> System.out.println(a));
    }

    @Test
    public void test4(){
        IntStream.rangeClosed(1,10).forEach(System.out::println);
    }

    @Test
    public void test5(){
        Stream<double[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
