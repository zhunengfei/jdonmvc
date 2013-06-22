package com.jdon.mvc.config;

import com.jdon.mvc.annotations.ExceptionHandler;
import com.jdon.mvc.annotations.Join;
import com.jdon.mvc.annotations.Plugin;
import com.jdon.mvc.annotations.Path;
import com.jdon.mvc.core.ExceptionResolver;
import com.jdon.mvc.core.ResourceInterceptor;
import com.jdon.mvc.plugin.JdonMvcPlugin;
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
        List<Class<?>> annotations = AnnotationUtils.findAnnotations(context, Plugin.class);
        checkInterface(annotations, JdonMvcPlugin.class);
        return annotations;
    }

    public static List<Class<?>> scanInterceptorClass(final ServletContext context) {
        List<Class<?>> annotations = AnnotationUtils.findAnnotations(context, Join.class);
        for (Class<?> annotation : annotations) {
            //判断是否是ResourceInterceptor的子类
            if (!ResourceInterceptor.class.isAssignableFrom(annotation)) {
                throw new ConfigException("interceptor class not extends ResourceInterceptor");
            }
        }
        return annotations;
    }


    public static Class<?> scanExceptionHandlerClass(final ServletContext context) {
        List<Class<?>> annotations = AnnotationUtils.findAnnotations(context, ExceptionHandler.class);
        if (annotations.size() > 1) {
            throw new ConfigException("find many exception handler class,require only one");
        }

        checkInterface(annotations, ExceptionResolver.class);

        if (annotations.size() == 1) {
            return annotations.get(0);
        }
        return null;
    }


    private static void checkInterface(List<Class<?>> list, Class<?> inter) {
        for (Class<?> annotation : list) {
            Class<?>[] interfaces = annotation.getInterfaces();
            if (interfaces.length == 0) {
                throw new ConfigException("found class not implements " + inter);
            }
            boolean imp = false;
            for (Class<?> anInterface : interfaces) {
                if (anInterface == inter) {
                    imp = true;
                }
            }

            if (!imp) {
                throw new ConfigException("found class not implements " + inter);
            }

        }
    }


}
