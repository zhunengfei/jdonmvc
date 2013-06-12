package com.jdon.mvc.converter;



public class StringConverter implements TypeConverter<String> {

    public String convert(Object value) {
        return FormValueHelper.singleValue(value);
    }

}
