package com.jdon.mvc.http;

/**
 * 表示一个表单键值对的key
 * 比如user.name=oojdon,name=oojdon,man=on中的user.name,name,man
 * 根据path数组可以判断这个key对应的value是否是要set到一个对象的字段中去
 * @author oojdon
 *
 */
public class ParameterKey {
	
	private final String key;

	private final String[] path;

	public ParameterKey(final String key) {
		this.key = key;
		this.path = key.split("[\\.\\[\\]]+");
	}

	public String getKey() {
		return key;
	}

	public String[] getPath() {
		return path;
	}
	
	public boolean matches(final String key) {
		return getPath()[0].equals(key);
	}

}
