package com.bboyhan.common.annotation;

import java.lang.annotation.*;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/22 18:12
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbName {
    String value();
}
