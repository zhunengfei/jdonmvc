package com.jdon.mvc.core;

import com.jdon.mvc.converter.*;
import com.jdon.mvc.http.FormFile;
import com.jdon.mvc.rs.java.MethodParameter;
import com.jdon.mvc.rs.java.SettingException;
import com.jdon.mvc.util.ReflectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * used for converter the request parameter value to the correct type so the
 * type can match the java method.
 * <p/>
 * 方法参数目前支持简单的原生类型绑定,String,BigDecimal,BigInteger,FormFile也支持
 *
 * @author oojdon
 */
public class DefaultConverterManager implements ConverterManager {

    private final Log LOG = LogFactory.getLog(DefaultConverterManager.class);

    private Map<Class<?>, TypeConverter<?>> converterTypes = new HashMap<Class<?>, TypeConverter<?>>();

    public DefaultConverterManager() {

        // Primitive Data Types
        converterTypes.put(Boolean.class, new BooleanConverter());
        converterTypes.put(boolean.class, new BooleanConverter());

        converterTypes.put(Byte.class, new ByteConverter());
        converterTypes.put(byte.class, new ByteConverter());

        converterTypes.put(Short.class, new ShortConverter());
        converterTypes.put(short.class, new ShortConverter());

        converterTypes.put(Character.class, new CharacterConverter());
        converterTypes.put(char.class, new CharacterConverter());

        converterTypes.put(Integer.class, new IntegerConverter());
        converterTypes.put(int.class, new IntegerConverter());

        converterTypes.put(Long.class, new LongConverter());
        converterTypes.put(long.class, new LongConverter());

        converterTypes.put(Float.class, new FloatConverter());
        converterTypes.put(float.class, new FloatConverter());

        converterTypes.put(Double.class, new DoubleConverter());
        converterTypes.put(double.class, new DoubleConverter());
        // Primitive Data Types


        converterTypes.put(String.class, new StringConverter());
        converterTypes.put(BigDecimal.class, new BigDecimalConverter());
        converterTypes.put(BigInteger.class, new BigIntegerConverter());

        converterTypes.put(FormFile.class, new SingleFormFileConverter());
        converterTypes.put(Enum.class, new EnumConverter());

    }

    public Object[] convert(List<MethodParameter> methodParameters, Object[] args) throws BindingException {

        for (MethodParameter methodParameter : methodParameters) {

            Class<?> originalType = methodParameter.getType();// 形参类型
            Object value = methodParameter.getValue();// 形参值

            // 数组
            if (originalType.isArray()) {
                Class<?> type = originalType.getComponentType();
                Object array = Array.newInstance(type, FormValueHelper.arrayValue(value).length);
                for (int i = 0; i < FormValueHelper.arrayValue(value).length; i++) {
                    Array.set(array, i, convert(type, value));
                }
                args[methodParameter.getPosition()] = array;
            }

            //集合
            else if (Collection.class.isAssignableFrom(originalType)) {
                Class<?> targetType = extractTargetFromCollection(methodParameter.getGenericType());
                Collection converted = (Collection) ReflectionUtil.instantiateCollection(originalType);
                for (String item : FormValueHelper.arrayValue(value)) {
                    converted.add(convert(targetType, item));
                }
                args[methodParameter.getPosition()] = converted;
            }

            //单个对象，原生或者包装或者枚举或者FormFile
            else {
                args[methodParameter.getPosition()] = convert(originalType, value);
            }


        }

        return args;
    }

    private Class<?> extractTargetFromCollection(Type genericType) {
        if (genericType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericType;
            return (Class<?>) type.getActualTypeArguments()[0];
        } else {
            throw new SettingException("collection_not_generic,can not fill a non generic Collection: " + genericType);
        }
    }


    //map和对象的映射
    public void doPathMapping(MethodParameter methodParameter, String key, Object value) {
        if (methodParameter.getType() == Map.class) {
            if (methodParameter.getValue() != null) {
                Map map = (HashMap) methodParameter.getValue();
                map.put(key.split("\\.")[1], value);
            } else {
                Map map = new HashMap();
                map.put(key.split("\\.")[1], value);
                methodParameter.setValue(map);
            }
        } else {
            try {
                if (methodParameter.getValue() == null) {
                    Object instance = methodParameter.getType().newInstance();
                    methodParameter.setValue(instance);
                }
                Method method = ReflectionUtil.findSetter(methodParameter.getValue(), key.split("\\.")[1]);
                Class<?> setType = method.getParameterTypes()[0];
                try {
                    method.invoke(methodParameter.getValue(), convert(setType,value));
                } catch (InvocationTargetException e) {
                    throw new BindingException(e);
                }
            } catch (InstantiationException e) {
                throw new BindingException(e);
            } catch (IllegalAccessException e) {
                throw new BindingException(e);
            }
        }
    }


    public Object convert(Class<?> clazz, Object value) throws BindingException {

        try {

            //枚举类型手动判断
            if (clazz.isEnum()) {
                return converterTypes.get(Enum.class).convert(clazz, value);
            }

            if (converterTypes.get(clazz) != null) {
                TypeConverter<?> c = converterTypes.get(clazz);
                return c.convert(clazz, value);
            }

            return null;

        } catch (Exception e) {
            LOG.error("type convert occur exception", e);
            throw new BindingException(e);
        }
    }


}
