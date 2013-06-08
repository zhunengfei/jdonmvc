package com.jdon.mvc.converter;

import java.lang.reflect.Array;

/**
 * 表单里面的值用name取到的是一个Object
 * 用这个类来做转换
 * @author oojdon
 *
 */
public class FormValueHelper {
	
	public static String[] arrayValue(Object val) {
		if (val.getClass().isArray()) {
			return (String[]) val;
		}
		return new String[] { (String) val };
	}

	public static String singleValue(Object val) {
		if (val.getClass().isArray()) {
			return (String) Array.get(val, 0);
		}
		return (String) val;
	}

}
