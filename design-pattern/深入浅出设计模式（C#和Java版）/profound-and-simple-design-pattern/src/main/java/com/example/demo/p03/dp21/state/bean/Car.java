package com.example.demo.p03.dp21.state.bean;

import com.example.demo.p03.dp21.state.bean.impl.Off;

/**
 * 汽车类，具有状态变量：
 */
public class Car {
    private State current;

    public Car() {
        current = new Off();
        System.out.println("新车生产出来了，现时空档");
    }

    public void setState(State s) {
        current = s;
    }

    public void pull() {
        current.pull(this);
    }
}
