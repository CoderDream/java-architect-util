package com.example.demo.p02.dp13.proxy.client;
//SocketProxy代理应用测试:

import com.example.demo.p02.dp13.proxy.bean.SocketInterface;
import com.example.demo.p02.dp13.proxy.bean.impl.SocketProxy;

import java.io.*;

public class ProxyDemo {
    public static void main(String[] args) {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        //创建新线程启动ServerSocket
        Thread st = new Thread(new ServerThread());
        st.start();
        SocketInterface socket = new SocketProxy("127.0.0.1", 8189, false);
        String str = null;
        boolean skip = true;
        while (true) {
            if (skip)
                skip = !skip;
            else {
                str = socket.readLine();
                System.out.println("Receive - " + str);
                if (str.equals("quit"))
                    break;
            }
            System.out.print("Send ---- ");
            try {
                str = in.readLine();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
            socket.writeLine(str);
            if (str.equals("quit"))
                break;
        }
        socket.dispose();
    }
}
