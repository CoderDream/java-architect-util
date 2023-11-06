package cn.xupengzhuang.chapter08.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IcbcOnlineSys extends OnlineBanking{
    @Override
    public void makeCustomHappy(Customer customer) {
        log.info("对用户："+customer.getName()+"加大广告投放量");
    }
}
