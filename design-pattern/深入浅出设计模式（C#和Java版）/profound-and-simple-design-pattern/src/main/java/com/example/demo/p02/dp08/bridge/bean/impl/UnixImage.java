package com.example.demo.p02.dp08.bridge.bean.impl;

import com.example.demo.p02.dp08.bridge.bean.ImageInterface;

/**
 * 定义Unix下的图象实现类，实现ImageImp接口。
 */
public class UnixImage implements ImageInterface {
    public void doPaint(String str) {
        System.out.println(str + " at UnixOS");
    }
}
