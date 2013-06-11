package com.jdon.mvc.converter;

public class BooleanConverter implements TypeConverter<Boolean> {

    public Boolean convert(Object value) {
        return Boolean.parseBoolean(FormValueHelper.singleValue(value));
    }
    

}
