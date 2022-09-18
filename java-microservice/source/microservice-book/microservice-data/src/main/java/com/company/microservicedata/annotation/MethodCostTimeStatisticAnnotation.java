package com.company.microservicedata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法耗时注解
 * 
 * @author xindaqi
 * @since 2021-01-12
 */

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodCostTimeStatisticAnnotation {
    int time() default 0; 
}