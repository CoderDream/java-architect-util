package com.gupaoedu.springcloud.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Configuration
public class TestConfiguration {

    @Bean("testClass1")
    TestClass testClass1(){
        return new TestClass("TestClass1");
    }

    @Qualifier
    @Bean("testClass2")
    TestClass testClass2(){
        return new TestClass("TestClass2");
    }
}
