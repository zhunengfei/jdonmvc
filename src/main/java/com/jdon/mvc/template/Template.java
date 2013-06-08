package com.jdon.mvc.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * HTML表示的抽象，一般用各种模板引擎实现
 * 比如velocity,jsp,freemarker
 *
 * @author oojdon
 */
public interface Template {

    void render(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception;
}
