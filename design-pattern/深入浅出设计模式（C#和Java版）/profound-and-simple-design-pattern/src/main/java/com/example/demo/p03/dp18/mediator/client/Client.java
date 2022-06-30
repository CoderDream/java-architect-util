package com.example.demo.p03.dp18.mediator.client;

import com.example.demo.p03.dp18.mediator.bean.Consumer;
import com.example.demo.p03.dp18.mediator.bean.Mediator;
import com.example.demo.p03.dp18.mediator.bean.Producer;

/**
 * 迭代器的应用测测试
 */
public class Client {
    public static void main (String[] args) {
        Mediator mb = new Mediator ();
        new Producer(mb).start ();
        new Producer (mb).start ();
        new Consumer(mb).start ();
        new Consumer (mb).start ();
        new Consumer (mb).start ();
        new Consumer (mb).start ();
    }
}
