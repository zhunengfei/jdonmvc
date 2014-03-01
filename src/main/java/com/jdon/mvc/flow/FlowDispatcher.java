package com.jdon.mvc.flow;

import com.jdon.controller.context.web.ServletContextWrapper;
import com.jdon.mvc.Constant;
import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.core.*;
import com.jdon.mvc.engine.DefaultFlowContext;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.ioc.JdonProvider;
import com.jdon.mvc.represent.Represent;
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

    private ComponentHolder holder;

    public FlowDispatcher(ComponentHolder holder) {
        this.holder = holder;
    }

    @Override
    public ComponentHolder getFc() {
        return holder;
    }


    /**
     * 分派的时候可能发生两种错误
     * 一个是ActionException，一个是BindingException，前者是框架运行时错误，后者是处理数据绑定时发生的错误
     *
     * @throws ServletException
     */
    public void dispatch() throws ServletException {

        FlowContext ic = new DefaultFlowContext(holder);

        LOG.info(">>>Begin dispatch the request to JdonMVC's process flow");

        ExceptionResolver exceptionResolver = holder.getExceptionResolver();

        try {
            new RequestResponseFlow().begin(ic);
        } catch (ActionException e) {
            RequestTargetInfo target = (RequestTargetInfo) ic.flashMap().get(Constant.RESOURCE);
            if (exceptionResolver != null) {
                try {
                    Represent represent = exceptionResolver.resolveActionException(Env.req(), Env.res(), target.getHandler(), e);
                    if (represent != null) {
                        represent.render(holder);
                    }
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
                    Represent represent = exceptionResolver.resolveBindingException(Env.req(), Env.res(), target.getHandler(), e);
                    if (represent != null) {
                        represent.render(holder);
                    }
                } catch (RepresentationRenderException rrx) {
                    throw new ServletException(rrx);
                }
            } else {
                throw new ServletException(e);
            }
        } catch (MaxUploadSizeException e) {
            RequestTargetInfo target = (RequestTargetInfo) ic.flashMap().get(Constant.RESOURCE);
            if (exceptionResolver != null) {
                try {
                    Represent represent = exceptionResolver.resolveUploadException(Env.req(), Env.res(), target.getHandler(), e);
                    if (represent != null) {
                        represent.render(holder);
                    }
                } catch (RepresentationRenderException rrx) {
                    throw new ServletException(rrx);
                }
            } else {
                throw new ServletException(e);
            }
        }catch (Exception e) {
            throw new ServletException(e);
        }

        LOG.info("<<<Finish process the request via JdonMVC's process flow");
    }

    public void destroy() {
        LOG.info("Destroy dispatcher,Destroy the framework context");
        IocProvider iocProvider = holder.getIocProvider();
        if (iocProvider != null && iocProvider instanceof JdonProvider) {
            ((JdonProvider) iocProvider).getCss().destroyed(new ServletContextWrapper(holder.getServletContext()));
        }
        holder.getPluginManager().dispose(holder.getServletContext());
        holder = null;
    }
}
