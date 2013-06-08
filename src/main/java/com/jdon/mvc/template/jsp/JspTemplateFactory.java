package com.jdon.mvc.template.jsp;

import com.jdon.mvc.template.Template;
import com.jdon.mvc.template.TemplateFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;


public class JspTemplateFactory implements TemplateFactory {

    private Log log = LogFactory.getLog(getClass());

    public Template loadTemplate(String path) throws Exception {
        if (log.isDebugEnabled())
            log.debug("Load JSP template '" + path + "'.");
        return new JspTemplate(path);
    }

    public void init(ServletContext servletContext) {
        log.info("JspTemplateFactory init ok.");
    }

}
