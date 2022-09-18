package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
* @description 代理模式：动态代理实现InvokerHandler
* @author xindaqi
* @since 2021-02-15 13:57:09
*/

public class MyInvocationHandler implements InvocationHandler {

    private static final Logger logger = Logger.getLogger("MyInvocationHandler");

    Object proxyObject;
    Object a;
    Object b;

    public MyInvocationHandler(Object proxyObject, Object a, Object b) {
        this.proxyObject = proxyObject;
        this.a = a;
        this.b = b;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        logger.info("进入原生前，我是动态代理");
        Object obj = method.invoke(proxyObject, a, b);
        logger.info("进入原生后，我是动态代理");
        return obj;
    }
    
}