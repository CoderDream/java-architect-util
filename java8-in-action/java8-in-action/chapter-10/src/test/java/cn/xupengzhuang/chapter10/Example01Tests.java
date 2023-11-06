package cn.xupengzhuang.chapter10;

import cn.xupengzhuang.chapter10.example01.Car;
import cn.xupengzhuang.chapter10.example01.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Example01Tests {

    @Test
    public void test(){
        Person person = new Person();
        Car car = person.getCar();
        //下面的语句会触发NullPointException
        car.getInsurance();
    }

}
