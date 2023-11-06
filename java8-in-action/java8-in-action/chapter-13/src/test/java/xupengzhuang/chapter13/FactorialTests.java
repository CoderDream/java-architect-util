package xupengzhuang.chapter13;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FactorialTests {

    @Test
    public void test1(){
        int i = FactorialUtil.factorialIterative(10);
        log.info("{}",i);
    }

    @Test
    public void test2(){
        int i = FactorialUtil.factorialRecursive(10);
        log.info("{}",i);
    }

    @Test
    public void test3(){
        int i = FactorialUtil.factorialStream(10);
        log.info("{}",i);
    }

    @Test
    public void test4(){
        int i = FactorialUtil.factorialTailRecursive(10);
        log.info("{}",i);
    }
}
