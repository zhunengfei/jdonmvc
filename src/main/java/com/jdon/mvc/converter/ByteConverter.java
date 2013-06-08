package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.util.StringUtils;


@Convert({Byte.class,byte.class})
public class ByteConverter implements TypeConverter<Byte> {

    public Byte convert(Object value) {
    	String formValue = FormValueHelper.singleValue(value);
    	if(StringUtils.isEmpty(formValue))
    		return null;
        return Byte.parseByte(formValue);
    }

}
