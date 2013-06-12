package com.jdon.mvc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: oojdon
 * Date: 13-6-12
 * Time: 下午10:49
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Plugin {
}
