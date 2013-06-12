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

    public MethodParameter(Class<?> type, Type genericType, String name, int position) {
        this.type = type;
        this.genericType = genericType;
        this.name = name;
        this.position = position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodParameter that = (MethodParameter) o;

        if (position != that.position) return false;
        if (!genericType.equals(that.genericType)) return false;
        if (!name.equals(that.name)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + genericType.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + position;
        return result;
    }
}
