package com.jdon.mvc.core;


import com.jdon.mvc.http.WebRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * web编程的环境
 *
 * @author oojdon
 */
public class Env {

    private WebRequest req;

    private HttpServletResponse res;

    private ServletContext ctx;

    private static ThreadLocal<Env> locals = new ThreadLocal<Env>();

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


}
