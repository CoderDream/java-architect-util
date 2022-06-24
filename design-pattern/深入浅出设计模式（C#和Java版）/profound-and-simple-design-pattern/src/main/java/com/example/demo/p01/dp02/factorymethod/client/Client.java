package com.example.demo.p01.dp02.factorymethod.client;

import com.example.demo.p01.dp02.factorymethod.bean.Mobile;
import com.example.demo.p01.dp02.factorymethod.factory.MobileFactory;
import com.example.demo.p01.dp02.factorymethod.factory.MotorolaFactory;
import com.example.demo.p01.dp02.factorymethod.factory.NokiaFactory;

public class Client {
    public static void main(String[] argv) {
        // 手机工厂
        MobileFactory mobileFactory;
        // 手机
        Mobile mobile;
        // 工厂为生产摩托罗拉手机的工厂
        mobileFactory = new MotorolaFactory();
        // 生产的手机为摩托罗拉手机
        mobile = mobileFactory.produceMobile();
        // 调用摩托罗拉手机的方法
        mobile.call();
        // 工厂为生产诺基亚手机的工厂
        mobileFactory = new NokiaFactory();
        // 生产的手机为诺基亚手机
        mobile = mobileFactory.produceMobile();
        // 调用诺基亚手机的方法
        mobile.call();
    }
}
