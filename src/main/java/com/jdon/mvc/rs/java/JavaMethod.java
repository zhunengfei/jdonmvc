package com.jdon.mvc.rs.java;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * represent the resource,our framework make the method as resource
 * so one url with http method will match one method.
 * 表示某次请求到来之后，对应的action处理方法(method)
 * @author oojdon
 *
 */
public class JavaMethod {

	private final Class<?> belongType;
	
	private final Method method;
	
	private List<MethodParameter> methodParameters = new ArrayList<MethodParameter>();
	
	private final Paranamer infoProvider = new CachingParanamer(
			new BytecodeReadingParanamer());
	

	public JavaMethod(Class<?> type, Method method) {
		this.belongType = type;
		this.method = method;
		
		Class<?>[] types = method.getParameterTypes();
		String[] parameterNames = infoProvider.lookupParameterNames(method);
		for (int i = 0; i < types.length; i++) {
			MethodParameter param = new MethodParameter(types[i],
					parameterNames[i], i);
			methodParameters.add(param);
		}
	}
	
	
	public List<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

	public Class<?> getBelongType() {
		return belongType;
	}

	public Method getMethod() {
		return method;
	}
	

	@Override
	public String toString() {
		return "[ResourceMethod: "
				+ method.getDeclaringClass().getSimpleName() + "."
				+ method.getName()+"]";
	}

}
