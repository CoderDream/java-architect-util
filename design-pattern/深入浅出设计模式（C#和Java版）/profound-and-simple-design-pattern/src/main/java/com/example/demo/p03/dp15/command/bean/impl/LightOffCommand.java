package com.example.demo.p03.dp15.command.bean.impl;

import com.example.demo.p03.dp15.command.bean.Command;
import com.example.demo.p03.dp15.command.bean.Light;

/**
 * 关灯命令
 */
public class LightOffCommand implements Command {
    private final Light myLight;

    public LightOffCommand(Light L) {
        myLight = L;
    }

    public void execute() {
        myLight.turnOff();
    }
}
