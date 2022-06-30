package com.example.demo.p03.dp14.chain.client;

import com.example.demo.p03.dp14.chain.bean.impl.Area;
import com.example.demo.p03.dp14.chain.bean.impl.Building;
import com.example.demo.p03.dp14.chain.bean.impl.TemperatureSensor;
import com.example.demo.p03.dp14.chain.bean.impl.Warehouse;

/**
 * 安全区域测验
 */
public class Client {
    public static void main(String[] args) {
        Area area = new Area("经理室");
        Warehouse warehouse = new Warehouse("仓库(B)");
        TemperatureSensor ts_at_corner = new TemperatureSensor("右边角");
        area.setParent(warehouse);//区域a在仓库w中
        Building building = new Building("#2");
        warehouse.setParent(building);//仓库w在大楼b中
        area.notify(200, ts_at_corner);//区域A传感器的测量值是200
    }
}
