package cn.xupengzhuang.chapter10;

import cn.xupengzhuang.chapter10.example02.Car;
import cn.xupengzhuang.chapter10.example02.Insurance;
import cn.xupengzhuang.chapter10.example02.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@Slf4j
public class OptionalTests {

    /**
     * 声明一个非空的Optional
     */
    @Test
    public void test1(){
        Optional<Car> optCat = Optional.empty();
    }

    /**
     * 依据一个非空值来创建Optional
     */
    @Test
    public void test2(){
        Car car = new Car();
        Optional<Car> optCar = Optional.of(car);
    }

    /**
     * 可接受null的Optional
     */
    @Test
    public void test3(){
        Car car = null;
        Optional<Car> optionalCar = Optional.ofNullable(car);
    }

    @Test
    public void test4(){
        Insurance insurance = null;
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> s = optionalInsurance.map(Insurance::getName);
        log.info("{}",s);
    }

    @Test
    public void test5(){
        Insurance insurance = new Insurance();
        insurance.setName("中国人寿");
        Car car = new Car();
        car.setInsurance(Optional.of(insurance));
        Person person = new Person();
        person.setCar(Optional.of(car));

        Optional<Person> optPerson = Optional.of(person);
        /*Optional<String> name =
                optPerson.map(Person::getCar)
                        .map(Car::getInsurance)
                        .map(Insurance::getName);*/

        String name = optPerson
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("Unknow");
        log.info("{}",name);
    }

    /**
     * 两个Optional对象的组合
     */
    @Test
    public void test6(){

    }

    /**
     * 通过不解包的方式
     * @param optPerson
     * @param optCar
     * @return
     */
    public Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> optPerson,Optional<Car> optCar){
        Optional<Insurance> insurance = optPerson.flatMap(p -> optCar.map(c -> findCheapestInsurance(p, c)));
        return insurance;
    }

    /**
     * 解包的方式
     * @param optPerson
     * @param optCar
     * @return
     */
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> optPerson,Optional<Car> optCar){
        if (optPerson.isPresent() && optCar.isPresent()){
            return Optional.of(findCheapestInsurance(optPerson.get(),optCar.get()));
        }else {
            return Optional.empty();
        }
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        //一些业务逻辑操作

        Insurance insurance = new Insurance();
        insurance.setName("平安");
        return insurance;
    }

    /**
     * 使用filter剔除特定的值
     */
    @Test
    public void test7(){
        Insurance insurance = new Insurance();
        insurance.setName("剑桥保险");

        Optional<Insurance> insuranceOptional = Optional.of(insurance);
        insuranceOptional.filter(i -> "剑桥保险".equals(i.getName())).ifPresent(x -> System.out.println(x.getName()));
    }

    @Test
    public void test8(){
        Properties p1 = new Properties("a","5");
        Properties p2 = new Properties("b","true");
        Properties p3 = new Properties("c","1.0");

        assertEquals(5,readDuration(p1,"a"));
        assertEquals(0,readDuration(p1,"b"));
        assertEquals(0,readDuration(p1,"c"));

        assertEquals(5,readDurationByOptional(p1,"a"));
        assertEquals(0,readDurationByOptional(p1,"b"));
        assertEquals(0,readDurationByOptional(p1,"c"));
    }

    /**
     * 原始的命令时写法
     * @param prop
     * @param name
     * @return
     */
    public int readDuration(Properties prop,String name){
        String o = prop.get(name);
        if (o != null){
            try{
                int i = Integer.parseInt(o);
                if (i > 0){
                    return i;
                }
            }catch (Exception e){}
        }
        return 0;
    }

    /**
     * 将 readDuration 方法用 Optional 进行改写
     * @return
     */
    public int readDurationByOptional(Properties prop, String name){
        return Optional.ofNullable(prop.get(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

}
