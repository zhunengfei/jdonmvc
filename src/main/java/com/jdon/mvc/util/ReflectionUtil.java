package com.jdon.mvc.util;

import com.jdon.mvc.rs.java.SettingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;

public class ReflectionUtil {

	private final static Log log = LogFactory.getLog(ReflectionUtil.class);

	private static final String IS_INITIALS = "is";

	private static final String GET_INITIALS = "get";

	private static final String SET_INITIALS = "set";

    public static Object instantiateCollection(Type type) {
        Class<?> clazz = Class.class.cast(type);
        if (List.class.isAssignableFrom(clazz)) {
            return new ArrayList<Object>();
        }
        if (Set.class.isAssignableFrom(clazz)) {
            return new LinkedHashSet<Object>();
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return new HashMap<Object,Object>();
        }
        if (Collection.class.isAssignableFrom(clazz)) {
            return new ArrayList<Object>();
        }
        throw new SettingException("Unable to instantiate the desired collection");
    }
	
	public static <T extends Annotation> List<FieldAnnotation<T>> readAnnotations(Class<?> type, Class<T> annot) {
		List<FieldAnnotation<T>> list = new ArrayList<FieldAnnotation<T>>();
		for (Field f : type.getDeclaredFields()) {
			if (!f.isAnnotationPresent(annot)) {
				continue;
			}
			T annotation = f.getAnnotation(annot);

			log.debug("Adding field annotation on field " + f.getName() + "::" + annotation);
			
			list.add(new FieldAnnotation<T>(annotation, f));
			f.setAccessible(true);
		}
		return list;
	}
	
	public static Map<String, Method> getGetters(Class<?> clazz) {
		if (!Modifier.isPublic(clazz.getModifiers())) {
			throw new IllegalArgumentException("class not public " + clazz);
		}
		Map<String, Method> methods = new TreeMap<String, Method>();
		for (Method m : clazz.getMethods()) {
			if (!isGetter(m)) {
				continue;
			}
			if (m.getDeclaringClass().equals(Object.class)) {
				// hack: removing getClass()
				continue;
			}
			String propertyName = "";
			if (m.getName().startsWith(GET_INITIALS)) {
				propertyName = m.getName().substring(GET_INITIALS.length());

			} else if (m.getName().startsWith(IS_INITIALS)) {
				propertyName = m.getName().substring(IS_INITIALS.length());
			}
			// ok, this is a hack, cause we can have a problem
			// with classes with a get() method
			// (the propertyname would be an empty string)
			if (propertyName.length() != 0) {
				if (propertyName.length() == 1 || Character.isLowerCase(propertyName.charAt(1))) {
					propertyName = Introspector.decapitalize(propertyName);
				}
				methods.put(propertyName, m);
			}
		}
		return methods;
	}
	
	public static boolean isGetter(Method m) {
		if (m.getParameterTypes().length != 0 || !Modifier.isPublic(m.getModifiers())
				|| m.getReturnType().equals(Void.TYPE)) {
			return false;
		}
		if (Modifier.isStatic(m.getModifiers()) || !Modifier.isPublic(m.getModifiers())
				|| Modifier.isAbstract(m.getModifiers())) {
			return false;
		}
		if (m.getName().startsWith(GET_INITIALS) && m.getName().length() > GET_INITIALS.length()) {
			return true;
		}
		if (m.getName().startsWith(IS_INITIALS) && m.getName().length() > IS_INITIALS.length()
				&& (m.getReturnType().equals(boolean.class) || m.getReturnType().equals(Boolean.class))) {
			return true;
		}
		return false;
	}


}
