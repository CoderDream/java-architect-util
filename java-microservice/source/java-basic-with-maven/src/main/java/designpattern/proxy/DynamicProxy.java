package designpattern.proxy;

import java.util.logging.Logger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;

/**
* @description 代理模式：动态代理
* @author xindaqi
* @since 2021-02-15 13:34:21
*/

public class DynamicProxy {

    private static final Logger logger = Logger.getLogger("DynamicProxy");

    IArithmeticOperation arithmeticOperation;
    Object a;
    Object b;

    public DynamicProxy(IArithmeticOperation arithmeticOperation, Object a, Object b) {
        this.arithmeticOperation = arithmeticOperation;
        this.a = a;
        this.b = b;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(arithmeticOperation.getClass().getClassLoader(), arithmeticOperation.getClass().getInterfaces(), new MyInvocationHandler(arithmeticOperation, a, b));
    }


    
}