package com.example.demo.p03.dp21.state.client;

import com.example.demo.p03.dp21.state.bean.Car;

/**
 * 状态模式就用测试:
 */
class Client {
    public static void main(String[] args) {
        Car car = new Car();
        car.pull();
        car.pull();
        car.pull();
        car.pull();
    }
}
