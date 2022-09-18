package designpattern.strategy;

import java.math.BigDecimal;
import common.FloatConstant;

/**
* @description 策略模式：90%折扣
* @author xindaqi
* @since 2021-02-11 23:40:58
*/
public class NinetyPercentOff implements IDiscount {

    @Override
    public float discount(float originalPrice) {
        BigDecimal price = BigDecimal.valueOf(originalPrice);
        BigDecimal off = BigDecimal.valueOf(FloatConstant.NINETY_PERCENT_OFF);
        return price.multiply(off).floatValue();
    }
    
}