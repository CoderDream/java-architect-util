package com.coderdream.freeapps.util.designpattern.dp01.singleton05;

/**
 * 双重检查锁（DCL， 即 double-checked locking）
 *
 * @className: Singleton
 * @date: 2021/6/7 14:32
 */
public class Singleton {

    // 1、私有化构造⽅法
    private Singleton() {
    }

    // 2、定义⼀个静态变量指向⾃⼰类型
    private volatile static Singleton instance;

    // 3、对外提供⼀个公共的⽅法获取实例
    public static Singleton getInstance() {
    // 第⼀重检查是否为 null
        if (instance == null) {
            // 使⽤ synchronized 加锁
            synchronized (Singleton.class) {
                // 第⼆重检查是否为 null
                if (instance == null) {
                    // new 关键字创建对象不是原⼦操作
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
