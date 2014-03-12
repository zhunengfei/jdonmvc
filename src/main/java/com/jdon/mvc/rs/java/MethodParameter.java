package com.jdon.mvc.rs.java;

import java.lang.reflect.Type;

/**
 * represent one method parameter,contains name,type and position
 * used for invoking the method.
 * 表示方法的一个形参
 *
 * @author oojdon
 */
public class MethodParameter {

    /**
     * 参数类型
     */
    private final Class<?> type;

    /**
     * 范型类型
     */
    private final Type genericType;

    /**
     * 参数名字
     */
    private final String name;

    /**
     * 参数位置
     */
    private final int position;

    //参数值
    private Object value;

    public MethodParameter(Class<?> type, Type genericType, String name, int position) {
        this.type = type;
        this.genericType = genericType;
        this.name = name;
        this.position = position;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Type getGenericType() {
        return genericType;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
