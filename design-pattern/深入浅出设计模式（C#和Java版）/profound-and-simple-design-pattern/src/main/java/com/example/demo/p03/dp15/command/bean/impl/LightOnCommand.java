package com.example.demo.p03.dp15.command.bean.impl;

import com.example.demo.p03.dp15.command.bean.Command;
import com.example.demo.p03.dp15.command.bean.Light;

/**
 * 开灯命令
 */
public class LightOnCommand implements Command {
    private final Light myLight;

    public LightOnCommand(Light L) {
        myLight = L;
    }

    public void execute() {
        myLight.turnOn();
    }
}
