package com.example.demo.p03.dp14.chain.bean;

/**
 * 定义socket接口
 */
public interface SocketInterface {
    String readLine();

    void writeLine(String str);

    void dispose();
}
