package com.jdon.mvc.annotations;


import com.jdon.mvc.ioc.BeanType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * default this annotation will mark some fields should be set service
 * which managed by JdonFrameWork.
 * 表示调用一个IOC容器管理的bean,默认调用Jdon管理的bean
 *
 * @author oojdon
 */

@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface In {

    String value() default "";

    BeanType type() default BeanType.service;
}
