package com.jdon.mvc.util;

import com.jdon.mvc.config.ConfigException;

/**
 * User: Asion
 * Date: 13-6-5
 * Time: 下午1:04
 */
public class ClassUtil {


    public static Object instance(Class<?> classz) {

        try {
            return classz.newInstance();
        } catch (Exception e) {

            throw new ConfigException(e);
        }
    }

    /**
     * 得到类加载器
     *
     * @return
     */
    public static ClassLoader getLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtil.class.getClassLoader();
        }
        return cl;
    }
}
