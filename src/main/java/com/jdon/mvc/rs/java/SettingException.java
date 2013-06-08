package com.jdon.mvc.rs.java;

public class SettingException extends RuntimeException {
    private static final long serialVersionUID = -8040463849613736889L;
    
    public SettingException() {   	
    }

    public SettingException(Throwable e) {
        super(e);
    }

    public SettingException(String msg) {
        super(msg);
    }

    public SettingException(String msg, Throwable e) {
        super(msg, e);
    }

}