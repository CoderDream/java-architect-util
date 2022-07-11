package com.example.demo.p03.dp21.state.bean.impl;

import com.example.demo.p03.dp21.state.bean.Car;
import com.example.demo.p03.dp21.state.bean.State;

/**
 * 高速档:
 */
public class High extends State {
    public void pull(Car wrapper) {
        wrapper.setState(new Off());
        System.out.println("挂空档");
    }
}
