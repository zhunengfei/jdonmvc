package com.jdon.mvc.template.velocity;

import com.jdon.mvc.template.Template;
import com.jdon.mvc.template.TemplateFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import javax.servlet.ServletContext;


public class VelocityTemplateFactory implements TemplateFactory {

    private final Log LOG = LogFactory.getLog(VelocityTemplateFactory.class);

    VelocityEngine ve = new VelocityEngine();

    public Template loadTemplate(String path) throws Exception {
        LOG.debug("Load Velocity template '" + path + "'.");
        return new VelocityTemplate(ve.getTemplate(path, "UTF-8"));
    }

    public void init(ServletContext servletContext) {
        LOG.info("begin to init VelocityTemplateFactory.");
        try {
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
            ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
            ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, servletContext.getRealPath("/"));
        } catch (Exception e) {
            LOG.error("fail to init VelocityTemplateFactory.", e);
        }
    }

    @Override
    public String getSuffix() {
        return "vm";
    }
}
