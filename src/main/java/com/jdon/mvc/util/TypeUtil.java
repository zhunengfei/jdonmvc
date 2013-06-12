package com.jdon.mvc.util;

/**
 * User: oojdon
 * Date: 13-6-12
 * Time: 下午11:05
 */
public class TypeUtil {

    public static boolean boolTrue(String str) {
        return str != null && Boolean.valueOf(str);
    }
}
