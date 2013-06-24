package com.jdon.mvc.converter;

/**
 * 绑定异常，发生在框架试图绑定请求中的数据到java类型的时候
 * User: Asion
 * Date: 13-6-21
 * Time: 下午2:00
 */
public class BindingException extends RuntimeException {

    public BindingException() {
        super();
    }

    public BindingException(String message) {
        super(message);
    }

    public BindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingException(Throwable cause) {
        super(cause);
    }
}
