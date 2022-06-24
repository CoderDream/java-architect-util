package com.example.demo.p02.dp09.composite.bean.impl;

import com.example.demo.p02.dp09.composite.bean.DocumentElement;

/**
 * 定义Character类,实现DocumentElement的接口
 */
public class CustomCharacter implements DocumentElement {
    int x, y;

    public CustomCharacter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        System.out.println("Draw a character at " + x + "," + y);
    }
}
