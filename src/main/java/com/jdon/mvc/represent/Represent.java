package com.jdon.mvc.represent;

import com.jdon.mvc.core.FrameWorkContext;

/**
 * 一种表示，这是REST中的概念，资源通过某种表示传递到客户端 比如Json,Atom,通常用HTML发到浏览器供人浏览，要发给机器使用就用
 * 便于机器理解的表示 DEV可通过实现这个模板方法对表示进行定制
 * 
 * @author oojdon
 * 
 */
public interface Represent {

	void render(FrameWorkContext fc) throws RepresentationRenderException;

}
