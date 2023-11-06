package cn.xupengzhuang.chapter08;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.xupengzhuang.chapter08.Dish.*;
import static java.util.stream.Collectors.*;

/**
 * 从命令式的数据处理切换到Stream
 */
@Slf4j
public class RefactorExample03 {

    @Test
    public void test1(){
        List<String> names= new ArrayList<>();
        for (Dish dish : menu){
            if (dish.getCalories() > 300){
                names.add(dish.getName());
            }
        }
        log.info("{}",names);//[pork, beef, chicken, french fries, rice, pizza, prawns, salmon]
    }

    @Test
    public void test2(){
        List<String> stringList = menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).collect(toList());
        log.info("{}",stringList);//[pork, beef, chicken, french fries, rice, pizza, prawns, salmon]
    }

}
