package com.jdon.mvc.core;

import com.jdon.mvc.represent.Represent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 * User: Asion
 * Date: 13-6-6
 * Time: 下午3:59
 */
public interface ExceptionResolver {

    Represent resolveException(
            HttpServletRequest request, HttpServletResponse response, Object controller, Exception ex);
}
