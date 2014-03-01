package com.jdon.mvc.core;

import com.jdon.mvc.template.TemplateFactory;

import javax.servlet.ServletContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ComponentHolder {

    private ConverterManager converterManager;

    private ResourceManager resourceManager;

    private IocProvider beanProvider;

    private final PluginManager pluginManager = new PluginManager();

    private ExceptionResolver exceptionResolver;

    private final ServletContext servletContext;

    private List<ResourceInterceptor> resourceInterceptorList = new LinkedList<ResourceInterceptor>();

    private TemplateFactory templateFactory;

    //框架配置
    private Properties props = new Properties();

    public ComponentHolder(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void addResourceInterceptor(ResourceInterceptor interceptor) {
        this.resourceInterceptorList.add(interceptor);
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

    public void setConverterManager(ConverterManager converterManager) {
        this.converterManager = converterManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public TemplateFactory getTemplateFactory() {
        return templateFactory;
    }

    public void setTemplateFactory(TemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }
}
