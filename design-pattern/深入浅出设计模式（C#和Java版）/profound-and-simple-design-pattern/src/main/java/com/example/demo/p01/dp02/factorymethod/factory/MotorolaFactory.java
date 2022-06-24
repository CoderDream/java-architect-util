package com.example.demo.p01.dp02.factorymethod.factory;

import com.example.demo.p01.dp02.factorymethod.bean.Mobile;
import com.example.demo.p01.dp02.factorymethod.bean.Motorola;

public class MotorolaFactory implements MobileFactory {
    public Mobile produceMobile() {
        System.out.print("摩托罗拉工厂制造了");
        return new Motorola();
    }
}
