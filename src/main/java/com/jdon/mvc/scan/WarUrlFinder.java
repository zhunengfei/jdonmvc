package com.jdon.mvc.scan;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class WarUrlFinder {

    public static URL[] findWebInfLib(ServletContext servletContext) {
        ArrayList<URL> list = new ArrayList<URL>();
        Set libJars = servletContext.getResourcePaths("/WEB-INF/lib");
        for (Object jar : libJars) {
            try {
                list.add(servletContext.getResource((String) jar));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return list.toArray(new URL[list.size()]);
    }

    /**
     * Find the URL pointing to "/WEB-INF/classes"  This method may not work in conjunction with IteratorFactory
     * if your servlet container does not extract the /WEB-INF/classes into a real file-based directory
     *
     * @param servletContext
     * @return null if cannot determin /WEB-INF/classes
     */
    public static URL findWebInfClasses(ServletContext servletContext) {
        return findWebInfURL(servletContext,"/WEB-INF/classes");
    }


    /**
     * 在/WEB-INF下寻找URL
     * @param servletContext
     * @param path
     * @return
     */
    public static URL findWebInfURL(ServletContext servletContext, String path) {
        String realPath = servletContext.getRealPath(path);
        if (realPath == null) return null;
        File fp = new File(realPath);
        if (fp.exists() == false) return null;
        try {
            return fp.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
