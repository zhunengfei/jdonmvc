package com.jdon.mvc.template;

import com.jdon.mvc.Constant;
import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.template.velocity.VelocityTemplateFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.lang.reflect.InvocationTargetException;

public class TemplateManager {
    private final static Log LOG = LogFactory.getLog(TemplateManager.class);

    private TemplateFactory templateFactoryLocator;

    public TemplateManager(final ServletContext servletContext) {
        String templateFactory = servletContext
                .getInitParameter(Constant.TEMPLATE_FACTORY);
        if (templateFactory == null)
            templateFactoryLocator = new VelocityTemplateFactory();
        else {
            try {
                templateFactoryLocator = (TemplateFactory) Class
                        .forName(templateFactory).getDeclaredConstructor()
                        .newInstance();
            } catch (InvocationTargetException e) {
                throw new ConfigException(e.getCause());
            } catch (Exception e) {
                throw new ConfigException(e);
            }
        }
        LOG.info("--->use template factory:["
                + templateFactoryLocator.getClass().getName() + "]");
        templateFactoryLocator.init(servletContext);

    }

    public TemplateFactory getConcreteTemplateFactory() {
        return templateFactoryLocator;
    }

}
