package com.example.demo.p03.dp14.chain.bean.impl;

import com.example.demo.p03.dp14.chain.bean.SecurityZone;
import com.example.demo.p03.dp14.chain.bean.Sensor;

/**
 * 大楼类，采取自已的行动，发生火警
 */
public class Building extends SecurityZone {
    public Building(String name) {
        super(name);
    }

    public boolean handleNotification(int measurement, Sensor sensor) {
        return false;
    }

    @Override
    public void fireAlarm(SecurityZone zone, Sensor sensor) {
        if (zone instanceof Area) {
            // Turn on sprinklers in surrounding areas
            // Don't call super.fireAlarm because that will turn on the
            // sprinkler for the whole warehouse.
            System.out.println(this.name + "栋大楼发生火警");
            if (getParent() != null)
                getParent().fireAlarm(zone, sensor);
            return;
        } // if
        super.fireAlarm(zone, sensor);
    }

}
