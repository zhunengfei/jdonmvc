package com.jdon.mvc.core;

/**
 * User: Asion
 * Date: 13-6-6
 * Time: 下午4:09
 */
public class ActionException extends Exception {

    public ActionException() {
        super();
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionException(Throwable cause) {
        super(cause);
    }
}
