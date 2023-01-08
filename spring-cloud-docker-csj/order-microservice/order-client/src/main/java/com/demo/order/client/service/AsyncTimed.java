package com.demo.order.client.service;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AsyncTimed {

}
