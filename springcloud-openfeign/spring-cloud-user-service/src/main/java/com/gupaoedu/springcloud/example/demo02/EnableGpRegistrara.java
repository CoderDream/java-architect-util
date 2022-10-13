package com.gupaoedu.springcloud.example.demo02;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GpImportBeanDefinitionRegistrar.class)
public @interface EnableGpRegistrara {

}
