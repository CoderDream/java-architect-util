package cn.xupengzhuang.chapter08;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PeekTests {

    @Test
    public void test1(){
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream().map(a -> a + 17)
                .filter(b -> b % 2 == 0)
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        List<Integer> list = numbers.stream()
                .peek(x -> System.out.println("from stream:" + x))
                .map(a -> a + 17)
                .peek(x -> System.out.println("after map:" + x))
                .filter(b -> b % 2 == 0)
                .peek(x -> System.out.println("after filter:" + x))
                .limit(3)
                .peek(x -> System.out.println("after limit:" + x))
                .collect(Collectors.toList());
        log.info("{}",list);
    }

}
