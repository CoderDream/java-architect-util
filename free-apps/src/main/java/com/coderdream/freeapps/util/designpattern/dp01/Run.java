package com.coderdream.freeapps.util.designpattern.dp01;

import com.coderdream.freeapps.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author 徐庶 QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 * <p>
 * 单例Bean的情况 如果在类中声明成员变量 并且有读写操作（有状态），就是线程不安全 解决： 1.设置为多例 2.将成员变量放在ThreadLocal 3.同步锁 会影响服务器吞吐量 但是! 只需要把成员变量声明在方法中（无状态），
 * 单例Bean是线程安全的
 */
public class Run {

//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
//            MainConfig.class);
//
//        // 线程一
//        UserService bean = applicationContext.getBean(UserService.class);
//        new Thread(() ‐> {
//            System.out.println(bean.welcome("张三"));
// }).start();
//
//        // 线程二
//        UserService bean2 = applicationContext.getBean(UserService.class);
//        new Thread(() ‐> {
//            System.out.println(bean2.welcome("李四"));
// }).start();
//    }
}
