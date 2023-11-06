package cn.xupengzhuang.chapter06;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import static cn.xupengzhuang.chapter06.Dish.menu;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 * 分组函数测试
 */
@Slf4j
public class GroupingByTests {
    /**
     * 将菜单中的菜按照类型进行归类
     */
    @Test
    public void testGroupingBy(){
        Map<Dish.Type, List<Dish>> map = menu.stream().collect(groupingBy(Dish::getType));
        log.info("map={}", JSON.toJSONString(map));
    }

    @Test
    public void testGroupingByCustom(){
        Map<CaloriesType, List<Dish>> map = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() < 400) {
                return CaloriesType.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloriesType.NORMAL;
            } else {
                return CaloriesType.FAT;
            }
        }));
        log.info("map={}",JSON.toJSONString(map));
    }

    @Test
    public void testMultiGroupingBy(){
        Map<Dish.Type, Map<CaloriesType, List<Dish>>> map = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() < 400) {
                                return CaloriesType.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloriesType.NORMAL;
                            } else {
                                return CaloriesType.FAT;
                            }
                        }))
        );
        log.info("map={}",JSON.toJSONString(map));
    }

    @Test
    public void testGroupingByCounting(){
        Map<Dish.Type, Long> map = menu.stream().collect(groupingBy(Dish::getType, counting()));
        log.info("map={}",JSON.toJSONString(map));
    }

    @Test
    public void testGroupingByMaxBy(){
        Map<Dish.Type, Optional<Dish>> map = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
        log.info("map={}",JSON.toJSONString(map));
    }

    @Test
    public void testCollectAndThen(){
        Map<Dish.Type, Dish> map = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        log.info("map={}",JSON.toJSONString(map));
    }

    /**
     * 求出各类型中所有菜肴热量总和
     */
    @Test
    public void testGroupingByAndSumming(){
        Map<Dish.Type, Integer> collect = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        log.info("collect={}",JSON.toJSONString(collect));
    }

    @Test
    public void testGroupingByAndMapping(){
        Map<Dish.Type, Set<CaloriesType>> map = menu.stream().collect(groupingBy(Dish::getType, mapping(
                dish -> {
                    if (dish.getCalories() < 400) {
                        return CaloriesType.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloriesType.NORMAL;
                    } else {
                        return CaloriesType.FAT;
                    }
                }, toSet()
        )));
        log.info("map={}",JSON.toJSONString(map));
    }

    @Test
    public void testGroupingByAndToCollection(){
        Map<Dish.Type, Set<CaloriesType>> map = menu.stream().collect(groupingBy(Dish::getType, mapping(
                dish -> {
                    if (dish.getCalories() < 400) {
                        return CaloriesType.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloriesType.NORMAL;
                    } else {
                        return CaloriesType.FAT;
                    }
                }, toCollection(HashSet::new)
        )));
        log.info("map={}",JSON.toJSONString(map));
    }
}
