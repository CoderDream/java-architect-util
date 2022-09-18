package designpattern.template;

import java.util.logging.Logger;
/**
* @description 模板模式：标准洗涤
* @author xindaqi
* @since 2021-02-12 15:09:42
*/
public class StandardsWashing extends BaseWashingMachine {

    private static final Logger logger = Logger.getLogger("StandardsWashing");

    @Override
    void powerOn() {
        logger.info("洗衣机：开机");
    }

    @Override
    void work() {
        logger.info("洗衣机：标准洗涤");
    }

    @Override
    void powerOff() {
        logger.info("洗衣机：关机");
    }
    
}