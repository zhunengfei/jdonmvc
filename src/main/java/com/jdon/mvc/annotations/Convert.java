package com.jdon.mvc.annotations;

import java.lang.annotation.*;


/**
 * 方法形参转换器
 * @author oojdon
 *
 */

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Convert {

    Class<?>[] value();

}
