package com.jdon.mvc.template;

import com.jdon.mvc.Constant;
import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.template.jsp.JspTemplateFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;

public class TemplateManager {

    private final static Log LOG = LogFactory.getLog(TemplateManager.class);

    private TemplateFactory templateFactoryLocator;

    public TemplateManager(final ServletContext servletContext) {
        String templateFactory = servletContext
                .getInitParameter(Constant.TEMPLATE_FACTORY);
        if (templateFactory == null)
            templateFactoryLocator = new JspTemplateFactory();
        else {
            try {
                templateFactoryLocator = (TemplateFactory) Class.forName(templateFactory).newInstance();
            }  catch (Exception e) {
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
