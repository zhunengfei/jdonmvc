package com.jdon.mvc.core;


import com.jdon.mvc.http.WebRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * web编程的环境
 *
 * @author oojdon
 */
public class Env {

    private WebRequest req;

    private HttpServletResponse res;

    private ServletContext ctx;

    private Map<String, String> params = Collections.emptyMap();

    private static ThreadLocal<Env> locals = new ThreadLocal<Env>();

    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    public static Env get() {
        return locals.get();
    }

    public static void create(WebRequest req, HttpServletResponse res,
                              ServletContext context) {
        locals.set(new Env(req, res, context));
    }

    public static void destroy() {
        locals.remove();
    }

    public static WebRequest req() {
        return get().getReq();
    }

    public static HttpServletResponse res() {
        return get().getRes();
    }

    public static ServletContext ctx() {
        return get().getCtx();
    }

    public static void setResourceParams(Map<String, String> params) {
        get().setParams(params);
    }

    public static String params(String key) {
        Map<String, String> params2 = get().getParams();
        String string = params2.get(key);
        if(string == null) {
            return Env.req().getParameter(key);
        }
        return string;
    }

    private Env(WebRequest req, HttpServletResponse res,
                ServletContext context) {
        this.req = req;
        this.res = res;
        this.ctx = context;
    }

    public ServletContext getCtx() {
        return ctx;
    }

    public WebRequest getReq() {
        return req;
    }

    public HttpServletResponse getRes() {
        return res;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }


}
