package com.jdon.mvc.scan;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Various functions to locate URLs to scan
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class ClasspathUrlFinder {

    /**
     * Find the classpath URLs for a specific classpath resource.  The classpath URL is extracted
     * from loader.getResources() using the baseResource.
     *
     * @param baseResource
     * @return
     */
    public static URL[] findResourceBases(String baseResource) {
        ClassLoader loader = ClassUtils.getDefaultClassLoader();
        ArrayList<URL> list = new ArrayList<URL>();
        try {
            Enumeration<URL> urls = loader.getResources(baseResource);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                list.add(findResourceBase(url, baseResource));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(new URL[list.size()]);
    }

    private static URL findResourceBase(URL url, String baseResource) {
        String urlString = url.toString();
        int idx = urlString.lastIndexOf(baseResource);
        urlString = urlString.substring(0, idx);
        URL deployUrl = null;
        try {
            deployUrl = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return deployUrl;
    }

    /**
     * Find the classpath URL for a specific classpath resource.  The classpath URL is extracted
     * from Thread.currentThread().getContextClassLoader().getResource() using the baseResource.
     *
     * @param baseResource
     * @return
     */
    public static URL findResourceBase(String baseResource) {
        ClassLoader loader = ClassUtils.getDefaultClassLoader();
        URL url = loader.getResource(baseResource);
        return findResourceBase(url, baseResource);
    }


    /**
     * Find the classpath for the particular class
     *
     * @param clazz
     * @return
     */
    public static URL findClassBase(Class clazz) {
        String resource = clazz.getName().replace('.', '/') + ".class";
        return findResourceBase(resource);
    }

    /**
     * Uses the java.class.path system property to obtain a list of URLs that represent the CLASSPATH
     *
     * @return
     */
    public static URL[] findClassPaths() {
        List<URL> list = new ArrayList<URL>();
        String classpath = System.getProperty("java.class.path");
        StringTokenizer tokenizer = new StringTokenizer(classpath, File.pathSeparator);

        while (tokenizer.hasMoreTokens()) {
            String path = tokenizer.nextToken();
            File fp = new File(path);
            if (!fp.exists()) throw new RuntimeException("File in java.class.path does not exist: " + fp);
            try {
                list.add(fp.toURI().toURL());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return list.toArray(new URL[list.size()]);
    }


}

