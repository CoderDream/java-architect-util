package com.coderdream.freeapps.util.designpattern.dp01.singleton07;

/**
 * 枚举实现单例
 *
 * @className: Singleton
 * @date: 2021/6/7 14:32
 */
public enum Singleton {
    INSTANCE;
    public void doSomething(String str) {
        System.out.println(str);
    }
}
