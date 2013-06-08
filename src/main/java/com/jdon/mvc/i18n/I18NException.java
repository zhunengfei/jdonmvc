package com.jdon.mvc.i18n;


/**                       
* @author:Chard Rapid
* 
* */
public class I18NException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public I18NException() {}
	

    public I18NException(String message) {
        super(message);
    }

    public I18NException(Throwable cause) {
        super(cause);
    }

    public I18NException(String message, Throwable cause) {
        super(message, cause);
    }
	
	
}
