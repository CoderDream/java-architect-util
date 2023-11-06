package com.coderdream.freeapps.util.thread;

public class Demo02 {

    public static void main(String[] args) {
        Thread thread = null;

    }
}

class Demo02Thread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("运行了run方法");

    }
}
