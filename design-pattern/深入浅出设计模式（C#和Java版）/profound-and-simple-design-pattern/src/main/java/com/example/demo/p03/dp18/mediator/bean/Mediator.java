package com.example.demo.p03.dp18.mediator.bean;

public class Mediator {
    private int number;//存储槽
    private boolean slotFull = false;

    //同步化，避免两个线程同时执行
    public synchronized void storeMessage(int num) {
        while (slotFull == true)//存储槽没有空间了
            try {
                wait();
            } catch (InterruptedException e) {
            }
        slotFull = true;
        number = num;
        notifyAll();
    }

    public synchronized int retrieveMessage() {
        while (slotFull == false) //存储槽空信息
            try {
                wait();
            } catch (InterruptedException e) {
            }
        slotFull = false;
        notifyAll();
        return number;
    }
}
