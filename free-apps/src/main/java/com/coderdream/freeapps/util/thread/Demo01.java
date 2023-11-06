package com.coderdream.freeapps.util.thread;

public class Demo01 {

    public static void main(String[] args) {
        while (true) {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
