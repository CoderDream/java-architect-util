package cn.xupengzhuang.chapter08;

import cn.xupengzhuang.chapter08.factory.Product;
import cn.xupengzhuang.chapter08.factory.ProductFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FactoryTests {

    @Test
    public void test1(){
        Product loan = ProductFactory.createProduct("loan");
        log.info(loan.getName());
    }

    /**
     * 使用lambda表达式重构上面的工厂方法设计模式
     */
    @Test
    public void test2(){
        Product loan = ProductFactory.createProductByLambda("loan");
        log.info(loan.getName());
    }

}
