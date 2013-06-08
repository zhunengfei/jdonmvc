package com.jdon.mvc.engine;

import com.jdon.mvc.annotations.Convert;
import com.jdon.mvc.config.Scanner;
import com.jdon.mvc.converter.FormValueHelper;
import com.jdon.mvc.converter.TypeConverter;
import com.jdon.mvc.core.ConverterManager;
import com.jdon.mvc.rs.java.MethodParameter;
import com.jdon.mvc.rs.java.SettingException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * used for converter the request parameter value to the correct type so the
 * type can match the java method.
 *
 * @author oojdon
 */
public class DefaultConverterManager implements ConverterManager {

    private final Log LOG = LogFactory.getLog(DefaultConverterManager.class);

    private Map<Class<?>, TypeConverter<?>> converterTypes = new HashMap<Class<?>, TypeConverter<?>>();

    public DefaultConverterManager(final ServletContext servletContext) {
        List<Class<?>> converts = Scanner.scanConvertClass(servletContext);
        LOG.debug("finish scan the convert class,the size is:" + converts.size());
        for (Class<?> clazz : converts) {
            Class<?>[] toTypes = clazz.getAnnotation(Convert.class).value();
            for (Class<?> type : toTypes)
                try {
                    converterTypes.put(type, (TypeConverter<?>) clazz
                            .newInstance());
                } catch (InstantiationException e) {
                    LOG.error("can't instance the converter of:"
                            + clazz.getName(), e);
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    LOG.error("can't instance the converter of:"
                            + clazz.getName(), e);
                    e.printStackTrace();
                }
        }
    }

    public Object[] convert(Map<MethodParameter, Map<String, Object>> methodValue, Object[] args) {

        for (Map.Entry<MethodParameter, Map<String, Object>> entry : methodValue.entrySet()) {

            Map<String, Object> map = entry.getValue();// 形参对应的参数
            MethodParameter methodParameter = entry.getKey();// 形参

            Class<?> originalType = methodParameter.getType();// 形参类型
            Object value = map.get(methodParameter.getName());// 形参值

            // 数组的class判断比较特殊，所以就特殊处理
            if (originalType.isArray())
                args[methodParameter.getPosition()] = convertArray(originalType, value, map);
            else
                args[methodParameter.getPosition()] = convert(
                        originalType, value, map);

        }

        return args;
    }

    private Object convert(Class<?> clazz, Object value, Map<String, Object> map) {
        if (converterTypes.get(clazz) != null) {
            TypeConverter<?> c = (TypeConverter<?>) converterTypes.get(clazz);
            return c.convert(value);
        } else {
            return convertObject(clazz, map);
        }
    }

    private Object convertArray(Class<?> clazz, Object value,
                                Map<String, Object> map) {
        Class<?> type = clazz.getComponentType();
        Object converted = Array.newInstance(type, FormValueHelper.arrayValue(value).length);
        for (int i = 0; i < FormValueHelper.arrayValue(value).length; i++) {
            Array.set(converted, i, convert(type, value, map));
        }
        return converted;
    }

    private Object convertObject(Class<?> clazz, Map<String, Object> map) {
        Object instance;
        try {
            instance = clazz.newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                String key = entry.getKey();
                String property = key.substring(key.indexOf(".") + 1);
                BeanUtils.setProperty(instance, property, value);
            }
        } catch (InstantiationException e) {
            LOG.error("can't instance the method parameter" + clazz.getClass()
                    + e);
            throw new SettingException(e);
        } catch (IllegalAccessException e) {
            LOG.error("can't instance the method parameter" + clazz.getClass()
                    + e);
            throw new SettingException(e);
        } catch (InvocationTargetException e) {
            LOG.error("can't instance the method parameter" + clazz.getClass()
                    + e);
            throw new SettingException(e);

        }
        return instance;
    }

    public Object convertPrimitive(Class<?> clazz, String s) {
        TypeConverter<?> c = converterTypes.get(clazz);
        return c.convert(s);
    }


}
