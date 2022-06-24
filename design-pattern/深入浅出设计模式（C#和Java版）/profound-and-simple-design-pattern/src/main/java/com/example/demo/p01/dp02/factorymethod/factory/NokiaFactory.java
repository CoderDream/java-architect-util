package com.example.demo.p01.dp02.factorymethod.factory;

import com.example.demo.p01.dp02.factorymethod.bean.Mobile;
import com.example.demo.p01.dp02.factorymethod.bean.Nokia;

public class NokiaFactory implements MobileFactory {
    public Mobile produceMobile() {
        System.out.print("诺基亚工厂制造了");
        return new Nokia();
    }
}
