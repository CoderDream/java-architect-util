package cn.xupengzhuang.chapter08;

import cn.xupengzhuang.chapter08.pojo.Apple;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static cn.xupengzhuang.chapter08.Dish.*;
import static java.util.stream.Collectors.*;

/**
 * 从 lambda 表达式到方法引用的转换
 */
@Slf4j
public class RefactorExample02 {

    /**
     * 根据菜单的热量来进行分类
     */
    @Test
    public void test1(){
        Map<CaloriesLevel, List<Dish>> map = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloriesLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloriesLevel.NORMAL;
            } else {
                return CaloriesLevel.FAT;
            }
        }));
        log.info("{}", map);
    }

    /**
     * 将test1中的lambda表达式抽取到一个单独的方法中，将其作为参数传递给groupingBy方法
     */
    @Test
    public void test2(){
        Map<CaloriesLevel, List<Dish>> map = menu.stream().collect(groupingBy(RefactorExample02::getCaloriesLevel));
        log.info("{}",map);
    }

    public static CaloriesLevel getCaloriesLevel(Dish dish){
        if (dish.getCalories() <= 400) {
            return CaloriesLevel.DIET;
        } else if (dish.getCalories() <= 700) {
            return CaloriesLevel.NORMAL;
        } else {
            return CaloriesLevel.FAT;
        }
    }

    @Test
    public void test3(){
        List<Apple> apples = Arrays.asList(new Apple("red", 100),
                new Apple("green", 50),
                new Apple("yellow", 150),
                new Apple("blue", 40));
        apples.sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        log.info("{}",apples);
    }

    /**
     * 应该尽量使用comparing、maxBy这些方法来替代test3中的lambda表达式的写法
     * 因为这些方法设计之初就是考虑到结合方法引用一起来使用的
     */
    @Test
    public void test4(){
        List<Apple> apples = Arrays.asList(new Apple("red", 100),
                new Apple("green", 50),
                new Apple("yellow", 150),
                new Apple("blue", 40));
        apples.sort(Comparator.comparing(Apple::getWeight));
        log.info("{}",apples);
    }

    @Test
    public void test5(){
        Integer caloriesSum = menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
        log.info("{}",caloriesSum);

        Integer collectSum = menu.stream().collect(summingInt(Dish::getCalories));
        log.info("{}",collectSum);
    }

}
