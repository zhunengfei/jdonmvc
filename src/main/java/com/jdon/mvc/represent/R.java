package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 这个类完全是为了偷懒，你的controller可以继承这个类来偷懒
 */
public class R {

    protected static Object session(String key) {
        return Env.session().getAttribute(key);
    }

    protected static HttpSession session() {
        return Env.session();
    }

    protected static HttpServletRequest req() {
        return Env.req();
    }

    protected static HttpServletResponse res() {
        return Env.res();
    }

    protected static void session(String key, Object value) {
        Env.session().setAttribute(key, value);
    }

    protected static Represent ok(String content) {
        return new Text(content);
    }

    protected static Html vm(String vm) {
        return new Html(vm);
    }

    protected static Represent go(String path) {
        return new Redirect(path);
    }

}
