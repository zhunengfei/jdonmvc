package com.jdon.mvc.util;

import com.jdon.mvc.core.Env;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlHelper {

    private static final Log LOG = LogFactory.getLog(UrlHelper.class);

    public static String extractResourceUrl(HttpServletRequest httpReq) {
        String url = httpReq.getRequestURI().replaceFirst(
                "(?i);jsessionid=.*$", "");
        LOG.debug("---->the request URL is:" + url);

        String contextName = httpReq.getContextPath();
        url = url.replaceFirst(contextName, "");
        LOG.debug("---->trying to access the resource URL:" + url);

        return url;
    }

    public static boolean isStaticFileRequest(HttpServletRequest httpReq) throws MalformedURLException {
        String url = extractResourceUrl(httpReq);
        URL resource = Env.ctx().getResource(url);
        if (resource == null)
            return false;
        else if (resource.toString().endsWith("/")) {
            return false;
        } else {
            LOG.debug("will return the static file to browser,the resource is:" + resource.toString());
            return true;
        }

    }

}
