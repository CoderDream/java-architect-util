package com.example.demo.p03.dp15.command.bean.impl;

import com.example.demo.p03.dp15.command.bean.Command;
import com.example.demo.p03.dp15.command.bean.Fan;

/**
 * 开风扇命令
 */
public class FanOnCommand implements Command {
    private final Fan myFan;

    public FanOnCommand(Fan F) {
        myFan = F;
    }

    public void execute() {
        myFan.startRotate();
    }
}
