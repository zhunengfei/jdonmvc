package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;


public class DoubleConverter implements TypeConverter<Double> {

    public Double convert(Class<?> type, Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Double.parseDouble(formValue);
    }

}
