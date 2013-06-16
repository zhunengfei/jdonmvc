package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;


public class IntegerConverter implements TypeConverter<Integer> {

    public Integer convert(Class<?> type, Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Integer.parseInt(formValue);
    }

}
