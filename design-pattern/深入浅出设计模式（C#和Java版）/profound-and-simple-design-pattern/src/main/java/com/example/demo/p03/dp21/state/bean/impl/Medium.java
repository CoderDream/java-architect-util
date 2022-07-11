package com.example.demo.p03.dp21.state.bean.impl;

import com.example.demo.p03.dp21.state.bean.Car;
import com.example.demo.p03.dp21.state.bean.State;

/**
 * 中速档：
 */
public class Medium extends State {
    public void pull(Car wrapper) {
        wrapper.setState(new High());
        System.out.println("挂高速");
    }
}
