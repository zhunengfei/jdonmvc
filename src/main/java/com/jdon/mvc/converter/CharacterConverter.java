package com.jdon.mvc.converter;



public class CharacterConverter implements TypeConverter<Character> {

    public Character convert(Object value) {
    	String s = FormValueHelper.singleValue(value);
        if (s.length()==0)
            throw new IllegalArgumentException("Cannot convert empty string to char.");
        return s.charAt(0);
    }

}
