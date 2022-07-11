package com.example.demo.p03.dp21.state.bean;
//


import com.example.demo.p03.dp21.state.bean.impl.Off;

/**
 * 定义抽象状态类:
 */
public abstract class State {
    public void pull(Car wrapper) {
        wrapper.setState(new Off());
        System.out.println(" turning off");
    }
}
