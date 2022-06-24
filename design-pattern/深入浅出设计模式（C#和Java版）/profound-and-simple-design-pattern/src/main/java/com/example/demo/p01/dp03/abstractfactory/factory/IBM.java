package com.example.demo.p01.dp03.abstractfactory.factory;

import com.example.demo.p01.dp03.abstractfactory.bean.cpu.Intel;
import com.example.demo.p01.dp03.abstractfactory.bean.harddisk.WestDigit;
import com.example.demo.p01.dp03.abstractfactory.bean.mainboard.MSI865PE;

/**
 * 抽象工厂类派生类IBM，定义其返回的系列配件产品
 */
public class IBM extends ComputerFactory {
    public IBM() {
        cpu = new Intel();
        hd = new WestDigit();
        mb = new MSI865PE();
    }

    @Override
    public String toString() {
        return "computer-world.IBM生产的电脑配置";
    }
}
