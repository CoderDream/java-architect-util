package com.example.demo.p02.dp11.facade.bean.impl;

/**
 * 定义茶怀子系统类
 */
public class TeaCup {
    boolean teaBagIsSteeped;
    Water facadeWater;
    TeaBag facadeTeaBag;

    public TeaCup() {
        setTeaBagIsSteeped(false);
        System.out.println("茶杯准备好了");
    }

    public boolean getTeaBagIsSteeped() {
        return teaBagIsSteeped;
    }

    public void setTeaBagIsSteeped(boolean isTeaBagSteeped) {
        teaBagIsSteeped = isTeaBagSteeped;
    }

    public void addFacadeTeaBag(TeaBag facadeTeaBagIn) {
        facadeTeaBag = facadeTeaBagIn;
        System.out.println("茶包放在苶杯了");
    }

    public void addFacadeWater(Water facadeWaterIn) {
        facadeWater = facadeWaterIn;
        System.out.println("水倒入杯子了");
    }

    public void steepTeaBag() {
        if ((facadeTeaBag != null) &&
                ((facadeWater != null) && (facadeWater.getWaterIsBoiling()))) {
            System.out.println("茶渗透到杯子中");
            setTeaBagIsSteeped(true);
        } else {
            System.out.println("茶没有渗透到杯子中");
            setTeaBagIsSteeped(false);
        }
    }

    public String toString() {
        if (this.getTeaBagIsSteeped()) {
            return ("一杯又香又浓的茶冲好了!");
        } else {
            String tempString = "一个杯子";
            if (facadeWater != null) {
                if (facadeWater.getWaterIsBoiling()) {
                    tempString = (tempString + "放有沸腾的水 ");
                } else {
                    tempString = (tempString + "放有冻水 ");
                }
            } else {
                tempString = (tempString + "没有放水 ");
            }
            if (facadeTeaBag != null) {
                tempString = (tempString + "有一个茶包");
            } else {
                tempString = (tempString + "没有茶包");
            }
            return tempString;
        }
    }
}
