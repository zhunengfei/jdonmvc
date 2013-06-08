package com.jdon.mvc.validation.infrastructure;

import com.jdon.mvc.validation.ValidateMessage;
import com.jdon.mvc.validation.Validator;


/**                       
* @author:Chard Rapid
*/
public class NotNull implements Validator {
	
	private Object _obj;
	
	public NotNull(Object obj){
		_obj=obj;
	}
	
	public ValidateMessage validate() {
				
		if(_obj==null||"".equals(_obj.toString())){
			return new ValidateMessage("");
		}
		
		return OK;

	}

}
