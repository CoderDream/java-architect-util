package com.example.demo.p02.dp11.facade.bean.impl;

/**
 * facadeCuppaMaker耦合各系统来冲茶
 */
public class FacadeCuppaMaker {
    boolean teaBagIsSteeped;

    public FacadeCuppaMaker() {
        System.out.println("FacadeCuppaMaker准备好为你冲茶了!");
    }

    public TeaCup makeACuppa() {
        TeaCup cup = new TeaCup();
        TeaBag teaBag = new TeaBag();
        Water water = new Water();
        cup.addFacadeTeaBag(teaBag);
        water.boilFacadeWater();
        cup.addFacadeWater(water);
        cup.steepTeaBag();
        return cup;
    }
}
