package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.rs.java.Handler;
import com.jdon.mvc.rs.java.MethodParameter;
import com.jdon.mvc.util.ReflectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 表单的参数，或者从url中捕获的键值对向 方法参数的映射转换拦截器
 *
 * @author oojdon
 */

public class ParametersMappingFlowUnit implements FlowUnit {

    private final Log LOG = LogFactory.getLog(ParametersMappingFlowUnit.class);

    public void process(FlowContext ic) throws BindingException {
        RequestTargetInfo target = ic.requestTargetInfo();
        Handler handler = target.getHandler();

        Map<String, String> pathParam = target.getPathParam();
        for (Entry<String, String> parameter : pathParam.entrySet()) {
            Env.req().setParameter(parameter.getKey(), parameter.getValue());
        }

        Object[] args = new Object[handler.getMethodParameters().size()];

        List<MethodParameter> methodParameters = handler.getMethodParameters();

        Map<String, MethodParameter> methodParameterMap = new HashMap<String, MethodParameter>();
        for (MethodParameter methodParameter : methodParameters) {
            methodParameterMap.put(methodParameter.getName(), methodParameter);
        }

        Map<String, Object> parameterMap = Env.req().getParameterMap();

        for (MethodParameter methodParameter : methodParameters) {
            methodParameter.setValue(parameterMap.get(methodParameter.getName()));
        }

        ic.fwContext().getConverterManager().convert(methodParameters, args);

        //如果参数中有字符.的key表示要映射到Map或者对象中，只处理两级
        for (String key : parameterMap.keySet()) {
            if (key.indexOf(".") != -1) {
                String pName = key.split("\\.")[0];
                MethodParameter methodParameter = methodParameterMap.get(pName);
                if (methodParameter != null) {
                    ic.fwContext().getConverterManager().doPathMapping(methodParameter, key, parameterMap.get(key));
                    args[methodParameter.getPosition()] = methodParameter.getValue();
                }
            }
        }

        ic.flashMap().put(Constant.ARGS_FOR_METHOD, args);

        LOG.info("the flow-unit of " + ParametersMappingFlowUnit.class.getName() + " finish");
    }





}
