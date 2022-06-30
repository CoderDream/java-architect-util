package com.example.demo.p03.dp14.chain.bean.impl;

import com.example.demo.p03.dp14.chain.bean.SecurityZone;
import com.example.demo.p03.dp14.chain.bean.Sensor;

/**
 * 具体区域，继承SecurityZone类
 */
public class Area extends SecurityZone {

    /**
     * 调用父类的构造函数
     *
     * @param string
     */
    public Area(String string) {
        super(string);
    }

    /**
     * 处理传感器的测量值
     *
     * @param measurement
     * @param sensor
     * @return
     */
    @Override
    public boolean handleNotification(int measurement, Sensor sensor) {
        if (sensor instanceof TemperatureSensor) {
            if (measurement > 150) {
                fireAlarm(this, sensor);
                return true;
            }
        }
        return false;
    }
}
