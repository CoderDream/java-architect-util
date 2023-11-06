package xupengzhuang.chapter13;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ListUtilTests {
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1, 4, 9);
        List<List<Integer>> lists = ListUtil.subSets(list);
        log.info("{}",lists);
    }
}
