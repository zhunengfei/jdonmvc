package com.jdon.mvc.validation;

public class ValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public ValidationException() {}

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
