package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.util.StringUtils;


@Convert({Short.class,short.class})
public class ShortConverter implements TypeConverter<Short> {

    public Short convert(Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Short.parseShort(formValue);
    }

}
