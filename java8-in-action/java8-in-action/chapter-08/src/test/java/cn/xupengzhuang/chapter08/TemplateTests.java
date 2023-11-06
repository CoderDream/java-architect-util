package cn.xupengzhuang.chapter08;

import cn.xupengzhuang.chapter08.template.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TemplateTests {
    @Test
    public void test1(){
        OnlineBanking abcOnline = new AbcOnline();
        abcOnline.processCustom(1l);

        OnlineBanking icbcOnlineSys = new IcbcOnlineSys();
        icbcOnlineSys.processCustom(2l);
    }

    @Test
    public void test2(){
        new OnlineBankingLambda().processCustom(1l, (Customer customer) -> log.info("给用户：" + customer.getName() + "发送商城代金券"));
        new OnlineBankingLambda().processCustom(2l, (Customer customer) -> log.info("给用户：" + customer.getName() + "加大广告投入"));
    }



}
