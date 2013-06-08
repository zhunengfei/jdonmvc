package com.jdon.mvc.rs.java;

/**
 * represent one method parameter,contains name,type and position
 * used for invoking the method.
 * 表示方法的一个形参
 * @author oojdon
 *
 */
public class MethodParameter {
	
	private final Class<?> type;
	private final String name;
	private final int position;
	
	public MethodParameter(Class<?> type, String name, int position) {
		this.type = type;
		this.name = name;
		this.position = position;
	}
	
	public Class<?> getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getPosition() {
		return position;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + position;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MethodParameter other = (MethodParameter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position != other.position)
			return false;
		return true;
	}

}
