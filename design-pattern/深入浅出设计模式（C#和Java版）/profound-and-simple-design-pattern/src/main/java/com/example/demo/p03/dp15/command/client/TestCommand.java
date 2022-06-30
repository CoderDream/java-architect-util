package com.example.demo.p03.dp15.command.client;
/**
 * 我们设计一个Switch(开关)，它可以开关风扇，也可以开关灯。开关类会有两个方法，一个是关方法，一个是开方法，它不用知道开关什么。
 * Command模式将开关的请求变成对象传给Switch,这个请求可以存储或转交给其它对象,Command Pattern的关键是要具有
 * Command接口,声明Execute(执行)的操作。在例子中，Switch(开关)是调用者，因为它调用了Execute的操作。
 * 具体命令如:LightOnCommand,实现了Command接口的Execute操作，它知道接收者对象Light的Turnon操作，充当Adapter的角色(参照Adapter模式)，它连接了接收者和调用者。
 */

import com.example.demo.p03.dp15.command.bean.*;
import com.example.demo.p03.dp15.command.bean.impl.FanOffCommand;
import com.example.demo.p03.dp15.command.bean.impl.FanOnCommand;
import com.example.demo.p03.dp15.command.bean.impl.LightOffCommand;
import com.example.demo.p03.dp15.command.bean.impl.LightOnCommand;

/**
 * 测试程序
 */
public class TestCommand {
    public static void main(String[] args) {
        Light testLight = new Light();
        LightOnCommand testLOC = new LightOnCommand(testLight);
        LightOffCommand testLFC = new LightOffCommand(testLight);
        Switch aSwitch = new Switch(testLOC, testLFC);
        aSwitch.flipUp();
        aSwitch.flipDown();
        Fan testFan = new Fan();
        FanOnCommand fanOnCommand = new FanOnCommand(testFan);
        FanOffCommand fanOffCommand = new FanOffCommand(testFan);
        aSwitch = new Switch(fanOnCommand, fanOffCommand);
        aSwitch.flipUp();
        aSwitch.flipDown();
    }
}
