package cn.xupengzhuang.chapter10.example02;


import java.util.Optional;

/**
 * 使用Optional来改写example01中的例子。
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
