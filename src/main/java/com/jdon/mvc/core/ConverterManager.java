package com.jdon.mvc.core;

import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.rs.java.MethodParameter;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author oojdon (vsmysee@gmail.com)
 * 形参类型转换器
 *
 */
public interface ConverterManager {
		
	Object[] convert(List<MethodParameter> methodParameters, Object[] args) throws BindingException;

    Object convert(Class<?> clazz, Object value) throws BindingException;

    void doPathMapping(MethodParameter methodParameter, String key, Object value);

}
