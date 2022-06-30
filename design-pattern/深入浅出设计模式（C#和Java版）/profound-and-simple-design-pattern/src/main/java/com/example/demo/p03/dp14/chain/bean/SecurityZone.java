package com.example.demo.p03.dp14.chain.bean;

/**
 * 定义安全区域抽象类
 */
public abstract class SecurityZone {
    public String name; //区域名
    private SecurityZone parent;//父区域

    public SecurityZone(String name) {
        this.name = name;
    }

    /**
     * 返回它的父区域
     * @return
     */
    public SecurityZone getParent() {
        return parent;
    }

    /**
     * 设定它的父区域
     * @param zone
     */
    public void setParent(SecurityZone zone) {
        parent = zone;
    }

    /**
     * 调用这个方法去通知区域对象其中的传感器的测量值
     *
     * @param measurement
     * @param sensor
     */
    public void notify(int measurement, Sensor sensor) {
        if (!handleNotification(measurement, sensor) && parent != null) {
            parent.notify(measurement, sensor);
        }
    }

    /**
     * 上面的方法notify调用这个方法让对象可以处理测量值
     *
     * @param measurement
     * @param sensor
     * @return
     */
    public abstract boolean handleNotification(int measurement, Sensor sensor);

    /**
     * 本方法被子区域调用来报告火警，期待子区域中打开喷撒器，重载此方法可以让父区域也采取必要的行动
     */
    public void fireAlarm(SecurityZone zone, Sensor sensor) {
        // Turn on sprinklers
        System.out.println(this.name + sensor.position + "水喷撒器打开了");
        if (parent != null)
            parent.fireAlarm(zone, sensor);
    }
}
