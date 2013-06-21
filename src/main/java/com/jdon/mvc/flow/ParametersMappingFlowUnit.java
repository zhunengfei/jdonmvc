package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.http.ParameterKey;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.rs.java.Handler;
import com.jdon.mvc.rs.java.MethodParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
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
        for (Entry<String, String> parameter : pathParam.entrySet())
            Env.req().setParameter(parameter.getKey(), parameter.getValue());

        Object[] args = new Object[handler.getMethodParameters().size()];

        Map<MethodParameter, Map<String, Object>> methodValue = formKeyValueToJavaMethodParameter(Env.req().getParameterMap(), handler.getMethodParameters());

        ic.fwContext().getConverterManager().convert(methodValue, args);

        ic.flashMap().put(Constant.ARGS_FOR_METHOD, args);
        LOG.info("the flow-unit of " + ParametersMappingFlowUnit.class.getName() + " finish");
    }

    private Map<MethodParameter, Map<String, Object>> formKeyValueToJavaMethodParameter(
            Map<String, Object> parameterMap,
            List<MethodParameter> methodParameters) {
        List<ParameterKey> parameters = new ArrayList<ParameterKey>();
        for (String key : parameterMap.keySet())
            parameters.add(new ParameterKey(key));

        Map<MethodParameter, Map<String, Object>> methodValue = new HashMap<MethodParameter, Map<String, Object>>();

        for (MethodParameter methodParameter : methodParameters) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (ParameterKey parameter : parameters) {
                if (parameter.matches(methodParameter.getName())) {
                    map.put(parameter.getKey(), parameterMap.get(parameter
                            .getKey()));
                    methodValue.put(methodParameter, map);
                }
            }

        }

        return methodValue;
    }

}
