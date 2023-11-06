package cn.xupengzhuang.chapter08;

import cn.xupengzhuang.chapter08.strategy.IsAllLowerCase;
import cn.xupengzhuang.chapter08.strategy.IsNumeric;
import cn.xupengzhuang.chapter08.strategy.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StrategyTests {

    @Test
    public void test1(){
        Validator validator1 = new Validator(new IsAllLowerCase());
        boolean ret1 = validator1.validate("Abc");
        log.info("Abc is all lower case={}",ret1);

        Validator validator2 = new Validator(new IsNumeric());;
        boolean ret2 = validator2.validate("123");
        log.info("123 is numeric={}",ret2);
    }

    @Test
    public void test2(){
        Validator validator1 = new Validator((String s) -> s.matches("\\d+"));
        boolean ret1 = validator1.validate("123");
        log.info("ret1={}",ret1);

        Validator validator2 = new Validator((String s) -> s.matches("[a-z]+"));
        boolean ret2 = validator2.validate("Abc");
        log.info("ret2={}",ret2);
    }

}
