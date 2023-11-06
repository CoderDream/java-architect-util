package cn.xupengzhuang.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 复合Lambda表达式
 */
@Slf4j
public class ComplexLambdaTests {

    /**
     * 比较器复合
     */
    @Test
    public void test1(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        Comparator<Apple> comparing = Comparator.comparing(Apple::getWeight);
        inventory.sort(comparing);
        log.info("{}",inventory);

    }

    /**
     * 比较器复合逆序
     */
    @Test
    public void test2(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        Comparator<Apple> comparing = Comparator.comparing(Apple::getWeight).reversed();
        inventory.sort(comparing);
        log.info("{}",inventory);
    }

    /**
     * 比较器链
     */
    @Test
    public void test3(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120),
                new Apple("yellow",120));
        Comparator<Apple> comparing = Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor);
        inventory.sort(comparing);
        log.info("{}",inventory);
    }

    /**
     * 谓词复合
     */
    @Test
    public void test4(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120),
                new Apple("yellow",120));

        Predicate<Apple> redApple = s -> "red".equals(s.getColor());
        Predicate<Apple> redAndGreenAndHeavy = redApple.or(s -> "green".equals(s.getColor())).and(s -> s.getWeight() > 100);
        List<Apple> appleList = inventory.stream().filter(redAndGreenAndHeavy).collect(Collectors.toList());
        log.info("{}",appleList);
    }

    /**
     * 函数复合
     */
    @Test
    public void test5(){
        Function<Integer,Integer> f1 = x -> x + 1;
        Function<Integer,Integer> f2 = x -> x * 2;
        Function<Integer, Integer> andThenFunc = f1.andThen(f2);
        Integer integer = andThenFunc.apply(1);
        log.info("andThenFunc={}",integer);

        Function<Integer, Integer> composeFunc = f1.compose(f2);
        Integer apply = composeFunc.apply(1);
        log.info("composeFunc={}",apply);


    }
}
