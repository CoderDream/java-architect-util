package designpattern.proxy;

import java.util.logging.Logger;

/**
* @description 代理模式：静态代理
* @author xindaqi
* @since 2021-02-15 12:46:21
*/

public class StaticProxy implements IArithmeticOperation {

    private static final Logger logger = Logger.getLogger("StaticProxy");

    IArithmeticOperation arithmeticOperation;

    public StaticProxy(IArithmeticOperation arithmeticOperation) {
        this.arithmeticOperation = arithmeticOperation;
    }

    @Override
    public float addition(float a, float b) {
        logger.info("进入原生前，我是静态代理接口实现：加法");
        arithmeticOperation.addition(a, b);
        float result = a + b;
        logger.info("进入原生后，我是静态代理接口实现：加法，计算结果：" + result);
        return result;

    }

    @Override
    public float substraction(float a, float b) {
        logger.info("进入原生前，我是静态代理接口实现：减法");
        arithmeticOperation.addition(a, b);
        float result = a - b;
        logger.info("进入原生后，我是静态代理接口实现：减法，计算结果：" + result);
        return result;
    }
    
}