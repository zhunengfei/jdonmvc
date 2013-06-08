package com.jdon.mvc.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class FieldAnnotation<T extends Annotation> {

	private final T annotation;

	private final Field field;

	public FieldAnnotation(T annotation, Field field) {
		this.annotation = annotation;
		this.field = field;
	}

	
	public T getAnnotation() {
		return this.annotation;
	}


	public Field getField() {
		return this.field;
	}

}
