package cn.xupengzhuang.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static cn.xupengzhuang.chapter05.Dish.*;

@Slf4j
public class FindAndMatchTests {

    /**
     * 菜单中是否有素菜
     */
    @Test
    public void test1(){
        boolean b = menu.stream().anyMatch(Dish::isVegetarian);
        if (b){
            log.info("存在");
        }else {
            log.info("菜单中没有素材");
        }
    }

    @Test
    public void test2(){
        boolean b = menu.stream().allMatch(d -> d.getCalories() < 2000);
        log.info("{}",b);
    }

    @Test
    public void test3(){
        boolean b = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        log.info("{}",b);
    }

    @Test
    public void test4(){
        Optional<Dish> any = menu.stream().filter(d -> d.isVegetarian()).findAny();
        any.ifPresent(a -> System.out.println(a.getName()));
    }

    /**
     * 给定一个数字列表，找出第一个平方能被3整除的数：
     */
    @Test
    public void test5(){
        List<Integer> numbers = Arrays.asList(2, 4, 5, 7, 8, 9);
        Optional<Integer> first = numbers.stream().filter(i -> i % 3 == 0).findFirst();
        first.ifPresent(a -> System.out.println(a));
    }

}
