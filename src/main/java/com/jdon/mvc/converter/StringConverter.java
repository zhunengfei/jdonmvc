package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;


public class StringConverter implements TypeConverter<String> {

    public String convert(Object value) {
        return FormValueHelper.singleValue(value);
    }

}
