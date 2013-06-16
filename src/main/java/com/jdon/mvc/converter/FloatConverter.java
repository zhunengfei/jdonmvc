package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;


public class FloatConverter implements TypeConverter<Float> {

    public Float convert(Class<?> type, Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Float.parseFloat(formValue);
    }

}
