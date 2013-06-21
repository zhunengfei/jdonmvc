package com.jdon.mvc.core;

import com.jdon.mvc.template.TemplateManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class FrameWorkContext {

    private final static Log LOG = LogFactory.getLog(FrameWorkContext.class);

    private final ConverterManager converterManager;

    private final ResourceManager resourceManager;

    private IocProvider beanProvider;

    private final TemplateManager templateManager;

    private final PluginManager pluginManager = new PluginManager();

    private ExceptionResolver exceptionResolver;

    private final ServletContext servletContext;

    private List<ResourceInterceptor> resourceInterceptorList = new LinkedList<ResourceInterceptor>();

    //框架配置
    private Properties props = new Properties();


    public void addResourceInterceptor(ResourceInterceptor interceptor) {
        this.resourceInterceptorList.add(interceptor);
    }

    public FrameWorkContext(ConverterManager converterManager,
                            ResourceManager resourceManager, TemplateManager templateManager, ServletContext servletContext) {
        this.converterManager = converterManager;
        this.resourceManager = resourceManager;
        this.templateManager = templateManager;
        this.servletContext = servletContext;
    }

    public ConverterManager getConverterManager() {
        return converterManager;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public IocProvider getIocProvider() {
        return beanProvider;
    }

    public void setBeanProvider(IocProvider beanProvider) {
        this.beanProvider = beanProvider;
    }

    public TemplateManager getTemplateManager() {
        return templateManager;
    }

    public ExceptionResolver getExceptionResolver() {
        return exceptionResolver;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public String getConfigItem(String key) {
        return props.getProperty(key);
    }

    public void setExceptionResolver(ExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public List<ResourceInterceptor> getResourceInterceptorList() {
        return resourceInterceptorList;
    }
}
