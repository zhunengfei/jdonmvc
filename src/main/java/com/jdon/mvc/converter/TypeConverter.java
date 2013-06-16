package com.jdon.mvc.converter;


/**
 * @param <T> 原生类型转换，负责将表单里的字符串类型转换为方法里的原生类型
 * @author oojdon (vsmysee@gmail.com)
 */

public interface TypeConverter<T> {

    T convert(Class<?> type, Object value);

}
