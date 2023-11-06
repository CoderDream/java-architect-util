package cn.xupengzhuang.chapter04;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xupengzhuang.chapter04.Dish.*;

@Slf4j
public class DishTests {

    /**
     * 1、选出400卡路里以下的菜肴
     * 2、按照卡路里排序
     * 3、提取菜肴的名称
     * 4、将所有名称保存在List中
     */
    @Test
    public void test1(){
        List<String> list = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(d -> d.getName())
                .collect(Collectors.toList());
        log.info("{}",list);
    }

    @Test
    public void test2(){
        List<String> list = menu.stream().filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        log.info("{}",list);
    }


}
