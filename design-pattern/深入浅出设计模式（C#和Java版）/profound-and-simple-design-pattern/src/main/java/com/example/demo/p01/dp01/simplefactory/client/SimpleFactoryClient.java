package com.example.demo.p01.dp01.simplefactory.client;

import com.example.demo.p01.dp01.simplefactory.bean.Mobile;
import com.example.demo.p01.dp01.simplefactory.factory.MobileFactory;

public class SimpleFactoryClient {
    public static void main(String[] argv) {
        // 手机工厂
        MobileFactory mobileFactory = new MobileFactory();
        // 手机
        Mobile mobile;
        try {
            // 生产的手机为诺基亚手机
            mobile = mobileFactory.getMobile("nokia");
            // 调用诺基亚手机的方法
            mobile.call();
            // 生产的手机为摩托罗拉手机
            mobile = mobileFactory.getMobile("motorola");
            // 调用摩托罗拉手机的方法
            mobile.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
