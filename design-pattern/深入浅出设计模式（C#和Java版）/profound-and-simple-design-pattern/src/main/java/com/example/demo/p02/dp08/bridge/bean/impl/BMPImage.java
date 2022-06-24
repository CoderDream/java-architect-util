package com.example.demo.p02.dp08.bridge.bean.impl;

import com.example.demo.p02.dp08.bridge.bean.Image;

/**
 * 派生的BMPImage，是Image的子类，重写method方法，调用内部的ImageInterface引用来实现。
 */
public class BMPImage extends Image {
    @Override
    public void method(String str) {
        String s1 = str + "\nBMP Image";
        this.imageInterface.doPaint(s1);
    }
}
