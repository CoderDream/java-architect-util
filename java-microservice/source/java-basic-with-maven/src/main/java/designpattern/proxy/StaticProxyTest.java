package designpattern.proxy;

/**
* @description 代理模式：静态代理测试样例
* @author xindaqi
* @since 2021-02-15 12:53:48
*/

public class StaticProxyTest {

    public static void main(String[] args) {
        IArithmeticOperation arithmeticOperation = new ArithmeticOperation();
        StaticProxy staticProxy = new StaticProxy(arithmeticOperation);
        float a = 1.0f;
        float b = 2.0f;
        staticProxy.addition(a, b);
    }
    
}