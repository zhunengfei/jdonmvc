package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.util.StringUtils;


@Convert({Long.class,long.class})
public class LongConverter implements TypeConverter<Long> {

    public Long convert(Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Long.parseLong(formValue);
    }

}
