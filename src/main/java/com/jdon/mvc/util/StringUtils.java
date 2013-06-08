package com.jdon.mvc.util;

/**
 * User: Asion
 * Date: 13-6-7
 * Time: 上午11:47
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
}
