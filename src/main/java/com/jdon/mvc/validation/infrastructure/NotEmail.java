package com.jdon.mvc.validation.infrastructure;

import com.jdon.mvc.validation.ValidateMessage;
import com.jdon.mvc.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author oojdon
 *
 */
public class NotEmail implements Validator{

	private static final String emailAddressPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	
	private static Pattern pattern = Pattern.compile(emailAddressPattern, Pattern.CASE_INSENSITIVE);
	
	private String _email;
	
	public NotEmail(String email){
		_email=email;
	}
	
	public ValidateMessage validate() {
		
		if(_email == null || _email.equals(""))
			return new ValidateMessage("");
		
		Matcher matcher = pattern.matcher(_email);
		
		if (!matcher.matches()) {
			return new ValidateMessage("");

		}
		return OK;
		

	}

}
