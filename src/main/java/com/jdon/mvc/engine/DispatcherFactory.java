package com.jdon.mvc.engine;

import com.jdon.mvc.core.Dispatcher;
import com.jdon.mvc.core.ComponentHolder;
import com.jdon.mvc.flow.FlowDispatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;


/**
 * 请求分派器的生产工厂，在这里开始了框架引擎的初始化
 *
 * @author oojdon
 */

public class DispatcherFactory {

    private final static Log LOG = LogFactory.getLog(DispatcherFactory.class);

    public static Dispatcher build(ServletContext servletContext) {
        LOG.info("JdonMVC bingin initializing ...");
        long start = System.currentTimeMillis();

        ComponentHolder holder = new BootStrapEngine().bootStrap(servletContext);

        long buildTime = System.currentTimeMillis() - start;
        LOG.info("JdonMVC init time " + buildTime + " ms");

        return new FlowDispatcher(holder);
    }


}
