package com.gupaoedu.springcloud.example.demo02;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyBeanDefinitionRegistrar.class)
public @interface EnableMyRegistrar {
}
