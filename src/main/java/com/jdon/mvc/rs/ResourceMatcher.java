package com.jdon.mvc.rs;

import com.jdon.mvc.rs.java.JavaMethod;

import java.util.Map;

/**
 * 资源匹配器,每一次URL请求到达，框架会遍历所有的匹配器，这些匹配器在
 * 框架的初始化过程中被解析完毕。
 * 提取出能够处理这次请求的匹配器,一旦匹配，匹配器还要负责提取出
 * url中的键值对，比如/user{user.id}匹配到/user/2，就要提取出user.id=2
 * @author oojdon
 *
 */
public interface ResourceMatcher {
	
	boolean canHandle(String url, String verb);
		
	Map<String,String> extractPathParams(String url); //extract key value from the url ,then will be set in method name with form value.
	
	JavaMethod getJavaMethod();
	
}
