package com.jdon.mvc.rs;

public class InvalidResourceException extends RuntimeException {
    private static final long serialVersionUID = -8040463849613736889L;
    
    public InvalidResourceException() {
    }

    public InvalidResourceException(Throwable e) {
        super(e);
    }

    public InvalidResourceException(String msg) {
        super(msg);
    }

    public InvalidResourceException(String msg, Throwable e) {
        super(msg, e);
    }

}