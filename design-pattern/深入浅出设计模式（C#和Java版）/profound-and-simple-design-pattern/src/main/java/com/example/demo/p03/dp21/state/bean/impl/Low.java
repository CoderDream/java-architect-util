package com.example.demo.p03.dp21.state.bean.impl;

import com.example.demo.p03.dp21.state.bean.Car;
import com.example.demo.p03.dp21.state.bean.State;

/**
 * 慢速档:
 */
public class Low extends State {
    public void pull(Car wrapper) {
        wrapper.setState(new Medium());
        System.out.println("挂中速");
    }
}
