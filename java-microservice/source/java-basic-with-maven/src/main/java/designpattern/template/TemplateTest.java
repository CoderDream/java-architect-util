package designpattern.template;

import java.util.logging.Logger;

/**
* @description 模板模式：测试样例
* @author xindaqi
* @since 2021-02-12 15:17:59
*/
public class TemplateTest {

    private static final Logger logger = Logger.getLogger("TemplateTest");

    public static void main(String[] args) {
        BaseWashingMachine washingMachine = new StandardsWashing();
        washingMachine.operation();
        logger.info("==============");
        washingMachine = new Dehydration();
        washingMachine.operation();
    }
    
}