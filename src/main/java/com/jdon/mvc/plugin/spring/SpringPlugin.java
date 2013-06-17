package com.jdon.mvc.plugin.spring;

import com.jdon.mvc.annotations.Plugin;
import com.jdon.mvc.core.FrameWorkContext;
import com.jdon.mvc.plugin.JdonMvcPlugin;
import com.jdon.mvc.util.TypeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;

/**
 * User: Asion
 * Date: 13-6-17
 * Time: 下午4:42
 */
@Plugin
public class SpringPlugin implements JdonMvcPlugin {

    private final static Log LOG = LogFactory.getLog(SpringPlugin.class);

    public static final String IOC_CONFIG = "useSpring?";

    private static final String CONFIG_FILE_DEFAULT_VALUE = "classpath:SpringContext.xml";

    protected static final String SPRING_CONTEXT_KEY = SpringPlugin.class.getName() + ".ROOT";


    @Override
    public void init(FrameWorkContext fc) {
        if (TypeUtil.boolTrue(fc.getConfigItem(IOC_CONFIG))) {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG_FILE_DEFAULT_VALUE);
            fc.getServletContext().setAttribute(SPRING_CONTEXT_KEY, applicationContext);
            LOG.info("will replace the default IOC container with spring");
            fc.setBeanProvider(new SpringProvider());
        }
    }

    @Override
    public void dispose(ServletContext servletContext) {
        servletContext.removeAttribute(SPRING_CONTEXT_KEY);
    }
}
