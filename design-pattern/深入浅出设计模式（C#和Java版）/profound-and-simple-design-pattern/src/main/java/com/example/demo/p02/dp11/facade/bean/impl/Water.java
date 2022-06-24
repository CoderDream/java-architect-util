package com.example.demo.p02.dp11.facade.bean.impl;

/**
 * 定义子系统类Water
 */
public class Water {
    boolean waterIsBoiling;

    public Water() {
        setWaterIsBoiling(false);
        System.out.println("纯净的水准备好了");
    }

    public void boilFacadeWater() {
        setWaterIsBoiling(true);
        System.out.println("水在沸腾");
    }

    public boolean getWaterIsBoiling() {
        return waterIsBoiling;
    }

    public void setWaterIsBoiling(boolean isWaterBoiling) {
        waterIsBoiling = isWaterBoiling;
    }
}
