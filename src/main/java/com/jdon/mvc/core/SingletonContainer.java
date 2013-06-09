package com.jdon.mvc.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Asion
 * Date: 13-6-9
 * Time: 上午11:28
 */
public class SingletonContainer {

    private Map<Class<?>, Object> holder = new ConcurrentHashMap<Class<?>, Object>();


    public void register(Class<?> type, Object instance) {
        holder.put(type, instance);
    }

    public Object getInstance(Class<?> type) {
        return holder.get(type);
    }

}
