package com.example.demo.p03.dp18.mediator.bean;

public class Consumer extends Thread {
    private static int num = 1;
    private final Mediator med; //消费者(信息接收者)也仅仅连接中介者
    private final int id;

    public Consumer(Mediator m) {
        med = m;
        id = num++;
    }

    public void run() {
        while (true) {
            //显示信息生产者的ID及其信息
            System.out.print("c" + id + "-" + med.retrieveMessage() + "   ");
        }
    }
}
