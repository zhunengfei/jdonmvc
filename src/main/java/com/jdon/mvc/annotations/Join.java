package com.jdon.mvc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * 框架的重要处理留下了拦截界面，可用这个标记介入
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Join {
}
