package com.jdon.mvc.core;

import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.config.Scanner;
import com.jdon.mvc.template.TemplateManager;
import com.jdon.mvc.util.ClassUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameWorkContext {

    private final static Log LOG = LogFactory.getLog(FrameWorkContext.class);

    private final ConverterManager converterManager;

    private final ResourceManager resourceManager;

    private final IocProvider beanProvider;

    private final TemplateManager templateManager;

    private final ExceptionResolver exceptionResolver;

    //框架配置
    private Properties props = new Properties();

    public FrameWorkContext(ConverterManager converterManager,
                            ResourceManager resourceManager, IocProvider beanProvider,
                            TemplateManager templateManager, ExceptionResolver exceptionResolver) {
        this.converterManager = converterManager;
        this.resourceManager = resourceManager;
        this.beanProvider = beanProvider;
        this.templateManager = templateManager;
        this.exceptionResolver = exceptionResolver;

        try {
            InputStream defaultConfig = ClassUtil.getLoader().getResourceAsStream("com/jdon/mvc/config/DefaultConfig.properties");
            try {
                if (defaultConfig != null) {
                    props.load(defaultConfig);
                } else {
                    throw new ConfigException("can not find the default config");
                }
            } finally {
                defaultConfig.close();
            }

            //如果发现用户有配置就覆盖默认配置
            InputStream userConfig = ClassUtil.getLoader().getResourceAsStream("mvc.properties");
            if (userConfig != null) {
                Properties userprops = new Properties();
                userprops.load(userConfig);
                props.putAll(userprops);
                userConfig.close();
            } else {
                LOG.warn("can't find mvc.properties,framework will use default config");
            }

        } catch (IOException e) {
            LOG.warn("parse framework config fail", e);
        }
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

    public String getConfigItem(String key) {
        return props.getProperty(key);
    }


}
