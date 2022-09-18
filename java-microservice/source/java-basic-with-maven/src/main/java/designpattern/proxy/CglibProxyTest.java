package designpattern.proxy;

/**
* @description 代理模式：测试样例
* @author xindaqi
* @since 2021-02-15 15:09:53
*/
public class CglibProxyTest {

    public static void main(String[] args) {
        float a = 1.0f;
        float b = 2.0f;
        ArithmeticOperation proxyObject = new ArithmeticOperation();
        CglibProxy cglibProxy = new CglibProxy(proxyObject, a, b);
        ArithmeticOperation arithmeticOperation = (ArithmeticOperation) cglibProxy.getProxyInstance();
        arithmeticOperation.addition(a, b);
    }
    
}