package designpattern.proxy;

import java.util.logging.Logger;

/**
* @description 代理模式：算术运算实现
* @author xindaqi
* @since 2021-02-15 12:29:12
*/
public class ArithmeticOperation implements IArithmeticOperation {

    private static final Logger logger = Logger.getLogger("ArithmeticOperation");

    @Override
    public float addition(float a, float b) {
        float result = a + b;
        logger.info("我是原生接口实现：加法，计算结果：" + result);
        return result;
    }

    @Override
    public float substraction(float a, float b) {
        float result = a - b;
        logger.info("我是原生接口实现：减法，计算结果：" + result);
        return result;
    }
    
}