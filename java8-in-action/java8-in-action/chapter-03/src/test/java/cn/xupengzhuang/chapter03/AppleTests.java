package cn.xupengzhuang.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 方法引用
 * 支持三种方法引用：实例方法引用、静态方法引用、现有对象的实例方法引用
 */
@Slf4j
public class AppleTests {
    /**
     * 实例方法引用
     */
    @Test
    public void test1(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        log.info("{}",inventory);
    }

    /**
     * 静态方法引用
     */
    @Test
    public void test2(){
        List<Apple> inventory = Arrays.asList(new Apple("green",80),
                new Apple("green",155),
                new Apple("red",120));
        List<Apple> greenApples = inventory.stream().filter(Apple::isGreenApple).collect(Collectors.toList());
        log.info("{}",greenApples);
    }

    @Test
    public void test3(){
        List<String> strings = Arrays.asList("1", "2", "3");
        List<Integer> integers = parseStingToX(strings, Integer::parseInt);
        log.info("{}",integers);
        List<Double> doubles = parseStingToX(strings, Double::parseDouble);
        log.info("{}",doubles);
    }

    public <T,R> List<R> parseStingToX(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for (T t : list){
            R r = f.apply(t);
            result.add(r);
        }
        return result;
    }

    @Test
    public void test4(){
        List<String> languages = Arrays.asList("Java", "Golang", "PHP", "C#");
        boolean contain = contain(List::contains, languages, "Java");
        log.info("{}",contain);

    }

    public <T,U> boolean contain(BiPredicate<List<T>, U> p,List<T> list,U u){
        return p.test(list,u);
    }

    @Test
    public void test5(){
        String s = "JavaInAction";
        boolean a1 = contain(String::contains, s, "Java");
        log.info("{}",a1);
    }

    public boolean contain(BiPredicate<String,CharSequence> p, String s, CharSequence c){
        return p.test(s,c);
    }



}
