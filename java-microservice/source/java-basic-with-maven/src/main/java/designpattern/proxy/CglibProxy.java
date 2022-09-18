package designpattern.proxy;

import java.util.logging.Logger;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
* @description 代理模式：CGLib代理
* @author xindaqi
* @since 2021-02-15 14:44:55
*/
public class CglibProxy implements MethodInterceptor {

    private static final Logger logger = Logger.getLogger("CglibProxy");

    Object proxyObject;
    Object a;
    Object b;

    public CglibProxy(Object proxyObject, Object a, Object b) {
        this.proxyObject = proxyObject;
        this.a = a;
        this.b = b;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxyObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        logger.info("进入原生前，我是CGLib代理");
        Object obj = method.invoke(proxyObject, a, b);
        logger.info("进入原生后，我是CGLib代理");
        return obj;
    }
    
}