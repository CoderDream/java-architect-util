package com.debug.middleware.server.distributeLock;/**
 * Created by Administrator on 2019/4/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 锁机制
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/14 17:16
 **/
public class LockOne{
    private static final Logger log= LoggerFactory.getLogger(LockOne.class);

    public static void main(String args[]){
        Thread tAdd=new Thread(new LockThread(100));
        Thread tSub = new Thread(new LockThread(-100));
        tAdd.start();
        tSub.start();
    }
}
//模拟锁机制的线程类
class LockThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(LockThread.class);

    //定义成员变量-用于接收线程初始化时提供的金额-代表取/存的金额
    private int count;

    //构造方法
    public LockThread(int count) {
        this.count = count;
    }

    /**
     * 线程操作共享资源的方法体-不加同步锁
     */
//    @Override
//    public void run() {
//        try {
//            //执行10次访问共享的操作
//            for (int i = 0; i < 10; i++) {
//                //通过传进来的金额(可正、可负)执行叠加操作
//                SysConstant.amount = SysConstant.amount + count;
//                //打印每次操作完账户的余额
//                log.info("此时账户余额为：{}", SysConstant.amount);
//            }
//        } catch (Exception e) {
//            //有异常情况时直接进行打印
//            e.printStackTrace();
//        }
//    }

    /**
     * 线程操作共享资源的方法体-加同步锁
     */
    @Override
    public void run() {
        //执行10次访问共享的操作
        for (int i = 0; i < 100; i++) {
            //加入 synchronized 关键字,控制并发线程对共享资源的访问
            synchronized (SysConstant.amount) {
                //通过传进来的金额(可正、可负)进行叠加
                SysConstant.amount = SysConstant.amount + count;
                //打印每次操作完账户的余额
                log.info("此时账户余额为：{}", SysConstant.amount);
            }
        }
    }
}


































