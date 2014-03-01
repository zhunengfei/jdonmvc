package com.jdon.mvc.plugin;

import com.jdon.mvc.core.ComponentHolder;

import javax.servlet.ServletContext;

/**
 * User: oojdon
 * Date: 13-6-12
 * Time: 上午12:05
 */
public interface JdonMvcPlugin {

    void init(ComponentHolder holder);

    void dispose(ServletContext servletContext);
}
