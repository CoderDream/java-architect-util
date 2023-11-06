package cn.xupengzhuang.chapter10.example01;

/**
 * Person可能有Car，也可能没有Car。
 * 如果没有Car，我们对Person调用getCar()会返回一个null，此时如果继续来获取Insurance，就会触发NullPointException
 */
public class Person {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
