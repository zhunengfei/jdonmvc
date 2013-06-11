package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.core.ActionException;
import com.jdon.mvc.core.FlowContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 该拦截器开始用反射API执行方法调用
 *
 * @author oojdon
 */

public class MethodExecuteFlowUnit implements FlowUnit {

    private final Log LOG = LogFactory.getLog(MethodExecuteFlowUnit.class);

    public void process(FlowContext context) throws ActionException {

        Method method = context.requestTargetInfo().getHandler().getMethod();

        Object[] args = (Object[]) context.flashMap().get(Constant.ARGS_FOR_METHOD);
        Object instance = context.javaObject();

        try {
            Object represent = method.invoke(instance, args);
            context.flashMap().put(Constant.RESULT_FOR_METHOD_EXECUTE, represent);
            LOG.info("the flow-unit of " + MethodExecuteFlowUnit.class.getName() + " finish");
        } catch (IllegalArgumentException e) {
            throw new ActionException(e);
        } catch (IllegalAccessException e) {
            throw new ActionException(e);
        } catch (InvocationTargetException e) {
            throw new ActionException(e);
        }

    }

}
