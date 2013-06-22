package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.core.ActionException;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.core.ResourceInterceptor;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.represent.RepresentationRenderException;
import com.jdon.mvc.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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

            List<ResourceInterceptor> resourceInterceptorList = context.fwContext().getResourceInterceptorList();
            RequestTargetInfo target = (RequestTargetInfo) context.flashMap().get(Constant.RESOURCE);

            for (ResourceInterceptor resourceInterceptor : resourceInterceptorList) {
                if (target.matchPattern(resourceInterceptor.getUrlMatchPattern()) && !resourceInterceptor.pre(target.getHandler())) {
                    return;
                }
            }

            Object represent = method.invoke(instance, args);

            for (ResourceInterceptor resourceInterceptor : resourceInterceptorList) {
                if (target.matchPattern(resourceInterceptor.getUrlMatchPattern())) {
                    resourceInterceptor.post(target.getHandler(), represent == null ? null : (Represent) represent);
                }
            }

            LOG.info("the flow-unit of " + MethodExecuteFlowUnit.class.getName() + " finish");
            String extension = context.requestTargetInfo().getResourceInfo()
                    .getExtension();

            if (represent == null)
                return;
            else if (represent instanceof Represent) {
                Represent r = (Represent) represent;
                try {
                    if (StringUtils.isEmpty(extension))
                        r.render(context.fwContext());
                    else
                        throw new RuntimeException(
                                "Now JdonMVC can not support url-based negotiation for the type:"
                                        + extension);
                } catch (RepresentationRenderException e) {
                    throw new RuntimeException(e);
                }
            } else

                throw new RuntimeException("Cannot handle result with type '"
                        + represent.getClass().getName() + "'.");
        } catch (IllegalArgumentException e) {
            throw new ActionException(e);
        } catch (IllegalAccessException e) {
            throw new ActionException(e);
        } catch (InvocationTargetException e) {
            throw new ActionException(e);
        }

    }

}
