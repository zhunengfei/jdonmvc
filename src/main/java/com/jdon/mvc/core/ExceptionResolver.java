package com.jdon.mvc.core;

import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.flow.MaxUploadSizeException;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.java.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 * User: Asion
 * Date: 13-6-6
 * Time: 下午3:59
 */
public interface ExceptionResolver {

    Represent resolveActionException(HttpServletRequest request, HttpServletResponse response, Handler handler, ActionException ex);

    Represent resolveBindingException(HttpServletRequest request, HttpServletResponse response, Handler handler, BindingException ex);

    Represent resolveUploadException(HttpServletRequest request, HttpServletResponse response, Handler handler, MaxUploadSizeException ex);
}
