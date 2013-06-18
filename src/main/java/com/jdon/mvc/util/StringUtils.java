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

    /**
     * 首字母大写其余小写
     *
     * @param str
     */
    public static String upperFirstLowerOther(String str) {
        if (isEmpty(str))
            return str;
        StringBuilder sb = new StringBuilder();
        char c = str.charAt(0);
        sb.append(Character.toUpperCase(c));
        String other = str.substring(1);
        sb.append(other.toLowerCase());
        return sb.toString();
    }

    /**
     * html转义
     *
     * @param str
     * @return
     */
    public static String escapeHtml(String str) {
        if (null == str)
            return null;
        char[] cas = str.toString().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cas) {
            switch (c) {
                case '&':
                    sb.append("&amp;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '\'':
                    sb.append("&#x27;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

}
