package com.feng.bean;

/**
 * @ClassName BakeEventList
 * @Description TODO
 * @Author admin
 * @Date 2021/4/8 11:43
 * @Version 1.0
 */
public class BakeEventList {
    private String bakeTime;

    private Double beanTemperature;

    public BakeEventList(String bakeTime, Double beanTemperature) {
        this.bakeTime = bakeTime;
        this.beanTemperature = beanTemperature;
    }

    public String getBakeTime() {
        return bakeTime;
    }

    public void setBakeTime(String bakeTime) {
        this.bakeTime = bakeTime;
    }

    public Double getBeanTemperature() {
        return beanTemperature;
    }

    public void setBeanTemperature(Double beanTemperature) {
        this.beanTemperature = beanTemperature;
    }
}
