package designpattern.template;

import java.util.logging.Logger;
/**
* @description 模板模式：脱水
* @author xindaqi
* @since 2021-02-12 15:14:29
*/
public class Dehydration extends BaseWashingMachine {

    private static final Logger logger = Logger.getLogger("Dehydration");

    @Override
    void powerOn() {
        logger.info("洗衣机：开机");
    }

    @Override
    void work() {
        logger.info("洗衣机：脱水");
    }

    @Override
    void powerOff() {
        logger.info("洗衣机：关机");
    }
    
}