package com.jdon.mvc.converter;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.util.StringUtils;

import java.math.BigInteger;

@Convert(BigInteger.class)
public class BigIntegerConverter implements TypeConverter<BigInteger> {

	public BigInteger convert(Object value) {
		String formValue = FormValueHelper.singleValue(value);
		if (StringUtils.isEmpty(formValue))
			return null;
		return new BigInteger(formValue);
	}

}
