package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.util.StringUtils;


@Convert({Integer.class,int.class})
public class IntegerConverter implements TypeConverter<Integer> {

    public Integer convert(Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Integer.parseInt(formValue);
    }

}
