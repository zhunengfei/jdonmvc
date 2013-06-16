package com.jdon.mvc.converter;

/**
 * User: oojdon
 * Date: 13-6-16
 * Time: 下午11:26
 */
public class EnumConverter implements TypeConverter<Enum> {

    @Override
    public Enum convert(Class<?> type, Object value) {
        String formValue = FormValueHelper.singleValue(value);
        Class<? extends Enum> enumType = (Class<? extends Enum>) type;
        return Enum.valueOf(enumType, formValue);
    }
}
