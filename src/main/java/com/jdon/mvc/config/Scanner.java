package com.jdon.mvc.config;

import com.jdon.mvc.annotations.ExceptionHandler;
import com.jdon.mvc.annotations.Plugin;
import com.jdon.mvc.annotations.Path;
import com.jdon.mvc.util.AnnotationUtils;

import javax.servlet.ServletContext;
import java.util.List;


/**
 * 扫描器
 *
 * @author oojdon
 */
public class Scanner {

    public static List<Class<?>> scanRestfulClass(final ServletContext context) {
        return AnnotationUtils.findAnnotations(context, Path.class);
    }


    public static List<Class<?>> scanPluginClass(final ServletContext context) {
        return AnnotationUtils.findAnnotations(context, Plugin.class);
    }


    public static Class<?> scanExceptionHandlerClass(final ServletContext context) {
        List<Class<?>> annotations = AnnotationUtils.findAnnotations(context, ExceptionHandler.class);
        if (annotations.size() > 1) {
            throw new ConfigException("find many exception handler class,require only one");
        }
        if (annotations.size() == 1) {
            return annotations.get(0);
        }
        return null;
    }


}
