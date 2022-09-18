package com.company.microserviceuser.annotation;

import java.lang.annotation.*;

/**
 * 自定义异常catch
 * @author xindaqi
 * @since 2020-12-27
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CatchThrows {

    Class<? extends Throwable>[] value() default {};
}
