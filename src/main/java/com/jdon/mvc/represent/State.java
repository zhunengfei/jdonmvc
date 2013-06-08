package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FrameWorkContext;

import java.io.IOException;


/**
 * Representational State Transfer表述性状态转移
 * 状态转移，重定向是否对应一个状态转移？
 * @author Owen (yubing744@163.com)
 * @author oojon
 * 
 */
public class State implements Represent {

	private String url;

	public State(String url) {
		this.url = url;
	}

	@Override
	public void render(FrameWorkContext fc) throws RepresentationRenderException {

		try {
			Env.res().sendRedirect(Env.req().getContextPath() + this.url);
		} catch (IOException e) {
			throw new RepresentationRenderException(
					"can't transfer the resource", e);
		}
	}

}
