package com.jdon.mvc.core;

import com.jdon.mvc.template.TemplateManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

public class FrameWorkContext {

    private final static Log LOG = LogFactory.getLog(FrameWorkContext.class);

    private final ConverterManager converterManager;

    private final ResourceManager resourceManager;

    private final IocProvider beanProvider;

    private final TemplateManager templateManager;

    private final PluginManager pluginManager = new PluginManager();

    private  ExceptionResolver exceptionResolver;

    //框架配置
    private Properties props = new Properties();

    public FrameWorkContext(ConverterManager converterManager,
                            ResourceManager resourceManager, IocProvider beanProvider,
                            TemplateManager templateManager) {
        this.converterManager = converterManager;
        this.resourceManager = resourceManager;
        this.beanProvider = beanProvider;
        this.templateManager = templateManager;
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
}
