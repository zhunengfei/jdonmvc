package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.core.ResourceManager;
import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.http.WebRequest;
import com.jdon.mvc.rs.ResourceRequestInfo;
import com.jdon.mvc.util.UrlHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 通过请求url去查找框架相对应的Java 方法
 *
 * @author oojdon
 */

public class ResourceLookupFlowUnit implements FlowUnit {

    private final Log LOG = LogFactory.getLog(ResourceLookupFlowUnit.class);

    private static final String OVERLOAD_POST = "_method";

    public void process(FlowContext context) {
        ResourceManager resourceManager = context.fwContext().getResourceManager();
        ResourceRequestInfo resourceRequestInfo = extractResourceRequestInfo(Env.req());
        RequestTargetInfo resourceInfo = resourceManager.translate(resourceRequestInfo);
        context.flashMap().put(Constant.RESOURCE, resourceInfo);
        LOG.info("the flow-unit of " + ResourceLookupFlowUnit.class.getName() + " finish task");
    }

    private ResourceRequestInfo extractResourceRequestInfo(WebRequest request) {

        String url = UrlHelper.extractResourceUrl(request);

        String httpVerb = getHttpVerb(request);

        int position = url.lastIndexOf(".");
        if (position != -1)
            return new ResourceRequestInfo(url.substring(0, position), url.substring(position + 1), httpVerb);
        else
            return new ResourceRequestInfo(url, null, httpVerb);

    }

    //获取对资源的操作动词
    private String getHttpVerb(WebRequest request) {
        String verb = request.getRequest().getParameter(OVERLOAD_POST);
        if (verb == null) {
            verb = request.getMethod();
        } else if ("GET".equalsIgnoreCase(request.getMethod())) {
            throw new IllegalArgumentException("You can't use " + OVERLOAD_POST + " parameter on a GET request. Use POST instead.");
        }
        verb = verb.toUpperCase();
        LOG.debug("---->the http method is:" + verb);
        return verb;
    }
}
