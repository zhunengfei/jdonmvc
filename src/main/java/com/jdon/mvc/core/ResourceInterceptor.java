package com.jdon.mvc.core;

import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.java.Handler;

import java.util.regex.Pattern;

/**
 * 拦截器
 * <p/>
 * User: Asion
 * Date: 13-6-9
 * Time: 上午10:03
 */
public abstract class ResourceInterceptor {

    abstract boolean pre(Handler handler);

    abstract void post(Handler handler, Represent represent);

    abstract void after(Handler handler, Exception ex);


    /**
     * 拦截路径，默认是全部，子类可覆盖
     *
     * @return
     */
    protected Pattern getUrlMatchPattern() {
        return Pattern.compile("/*");
    }


}
