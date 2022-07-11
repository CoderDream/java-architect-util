package com.example.demo.p03.dp21.state.bean.impl;

import com.example.demo.p03.dp21.state.bean.Car;
import com.example.demo.p03.dp21.state.bean.State;

/**
 * 空档:
 */
public class Off extends State {
    public void pull(Car wrapper) {
        wrapper.setState(new Low());
        System.out.println("挂空档");
    }
}
