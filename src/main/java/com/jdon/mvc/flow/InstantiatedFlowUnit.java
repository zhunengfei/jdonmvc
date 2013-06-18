package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.annotations.In;
import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.core.IocProvider;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.ioc.BeanType;
import com.jdon.mvc.rs.java.SettingException;
import com.jdon.mvc.util.FieldAnnotation;
import com.jdon.mvc.util.ReflectionUtil;
import com.jdon.mvc.validation.Validation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.List;


/**
 * Method 反射 Invoke的目标对象实例化拦截器
 * 在这里注入@Context所要求的上下文对象
 *
 * @author oojdon
 */

public class InstantiatedFlowUnit implements FlowUnit {

    private final Log LOG = LogFactory.getLog(InstantiatedFlowUnit.class);

    public void process(FlowContext context) {
        RequestTargetInfo resource = context.requestTargetInfo();

        Class<?> type = resource.getHandler().getBelongType();

        List<FieldAnnotation<In>> services = ReflectionUtil
                .readAnnotations(type, In.class);
        Object instance;
        try {
            instance = type.newInstance();
            for (FieldAnnotation<In> annotation : services) {
                Field f = annotation.getField();
                f.setAccessible(true);

                if (f.getType() == HttpServletRequest.class) {
                    f.set(instance, Env.req().getRequest());
                } else if (f.getType() == HttpServletResponse.class) {
                    f.set(instance, Env.res());
                } else if (f.getType() == HttpSession.class) {
                    f.set(instance, Env.req().getSession());
                } else if (f.getType() == ServletContext.class) {
                    f.set(instance, Env.ctx());
                } else if (f.getType() == Validation.class) {
                    f.set(instance, new Validation(Env.req()));
                } else {
                    String service = annotation.getAnnotation().value();
                    BeanType beanType = annotation.getAnnotation().type();

                    IocProvider beanProvider = context.fwContext().getIocProvider();

                    if (beanProvider != null) {
                        if (!service.equals(""))
                            f.set(instance, beanProvider.getBean(service, beanType, Env.ctx()));
                        else
                            f.set(instance, beanProvider.getBean(f.getName(), beanType, Env.ctx()));
                    }

                }
            }
        } catch (SecurityException e) {
            throw new SettingException(
                    "Unable to set field annotation by @In:"
                            + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new SettingException(
                    "Unable to set field annotation by @In:"
                            + e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new RuntimeException(
                    "Unable to instantiation your restful Java class,please make default constructor for:"
                            + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new SettingException(
                    "Unable to set field annotation by @In:"
                            + e.getMessage(), e);
        }

        context.flashMap().put(Constant.RESOURCE_INSTANCE, instance);
        LOG.info("the flow-unit of " + InstantiatedFlowUnit.class.getName() + " finish");

    }

}
