package com.jdon.mvc.validation;

import java.util.List;

/**
 * 给JSR BeanValidator预留接口
 * @author Chard Rapid
 * @author oojdon
 *
 */
public interface BeanValidator {
	
	List<ValidateMessage> validate(Object obj);
}
