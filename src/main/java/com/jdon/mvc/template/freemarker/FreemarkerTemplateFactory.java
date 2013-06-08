package com.jdon.mvc.template.freemarker;

import com.jdon.mvc.template.Template;
import com.jdon.mvc.template.TemplateFactory;
import freemarker.template.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;

public class FreemarkerTemplateFactory implements TemplateFactory {

    private final Log log = LogFactory.getLog(FreemarkerTemplateFactory.class);

    private Configuration cfg;


    public void init(ServletContext servletContext) {
        log.info("begin init freemarker template.");
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(servletContext, "/");
        cfg.setDefaultEncoding("UTF-8");

    }

    public Template loadTemplate(String path) throws Exception {
        freemarker.template.Template t = cfg.getTemplate(path);
        t.setEncoding("UTF-8");
        return new FreemarkerTemplate(t);
    }

}
