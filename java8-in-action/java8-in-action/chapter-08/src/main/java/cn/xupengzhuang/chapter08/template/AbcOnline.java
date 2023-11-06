package cn.xupengzhuang.chapter08.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbcOnline extends OnlineBanking{
    @Override
    public void makeCustomHappy(Customer customer) {
        log.info("给用户："+customer.getName()+"赠送商城代金券");
    }
}
