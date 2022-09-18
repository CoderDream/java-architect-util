package designpattern.strategy;

/**
* @description 策略模式：策略选择器
* @author xindaqi
* @since 2021-02-12 01:00:56
*/
public class StrategyChoice {

    IDiscount iDiscount;

    public StrategyChoice(IDiscount iDiscount) {
        this.iDiscount = iDiscount;
    }

    public float doStrategy(float originalPrice) {
        return iDiscount.discount(originalPrice);
    }
    
}