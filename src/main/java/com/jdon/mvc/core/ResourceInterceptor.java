package com.jdon.mvc.core;

import com.jdon.mvc.represent.Represent;

/**
 * 拦截器
 * User: Asion
 * Date: 13-6-9
 * Time: 上午10:03
 */
public interface ResourceInterceptor {

    boolean pre(Object handler);

    void post(Object handler, Represent represent);

    void after(Object handler, Exception ex);
}
