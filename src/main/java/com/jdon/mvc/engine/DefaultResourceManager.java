package com.jdon.mvc.engine;

import com.jdon.mvc.config.Scanner;
import com.jdon.mvc.core.ResourceManager;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.DefaultResourceMatcher;
import com.jdon.mvc.rs.InvalidResourceException;
import com.jdon.mvc.rs.ResourceMatcher;
import com.jdon.mvc.rs.ResourceRequestInfo;
import com.jdon.mvc.rs.java.Handler;
import com.jdon.mvc.rs.method.Path;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultResourceManager implements ResourceManager {

    private final Log LOG = LogFactory.getLog(getClass());

    private List<ResourceMatcher> matcherList = new ArrayList<ResourceMatcher>();

    private List<String> keys = new ArrayList<String>();


    public DefaultResourceManager(final ServletContext servletContext) {
        List<Class<?>> resourceTypes = Scanner.scanRestfulClass(servletContext);
        LOG.debug("finish scan the restful class,the size is:" + resourceTypes.size());
        for (Class<?> resourceType : resourceTypes) {
            parseClass(resourceType);
        }
    }


    @Override
    public void registerClass(Class<?> type) {
        parseClass(type);
    }

    private void parseClass(Class<?> resourceType) {
        Method[] ms = resourceType.getMethods();
        for (Method m : ms) {
            if (validateMethod(m)) {
                Handler handler = new Handler(resourceType, m);
                ResourceMatcher matcher = new DefaultResourceMatcher(handler, keys);
                matcherList.add(matcher);
                LOG.debug(matcher.toString());
            }
        }
    }

    @Override
    public RequestTargetInfo translate(ResourceRequestInfo requestInfo) {
        Handler handler = null;
        Map<String, String> pathParam = null;

        String url = requestInfo.getUrl();
        String verb = requestInfo.getVerb();

        for (ResourceMatcher matcher : this.matcherList) {
            if (matcher.canHandle(url, verb)) {
                pathParam = matcher.extractPathParams(url);
                handler = matcher.getHandler();
                break;
            }
        }

        if (handler == null)
            throw new InvalidResourceException("can't find resource for:["
                    + url + "] by http verb[" + verb
                    + "]please check your resource design!");

        return new RequestTargetInfo(handler, pathParam, requestInfo);
    }


    private boolean validateMethod(Method m) {
        Path mapping = m.getAnnotation(Path.class);
        if (mapping == null)
            return false;
        if (mapping.value().length() == 0) {
            return false;
        }
        if (Modifier.isStatic(m.getModifiers())) {
            return false;
        }
        Class<?> retType = m.getReturnType();
        if (retType.equals(void.class)
                || Represent.class.isAssignableFrom(retType))
            return true;
        return false;
    }
}
