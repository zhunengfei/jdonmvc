package com.jdon.mvc.validation;


/**
 * 
 * @author:Chard Rapid
 *
 */
public interface Validator {
	
	public static final ValidateMessage OK = new OkMessage();
	
	public ValidateMessage validate();
}
