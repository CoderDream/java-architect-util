package cn.xupengzhuang.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MapUtilTests {

    @Test
    public void test1(){
        List<String> strings = Arrays.asList("Java", "C#", "Golang");
        List<Integer> map = MapUtil.map(strings, (String s) -> s.length());
        log.info("{}",map);
    }
}
