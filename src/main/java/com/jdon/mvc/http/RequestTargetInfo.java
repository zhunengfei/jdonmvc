package com.jdon.mvc.http;

import com.jdon.mvc.rs.ResourceRequestInfo;
import com.jdon.mvc.rs.java.Handler;

import java.util.Map;
import java.util.regex.Pattern;


/**
 * 请求到来，框架根据请求URI解析出来的一个对象
 * 这个对象包含对应这次请求所要调用的某个action方法和在URL里的查询参数
 *
 * @author oojdon
 */
public class RequestTargetInfo {

    private final Handler handler;

    private final Map<String, String> pathParam;

    private final ResourceRequestInfo resourceInfo;

    public RequestTargetInfo(Handler handler,
                             Map<String, String> pathParam, ResourceRequestInfo resourceInfo) {
        this.handler = handler;
        this.pathParam = pathParam;
        this.resourceInfo = resourceInfo;
    }

    public boolean matchPattern(Pattern pattern) {
        return pattern.matcher(resourceInfo.getUrl()).matches();
    }


    public Handler getHandler() {
        return handler;
    }

    public Map<String, String> getPathParam() {
        return pathParam;
    }

    public ResourceRequestInfo getResourceInfo() {
        return resourceInfo;
    }


}
