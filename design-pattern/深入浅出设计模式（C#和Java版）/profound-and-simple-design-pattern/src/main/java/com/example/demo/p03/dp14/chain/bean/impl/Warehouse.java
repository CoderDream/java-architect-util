package com.example.demo.p03.dp14.chain.bean.impl;

import com.example.demo.p03.dp14.chain.bean.SecurityZone;
import com.example.demo.p03.dp14.chain.bean.Sensor;
import com.example.demo.p03.dp14.chain.bean.impl.Area;

//仓库类,有火时，呼叫保安马上检查发火区域:
public class Warehouse extends SecurityZone {
    public Warehouse(String string) {
        super(string);
    }

    //仓库下的区域作已经处理传感器的测量值，这里不作处理
    @Override
    public boolean handleNotification(int measurement, Sensor sensor) {
        return false;
    }

    //重载火警发生时的行动
    @Override
    public void fireAlarm(SecurityZone zone, Sensor sensor) {
        if (zone instanceof Area) {
            // 子区域已经打开了喷撒器，这里呼叫保安马上检查发火区域
            System.out.println("保安请马上去检查" + this.name + "的" + zone.name);
            if (getParent() != null)
                getParent().fireAlarm(zone, sensor);
            return;
        }
        super.fireAlarm(zone, sensor);
    }
}
