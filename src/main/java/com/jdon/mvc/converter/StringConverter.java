package com.jdon.mvc.converter;



public class StringConverter implements TypeConverter<String> {

    public String convert(Class<?> type, Object value) {
        return FormValueHelper.singleValue(value);
    }

}
