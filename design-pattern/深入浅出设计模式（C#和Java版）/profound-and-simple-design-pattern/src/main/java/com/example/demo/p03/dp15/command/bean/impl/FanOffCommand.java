package com.example.demo.p03.dp15.command.bean.impl;

import com.example.demo.p03.dp15.command.bean.Command;
import com.example.demo.p03.dp15.command.bean.Fan;

/**
 * 停风扇命令
 */
public class FanOffCommand implements Command {
    private final Fan myFan;

    public FanOffCommand(Fan F) {
        myFan = F;
    }

    public void execute() {
        myFan.stopRotate();
    }
}
