package com.jdon.mvc.config;

import com.jdon.mvc.rs.method.Path;
import com.jdon.mvc.util.AnnotationUtils;

import javax.servlet.ServletContext;
import java.util.List;


/**
 * responsible for loading the class that DEV provide
 * such as Restful,Convert and so on.
 *
 * @author oojdon
 */
public class Scanner {

    public static List<Class<?>> scanRestfulClass(final ServletContext context) {
        return AnnotationUtils.findAnnotations(context, Path.class);
    }


}
