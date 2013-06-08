package com.jdon.mvc.represent;

public class RepresentationRenderException extends Exception {

	private static final long serialVersionUID = 6082150557792704447L;

	public RepresentationRenderException() {

	}

	public RepresentationRenderException(String message) {
		super(message);
	}

	public RepresentationRenderException(Throwable cause) {
		super(cause);
	}

	public RepresentationRenderException(String message, Throwable cause) {
		super(message, cause);
	}

}
