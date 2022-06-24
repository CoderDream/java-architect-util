package com.example.demo.p01.dp06.singleton.client;

import com.example.demo.p01.dp06.singleton.bean.Logger;

public class Client {
    public static void main(String[] argv) {
        //初始化Logger
        Logger.initialize();
        //取得实例
        Logger logger = Logger.getLogger();
        logger.logMsg("client log message");
    }
}
