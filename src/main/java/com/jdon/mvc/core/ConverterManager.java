package com.jdon.mvc.core;

import com.jdon.mvc.rs.java.MethodParameter;

import java.util.Map;

/**
 * 
 * @author oojdon (vsmysee@gmail.com)
 * 形参类型转换器
 *
 */
public interface ConverterManager {
		
	Object[] convert(Map<MethodParameter, Map<String, Object>> methodValue, Object[] args);

}
