package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;


@Convert({Boolean.class,boolean.class})
public class BooleanConverter implements TypeConverter<Boolean> {

    public Boolean convert(Object value) {
        return Boolean.parseBoolean(FormValueHelper.singleValue(value));
    }
    

}
