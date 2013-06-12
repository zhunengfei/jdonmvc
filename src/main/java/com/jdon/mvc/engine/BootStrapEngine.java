package com.jdon.mvc.engine;

import com.jdon.mvc.Constant;
import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.config.Scanner;
import com.jdon.mvc.core.*;
import com.jdon.mvc.ioc.JdonProvider;
import com.jdon.mvc.ioc.SpringProvider;
import com.jdon.mvc.plugin.JdonMvcPlugin;
import com.jdon.mvc.template.TemplateManager;
import com.jdon.mvc.util.ClassUtil;
import com.jdon.mvc.util.ReflectionUtil;
import com.jdon.mvc.util.TypeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

/**
 * 框架的启动引擎
 *
 * @author oojdon
 */
public class BootStrapEngine {

    private final static Log LOG = LogFactory.getLog(BootStrapEngine.class);

    public static final String IOC_CONFIG = "useSpring?";


    public FrameWorkContext bootStrap(final ServletContext servletContext) {

        LOG.info("Begin initial core component of framework.");

        ResourceManager resourceManager = new DefaultResourceManager(servletContext);

        ConverterManager converterManager = new DefaultConverterManager();

        Properties props = new Properties();

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
                Properties customProps = new Properties();
                customProps.load(userConfig);
                props.putAll(customProps);
                userConfig.close();
            } else {
                LOG.warn("can't find mvc.properties,framework will use default config");
            }

        } catch (IOException e) {
            LOG.warn("parse framework config fail", e);
        }

        IocProvider beanProvider = new JdonProvider();
        if (TypeUtil.boolTrue(props.getProperty(IOC_CONFIG))) {
            beanProvider = new SpringProvider();
        }
        LOG.info("IOC container [" + beanProvider.getClass().getName()
                + "] init ok.");

        TemplateManager templateManager = new TemplateManager(servletContext);

        FrameWorkContext frameWorkContext = new FrameWorkContext(converterManager, resourceManager,
                beanProvider, templateManager,servletContext);

        frameWorkContext.setProps(props);

        LOG.info("begin scan plugin");

        List<Class<?>> plugins = Scanner.scanPluginClass(servletContext);
        PluginManager pluginManager = frameWorkContext.getPluginManager();

        for (Class<?> plugin : plugins) {
            JdonMvcPlugin plugInstance = (JdonMvcPlugin) ReflectionUtil.instantiate(plugin);
            pluginManager.register(plugInstance);
        }

        pluginManager.init(frameWorkContext);


        return frameWorkContext;

    }

}
