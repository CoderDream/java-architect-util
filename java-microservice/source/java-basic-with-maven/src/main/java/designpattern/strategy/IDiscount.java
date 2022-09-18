package designpattern.strategy;

import java.math.BigDecimal;

/**
* @description 策略模式：打折
* @author xindaqi
* @since 2021-02-11 22:17:35
*/
public interface IDiscount {

    /**
    * description: 打折
    * @since 2021-02-11 23:29:10
    * @param originalPrice 原价
    * @return 折后价
    */
    float discount(float originalPrice);
    
}