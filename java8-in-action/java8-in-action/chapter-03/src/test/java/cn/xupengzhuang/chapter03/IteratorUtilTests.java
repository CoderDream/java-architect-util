package cn.xupengzhuang.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class IteratorUtilTests {
    @Test
    public void test1(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        IteratorUtil.forEach(integers,i -> System.out.println(i));
    }
}
