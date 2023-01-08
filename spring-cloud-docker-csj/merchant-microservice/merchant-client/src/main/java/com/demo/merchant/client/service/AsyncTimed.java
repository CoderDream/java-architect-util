package com.demo.merchant.client.service;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AsyncTimed {

}
