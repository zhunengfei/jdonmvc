package com.jdon.mvc.converter;


/**
 * 
 * @author oojdon (vsmysee@gmail.com)
 * @param <T>
 * 原生类型转换，负责将表单里的字符串类型转换为方法里的原生类型
 */

public interface TypeConverter<T> {

    T convert(Object value);	

}
