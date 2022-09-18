package designpattern.strategy;

import java.math.BigDecimal;

import common.FloatConstant;

/**
* @description 策略模式：50%折扣
* @author xindaqi
* @since 2021-02-12 00:56:43
*/
public class FiftyPercentOff implements IDiscount {

    @Override 
    public float discount(float originalPrice) {
        BigDecimal price = BigDecimal.valueOf(originalPrice);
        BigDecimal off = BigDecimal.valueOf(FloatConstant.FIFTY_PERCENT_OFF);
        return price.multiply(off).floatValue();
    }
    
}