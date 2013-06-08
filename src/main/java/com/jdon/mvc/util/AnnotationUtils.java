package com.jdon.mvc.util;

import com.jdon.mvc.scan.AnnotationDB;
import com.jdon.mvc.scan.ClasspathUrlFinder;
import com.jdon.mvc.scan.WarUrlFinder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @author yubing (yubing744@163.com)
 * 注解扫描工具
 *
 */
public class AnnotationUtils {

    private static final Log LOG = LogFactory.getLog(AnnotationUtils.class);

    private static Map<String, Set<String>> annotationIndex = null;

    public static List<Class<?>> findAnnotations(ServletContext context, Class<?> annotationClass) {

        if (annotationIndex == null) {
            annotationIndex = scanAnnotation(useDefaultURLs(context));
        }

        List<Class<?>> annotations = new ArrayList<Class<?>>();
        Set<String> entities = annotationIndex.get(annotationClass.getName());

        if (entities == null) return annotations;

        for (String typeName : entities) {
            Class<?> resultType = null;
            try {
                resultType = Class.forName(typeName);
            } catch (ClassNotFoundException e) {
                LOG.error(String.format("ClassNotFoundException %s", e
                        .getMessage()), e);
                continue;
            }
            LOG.info(String.format("resource found %s", resultType.getName()));
            annotations.add(resultType);
        }
        return annotations;
    }

    private static Map<String, Set<String>> scanAnnotation(List<URL> urls) {
        AnnotationDB db = new AnnotationDB();
        try {
            db.scanArchives(urls.toArray(new URL[urls.size()]));
        } catch (IOException e) {
            LOG.error(String.format("IOException %s", e.getMessage()), e);
            return null;
        }
        return db.getAnnotationIndex();
    }

    private static List<URL> useDefaultURLs(ServletContext context) {
        List<URL> urls = new ArrayList<URL>();
        try {

            URL classURl = WarUrlFinder.findWebInfClasses(context);
            if (classURl != null) {
                urls.add(classURl);
            }
            URL[] libURls = WarUrlFinder.findWebInfLib(context);
            if (libURls != null) {
                for (URL url : libURls) {
                    urls.add(url);
                }
            }
            URL[] classPaths = ClasspathUrlFinder.findClassPaths();
            if (classPaths != null) {
                for (URL url : classPaths) {
                    urls.add(url);
                }
            }

        } catch (Exception e) {
            LOG.error(String.format("find scan url failed: %s", e.getMessage()), e);
        }

        return urls;
    }

}
