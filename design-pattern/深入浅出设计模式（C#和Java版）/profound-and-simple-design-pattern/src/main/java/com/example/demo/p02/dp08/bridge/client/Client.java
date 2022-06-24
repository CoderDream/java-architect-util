package com.example.demo.p02.dp08.bridge.client;

import com.example.demo.p02.dp08.bridge.bean.ImageInterface;
import com.example.demo.p02.dp08.bridge.bean.impl.BMPImage;
import com.example.demo.p02.dp08.bridge.bean.impl.UnixImage;
import com.example.demo.p02.dp08.bridge.bean.impl.WinImage;

/**
 * 客户测试程序
 */
public class Client {
    public static void main(String[] args) {
        BMPImage bmp = new BMPImage();
        ImageInterface wimp = new WinImage();
        bmp.setImageInterface(wimp);
        bmp.method("Paint-->");
        ImageInterface unixImp = new UnixImage();
        bmp.setImageInterface(unixImp);
        bmp.method("Paint-->");
    }
}
