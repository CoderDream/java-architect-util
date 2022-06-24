package com.example.demo.p02.dp11.facade.client;

import com.example.demo.p02.dp11.facade.bean.impl.FacadeCuppaMaker;
import com.example.demo.p02.dp11.facade.bean.impl.TeaCup;

/**
 * 顾客需要冲茶，只要调用FacadeCuppaMaker的一个方法就可以完成了
 */
public class Client {
    public static void main(String[] args) {
        FacadeCuppaMaker cuppaMaker = new FacadeCuppaMaker();
        TeaCup teaCup = cuppaMaker.makeACuppa();
        System.out.println(teaCup);
    }
}
