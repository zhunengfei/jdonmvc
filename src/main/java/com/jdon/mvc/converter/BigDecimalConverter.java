package com.jdon.mvc.converter;

import com.jdon.mvc.util.StringUtils;

import java.math.BigDecimal;

public class BigDecimalConverter implements TypeConverter<BigDecimal> {

	public BigDecimal convert(Class<?> type, Object value) {
		String formValue = FormValueHelper.singleValue(value);
		if (StringUtils.isEmpty(formValue))
			return null;
		return new BigDecimal(formValue);
	}

}
