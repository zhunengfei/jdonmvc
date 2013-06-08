package com.jdon.mvc.engine;

import com.jdon.mvc.Constant;
import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.core.ConverterManager;
import com.jdon.mvc.core.FrameWorkContext;
import com.jdon.mvc.core.IocProvider;
import com.jdon.mvc.core.ResourceManager;
import com.jdon.mvc.ioc.JdonProvider;
import com.jdon.mvc.template.TemplateManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.lang.reflect.InvocationTargetException;

/**
 * 框架的启动引擎
 *
 * @author oojdon
 */
public class BootStrapEngine {

    private final static Log LOG = LogFactory.getLog(BootStrapEngine.class);

    private ConverterManager converterManager;

    private ResourceManager resourceManager;

    private IocProvider beanProvider;

    private TemplateManager templateManager;

    public FrameWorkContext bootStrap(final ServletContext servletContext) {

        LOG.info("Begin initial core component of framework.");

        resourceManager = new DefaultResourceManager(servletContext);

        converterManager = new DefaultConverterManager(servletContext);

        String ioc = servletContext.getInitParameter(Constant.BEAN_PROVIDER);
        if (ioc == null)
            beanProvider = new JdonProvider();
        else {
            try {
                beanProvider = (IocProvider) Class.forName(ioc)
                        .getDeclaredConstructor().newInstance();
            } catch (InvocationTargetException e) {
                throw new ConfigException(e.getCause());
            } catch (Exception e) {
                throw new ConfigException(e);
            }
        }
        LOG.info("IOC container [" + beanProvider.getClass().getName()
                + "] init ok.");

        templateManager = new TemplateManager(servletContext);

        return new FrameWorkContext(converterManager, resourceManager,
                beanProvider, templateManager, null);

    }

}
