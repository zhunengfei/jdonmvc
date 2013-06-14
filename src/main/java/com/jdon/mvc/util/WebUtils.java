package com.jdon.mvc.util;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * User: Asion
 * Date: 13-6-14
 * Time: 上午11:05
 */
public class WebUtils {

    public static final String TEMP_DIR_CONTEXT_ATTRIBUTE = "javax.servlet.context.tempdir";


    public static File getTempDir(ServletContext servletContext) {
        return (File) servletContext.getAttribute(TEMP_DIR_CONTEXT_ATTRIBUTE);
    }
}
