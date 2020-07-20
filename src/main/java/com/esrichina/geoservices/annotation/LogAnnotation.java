package com.esrichina.geoservices.annotation;

import java.lang.annotation.*;

/**
 * 日志自定义注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String value() default "";
}
