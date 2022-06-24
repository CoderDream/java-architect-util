package com.example.demo.p02.dp13.proxy.client;

import com.example.demo.p02.dp13.proxy.bean.SocketInterface;
import com.example.demo.p02.dp13.proxy.bean.impl.SocketProxy;

/**
 * 定义Socket应答服务器的线程
 */
public class ServerThread extends Thread {
    String str;
    private SocketInterface server_socket;

    public void run() {
        server_socket = new SocketProxy("127.0.0.1", 8189, true);
        System.out.println("thread begin");
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e);
            }
            str = server_socket.readLine();
            if (str.length() > 0) {
                if (str.equals("quit"))//收到quit就结束循环
                    break;
                str = "(server echo)" + str;
                server_socket.writeLine(str);
                if (str.equals("quit"))
                    str = "";
            }
        }
        server_socket.dispose();
        System.out.println("thread end");
    }
}
