package com.jdon.mvc.validation.infrastructure;

import com.jdon.mvc.validation.ValidateMessage;
import com.jdon.mvc.validation.Validator;


/**                       
* @author:oojdon
* 
*/
public class NotEqual implements Validator{
	
	private String one;
	
	private String two;
	
	public NotEqual(String one,String two){
		this.one = one;
		this.two = two;
	}
	
	public ValidateMessage validate() {
				
		if(!one.equals(two)){
			return new ValidateMessage("");
		}		
		return OK;

	}

}
