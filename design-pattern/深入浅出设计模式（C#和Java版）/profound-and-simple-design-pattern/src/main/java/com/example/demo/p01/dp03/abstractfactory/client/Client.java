package com.example.demo.p01.dp03.abstractfactory.client;

import com.example.demo.p01.dp03.abstractfactory.factory.Dell;
import com.example.demo.p01.dp03.abstractfactory.factory.IBM;

public class Client {
    public static void main(String[] argv) {
        IBM ibm = new IBM();
        ibm.show();
        Dell dell = new Dell();
        dell.show();
    }
}
