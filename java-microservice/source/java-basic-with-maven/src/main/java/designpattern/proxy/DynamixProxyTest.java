package designpattern.proxy;

import java.util.logging.Logger;

/**
* @description 动态代理：测试样例
* @author xindaqi
* @since 2021-02-15 14:11:27
*/
public class DynamixProxyTest {

    private static final Logger logger = Logger.getLogger("DynamixProxyTest");

    public static void main(String[] args) {
        IArithmeticOperation arithmeticOperation = new ArithmeticOperation();
        float a = 1.0f;
        float b = 2.0f;
        IArithmeticOperation dynamicProxy = (IArithmeticOperation) new DynamicProxy(arithmeticOperation, a, b).getProxy();
        dynamicProxy.addition(a, b);
    }
    
}