package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;


public class ByteConverter implements TypeConverter<Byte> {

    public Byte convert(Class<?> type, Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Byte.parseByte(formValue);
    }

}
