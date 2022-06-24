package com.example.demo.p01.dp03.abstractfactory.factory;

import com.example.demo.p01.dp03.abstractfactory.bean.cpu.AMD;
import com.example.demo.p01.dp03.abstractfactory.bean.harddisk.Maxtor;
import com.example.demo.p01.dp03.abstractfactory.bean.mainboard.MSIK7N2G;

/**
 * 抽象工厂类派生类Dell，定义其返回的系列配件产品:
 */
public class Dell extends ComputerFactory {
    public Dell() {
        cpu = new AMD();
        hd = new Maxtor();
        mb = new MSIK7N2G();
    }

    @Override
    public String toString() {
        return "computer-world.Dell生产的电脑配置";
    }
}
