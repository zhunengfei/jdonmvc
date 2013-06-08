package com.jdon.mvc.core;

import javax.servlet.ServletException;

/**
 * 请求分派器，负责转发请求给框架的拦截器栈
 * Dispatcher的实现会被Filter hold住然后处于单态
 * 每次请求到来都通过这个Dispatcher来处理
 * @author oojdon
 *
 */
public interface Dispatcher {

	void dispatch() throws ServletException;

    FrameWorkContext getFc();

    void destroy();

}
