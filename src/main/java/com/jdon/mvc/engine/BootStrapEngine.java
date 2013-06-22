package com.jdon.mvc.engine;

import com.jdon.container.access.xml.AppConfigureCollection;
import com.jdon.container.startup.ContainerSetupScript;
import com.jdon.controller.context.web.ServletContextWrapper;
import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.config.Scanner;
import com.jdon.mvc.core.*;
import com.jdon.mvc.ioc.JdonProvider;
import com.jdon.mvc.plugin.JdonMvcPlugin;
import com.jdon.mvc.template.TemplateManager;
import com.jdon.mvc.util.ClassUtil;
import com.jdon.mvc.util.ReflectionUtil;
import com.jdon.mvc.util.TypeUtil;
import com.jdon.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 框架的启动引擎
 *
 * @author oojdon
 */
public class BootStrapEngine {

    private final static Log LOG = LogFactory.getLog(BootStrapEngine.class);

    //是否启动Jdon容器，框架的默认配置是启动的
    private final static String INIT_JDON = "initJdon?";


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

        TemplateManager templateManager = new TemplateManager(servletContext);

        FrameWorkContext frameWorkContext = new FrameWorkContext(converterManager, resourceManager, templateManager, servletContext);

        Class<ExceptionResolver> exh = (Class<ExceptionResolver>) Scanner.scanExceptionHandlerClass(servletContext);
        if (exh != null) {
            frameWorkContext.setExceptionResolver((ExceptionResolver) ClassUtil.instance(exh));
        }

        List<Class<?>> joinList = Scanner.scanInterceptorClass(servletContext);
        for (Class<?> join : joinList) {
            frameWorkContext.addResourceInterceptor((ResourceInterceptor) ClassUtil.instance(join));
        }

        if (TypeUtil.boolTrue(props.getProperty(INIT_JDON))) {
            frameWorkContext.setBeanProvider(initJdonIoc(servletContext));
        }

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


    private JdonProvider initJdonIoc(ServletContext servletContext) {
        ServletContextWrapper context = new ServletContextWrapper(servletContext);
        final ContainerSetupScript css = new ContainerSetupScript();
        css.initialized(context);

        String app_configFile = context.getInitParameter(AppConfigureCollection.CONFIG_NAME);
        if (StringUtils.isEmpty(app_configFile)) {
            css.prepare("", context);
        } else {
            String[] configs = StringUtil.split(app_configFile, ",");
            for (int i = 0; i < configs.length; i++) {
                css.prepare(configs[i], context);
            }
        }

        return new JdonProvider(css);
    }

}
