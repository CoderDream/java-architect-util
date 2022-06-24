package com.example.demo.p01.dp05.prototype.client;

import com.example.demo.p01.dp05.prototype.bean.impl.Circle;
import com.example.demo.p01.dp05.prototype.bean.impl.Rectangle;
import com.example.demo.p01.dp05.prototype.factory.Toolbar;

public class Client {
    public static void main(String[] argv) {
        Toolbar toolbar = new Toolbar();
        String key = "circle";
        Circle circle = (Circle) toolbar.getClone(key);
        circle.draw();
        key = "rectangle";
        Rectangle rectangle = (Rectangle) toolbar.getClone(key);
        rectangle.draw();
    }
}
