package cn.xupengzhuang.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class ComparatorTests {
    @Test
    public void test1(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        log.info("{}",inventory);
    }

    @Test
    public void test2(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));

        inventory.sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        log.info("{}",inventory);
    }

    @Test
    public void test3(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        log.info("{}",inventory);
    }

}
