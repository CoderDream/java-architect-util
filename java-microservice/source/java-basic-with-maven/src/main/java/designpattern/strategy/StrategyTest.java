package designpattern.strategy;

import java.util.logging.Logger;

/**
* @description 策略模式：测试样例
* @author xindaqi
* @since 2021-02-12 01:06:41
*/
public class StrategyTest {

    private static final Logger logger = Logger.getLogger("StrategyTest");

    public static void main(String[] args) {
        IDiscount fiftyDiscount = new FiftyPercentOff();
        StrategyChoice strategyChoiceFifty = new StrategyChoice(fiftyDiscount);
        float originalPrice = 100f;
        logger.info("原始价格：" + originalPrice + "，折后价：" + strategyChoiceFifty.doStrategy(originalPrice));

        IDiscount ninetyDiscount = new NinetyPercentOff();
        StrategyChoice strategyChoiceNinety = new StrategyChoice(ninetyDiscount);
        logger.info("原始价格：" + originalPrice + "，折后价：" + strategyChoiceNinety.doStrategy(originalPrice));

    }
    
}