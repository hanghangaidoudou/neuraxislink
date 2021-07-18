package com.etong.pms.annotation;

import java.lang.annotation.*;

/**
 * @ClassName PassToken
 * @Author wch
 * @DATE 2019/8/23 20:02
 * @return
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}