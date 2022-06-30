package com.example.demo.p03.dp15.command.bean;

/**
 * 总开关类，控制风扇和灯开关
 */
public class Switch {
    private final Command UpCommand;
    private final Command DownCommand;

    public Switch(Command Up, Command Down) {
        UpCommand = Up; //先注册开关的命令
        DownCommand = Down;
    }

    public void flipUp() { //调用Command接收者的方法
        UpCommand.execute();
    }

    public void flipDown() {
        DownCommand.execute();
    }
}
