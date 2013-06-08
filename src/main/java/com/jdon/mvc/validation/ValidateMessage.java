package com.jdon.mvc.validation;

/**
 * @author:Chard Rapid
 * 
 */
public class ValidateMessage {

	private String _key;

	private String _message;

	private String _i18nKey;

	public ValidateMessage() {
	}

	public ValidateMessage(String message) {
		_message = message;
	}

	public ValidateMessage addPositionKey(String key) {
		_key = key;
		return this;
	}

	public String getPositionKey() {
		return _key;
	}

	public String getMessage() {
		return _message;
	}

	public String getI18nKey() {
		return _i18nKey;
	}

	public ValidateMessage addMessage(String message) {
		_message = message;
		return this;
	}

	public ValidateMessage addi18nKey(String i18nKey) {
		_i18nKey = i18nKey;
		return this;
	}

}
