package com.jdon.mvc.flow;

import com.jdon.controller.context.web.ServletContextWrapper;
import com.jdon.mvc.Constant;
import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.core.*;
import com.jdon.mvc.engine.DefaultFlowContext;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.ioc.JdonProvider;
import com.jdon.mvc.represent.RepresentationRenderException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;

/**
 * 流式分派
 *
 * @author oojdon
 */

public class FlowDispatcher implements Dispatcher {

    private final Log LOG = LogFactory.getLog(FlowDispatcher.class);

    private FrameWorkContext fc;

    public FlowDispatcher(FrameWorkContext fc) {
        this.fc = fc;
    }

    @Override
    public FrameWorkContext getFc() {
        return fc;
    }


    /**
     * 分派的时候可能发生两种错误
     * 一个是ActionException，一个是BindingException，前者是框架运行时错误，后者是处理数据绑定时发生的错误
     *
     * @throws ServletException
     */
    public void dispatch() throws ServletException {

        FlowContext ic = new DefaultFlowContext(fc);

        LOG.info(">>>Begin dispatch the request to JdonMVC's process flow");

        ExceptionResolver exceptionResolver = fc.getExceptionResolver();

        try {
            new RequestResponseFlow().begin(ic);
        } catch (ActionException e) {
            RequestTargetInfo target = (RequestTargetInfo) ic.flashMap().get(Constant.RESOURCE);
            if (exceptionResolver != null) {
                try {
                    exceptionResolver.resolveActionException(Env.req(), Env.res(), target.getHandler(), e).render(fc);
                } catch (RepresentationRenderException rrx) {
                    throw new ServletException(rrx);
                }
            } else {
                throw new ServletException(e);
            }
        } catch (BindingException e) {
            RequestTargetInfo target = (RequestTargetInfo) ic.flashMap().get(Constant.RESOURCE);
            if (exceptionResolver != null) {
                try {
                    exceptionResolver.resolveBindingException(Env.req(), Env.res(), target.getHandler(), e).render(fc);
                } catch (RepresentationRenderException rrx) {
                    throw new ServletException(rrx);
                }
            } else {
                throw new ServletException(e);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        LOG.info("<<<Finish process the request via JdonMVC's process flow");
    }

    public void destroy() {
        LOG.info("Destroy dispatcher,Destroy the framework context");
        IocProvider iocProvider = fc.getIocProvider();
        if (iocProvider != null && iocProvider instanceof JdonProvider) {
            ((JdonProvider) iocProvider).getCss().destroyed(new ServletContextWrapper(fc.getServletContext()));
        }
        fc.getPluginManager().dispose(fc.getServletContext());
        fc = null;
    }
}
