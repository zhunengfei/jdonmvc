package com.jdon.mvc;

import com.jdon.mvc.core.Dispatcher;
import com.jdon.mvc.core.Env;
import com.jdon.mvc.engine.DispatcherFactory;
import com.jdon.mvc.http.WebRequest;
import com.jdon.mvc.util.UrlHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * the core filter for this framework,so you must configure this filter in
 * web.xml 核心过滤器，必须在web.xml中配置，通过该过滤器进入Rest框架，拦截浏览器请求
 * <p/>
 * <pre>
 * 	<filter>
 *      <filter-name>RestFilter</filter-name>
 *      <filter-class>com.jdon.mvc.RestFilter</filter-class>
 *  </filter>
 *
 *  <filter-mapping>
 *      <filter-name>RestFilter</filter-name>
 *      <url-pattern>/*</url-pattern>
 *  </filter-mapping>
 * </pre>
 *
 * @author oojdon
 * @since 1.0
 */

public class RestFilter implements Filter {

    private final Log LOG = LogFactory.getLog(RestFilter.class);

    private AtomicBoolean initialized = new AtomicBoolean();

    private ServletContext servletContext;

    private Dispatcher dispatcher;


    @Override
    public void init(FilterConfig cfg) throws ServletException {
        if (initialized.compareAndSet(false, true)) {
            servletContext = cfg.getServletContext();
            this.dispatcher = DispatcherFactory.build(servletContext);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpReq = (HttpServletRequest) req;
        final HttpServletResponse httpResp = (HttpServletResponse) res;

        WebRequest request = new WebRequest(httpReq, dispatcher.getFc());
        Env.create(request, httpResp, servletContext);

        try {
            if (UrlHelper.isStaticFileRequest(httpReq)) {
                chain.doFilter(httpReq, httpResp);
            } else {
                dispatcher.dispatch();
            }
        } catch (IOException e) {
            throw e;
        } catch (ServletException e) {
            throw e;
        } finally {
            request.cleanupMultipart();
            Env.destroy();
        }
    }

    @Override
    public void destroy() {
        LOG.info("--->Destroy JdonMVC Framework...");
        dispatcher.destroy();
        dispatcher = null;
        servletContext = null;
    }

}
