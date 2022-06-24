package com.example.demo.p02.dp13.proxy.bean.impl;

import com.example.demo.p02.dp13.proxy.bean.SocketInterface;

import java.io.*;
import java.net.*;

/**
 * SocketProxy代理类的详情实现
 */
public class SocketProxy implements SocketInterface {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketProxy(String host, int port, boolean wait) {
        try {
            if (wait) {
                ServerSocket server = new ServerSocket(port);
                socket = server.accept();
            } else
                socket = new Socket(host, port); // in the wrapper
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        String str = "";
        try {
            str = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void writeLine(String str) {
        out.println(str); //The wrapper delegates to the target
    }

    public void dispose() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
