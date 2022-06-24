package com.example.demo.p02.dp13.proxy.bean;

/**
 * 定义socket接口
 */
public interface SocketInterface {
    String readLine();

    void writeLine(String str);

    void dispose();
}
