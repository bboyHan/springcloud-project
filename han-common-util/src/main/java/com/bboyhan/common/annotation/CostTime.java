package com.bboyhan.common.annotation;

import java.lang.annotation.*;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 17:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CostTime {
    String name() default "";
}
