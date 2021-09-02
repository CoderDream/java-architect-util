package com.coderdream;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 17:26
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解集
 *
 * @author ruoyi
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels
{
    Excel[] value();
}