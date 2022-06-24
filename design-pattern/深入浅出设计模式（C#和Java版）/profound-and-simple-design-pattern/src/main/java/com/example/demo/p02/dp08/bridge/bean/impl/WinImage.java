package com.example.demo.p02.dp08.bridge.bean.impl;

import com.example.demo.p02.dp08.bridge.bean.ImageInterface;

/**
 * 定义在Window下的图象实现类，实现ImageImp接口
 */
public class WinImage implements ImageInterface {
    public void doPaint(String str) {
        System.out.println(str + " at WinOS");
    }
}
