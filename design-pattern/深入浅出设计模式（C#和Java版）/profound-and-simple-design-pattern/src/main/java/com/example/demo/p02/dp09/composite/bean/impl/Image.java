package com.example.demo.p02.dp09.composite.bean.impl;

import com.example.demo.p02.dp09.composite.bean.DocumentElement;

/**
 * 定义Image类，实现DocumentElement接口
 */
public class Image implements DocumentElement {
    int x, y;

    public Image(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        System.out.println("Draw an image at " + x + "," + y);
    }
}
