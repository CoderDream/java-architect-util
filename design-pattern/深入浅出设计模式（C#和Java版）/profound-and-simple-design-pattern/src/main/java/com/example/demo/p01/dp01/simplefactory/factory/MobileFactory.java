package com.example.demo.p01.dp01.simplefactory.factory;

import com.example.demo.p01.dp01.simplefactory.bean.Mobile;
import com.example.demo.p01.dp01.simplefactory.bean.Motorola;
import com.example.demo.p01.dp01.simplefactory.bean.Nokia;


public class MobileFactory {
    public Mobile getMobile(String title) throws Exception {
        if (title.equalsIgnoreCase("nokia")) {
            return new Nokia();
        } else if (title.equalsIgnoreCase("motorola")) {
            return new Motorola();
        } else {
            throw new Exception("no such " + title + " mobile found");
        }
    }
}
