package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;


public class ShortConverter implements TypeConverter<Short> {

    public Short convert(Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Short.parseShort(formValue);
    }

}
