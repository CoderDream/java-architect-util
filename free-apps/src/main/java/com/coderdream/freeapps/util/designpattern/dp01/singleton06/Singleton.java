package com.coderdream.freeapps.util.designpattern.dp01.singleton06;

/**
 * 静态内部类实现单例
 *
 * @className: Singleton
 * @date: 2021/6/7 14:32
 */
public class Singleton {

    // 1、私有化构造⽅法
    private Singleton() {
    }

    // 2、对外提供获取实例的公共⽅法
    public static Singleton getInstance() {
        return InnerClass.INSTANCE;
    }

    // 定义静态内部类
    private static class InnerClass {

        private final static Singleton INSTANCE = new
            Singleton();
    }
}
