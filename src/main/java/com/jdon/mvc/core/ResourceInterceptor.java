package com.jdon.mvc.core;

import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.java.Handler;

/**
 * 拦截器
 * <p/>
 * 粒度问题？
 * User: Asion
 * Date: 13-6-9
 * Time: 上午10:03
 */
public interface ResourceInterceptor {

    boolean pre(Handler handler);

    void post(Handler handler, Represent represent);

    void after(Handler handler, Exception ex);
}
