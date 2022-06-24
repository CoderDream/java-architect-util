package com.example.demo.p01.dp03.abstractfactory.factory;

import com.example.demo.p01.dp03.abstractfactory.bean.cpu.CPU;
import com.example.demo.p01.dp03.abstractfactory.bean.harddisk.HardDisk;
import com.example.demo.p01.dp03.abstractfactory.bean.mainboard.MainBoard;

/**
 * 定义抽象电脑工厂类
 */
public abstract class ComputerFactory {
    CPU cpu;
    HardDisk hd;
    MainBoard mb;

    public void show() {
        try {
            System.out.println(this.getClass().getName() + "生产的电脑配置");
            System.out.println("CPU:" + cpu.getCPU());
            System.out.println("HardDisk:" + hd.getSize());
            System.out.print("MainBoard:");
            mb.Attach(cpu);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
