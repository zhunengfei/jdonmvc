package com.jdon.mvc.validation;


public final class OkMessage extends ValidateMessage {

	public OkMessage() {	
	}
	
	@Override
	public ValidateMessage addPositionKey(String key) {
				
		return this;
	}
	
	@Override
	public ValidateMessage addMessage(String message) {
	
		return this;
	}
	
	@Override
	public ValidateMessage addi18nKey(String i18nKey){
	
		return this;
	}
}
