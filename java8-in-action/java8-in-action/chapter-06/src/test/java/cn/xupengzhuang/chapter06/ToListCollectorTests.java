package cn.xupengzhuang.chapter06;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static cn.xupengzhuang.chapter06.Dish.*;

@Slf4j
public class ToListCollectorTests {
    @Test
    public void testToListCollector(){
        List<Dish> list = menu.stream().filter(Dish::isVegetarian).collect(new ToListCollector<Dish>());
        log.info("{}",list);
    }
}
