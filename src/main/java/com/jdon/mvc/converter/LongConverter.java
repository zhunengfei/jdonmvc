package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;


public class LongConverter implements TypeConverter<Long> {

    public Long convert(Class<?> type, Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Long.parseLong(formValue);
    }

}
