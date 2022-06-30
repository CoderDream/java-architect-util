package com.example.demo.p03.dp18.mediator.bean;

public class Producer extends Thread {
  private Mediator med; //生产者仅仅连接中介者
  private int id;
  private static int num = 1; //静态全局变量
  public Producer (Mediator m) {
    med = m;
    id = num++;
  }
  public void run () {
    int num;
    while (true) {
      med.storeMessage (num = (int) (Math.random () * 100));
      //显示信息生产者的ID及其信息
      System.out.print ("p" + id + "-" + num + "   ");
    }
  }
}
