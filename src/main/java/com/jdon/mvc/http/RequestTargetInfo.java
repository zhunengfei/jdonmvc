package com.jdon.mvc.http;

import com.jdon.mvc.rs.ResourceRequestInfo;
import com.jdon.mvc.rs.java.JavaMethod;

import java.util.Map;


/**
 * 请求到来，框架根据请求URI解析出来的一个对象
 * 这个对象包含对应这次请求所要调用的某个action方法和在URL里的查询参数
 *
 * @author oojdon
 */
public class RequestTargetInfo {

    private final JavaMethod jm;

    private final Map<String, String> pathParam;

    private final ResourceRequestInfo resourceInfo;

    public RequestTargetInfo(JavaMethod jm,
                             Map<String, String> pathParam, ResourceRequestInfo resourceInfo) {
        this.jm = jm;
        this.pathParam = pathParam;
        this.resourceInfo = resourceInfo;
    }

    public JavaMethod getJavaMethod() {
        return jm;
    }

    public Map<String, String> getPathParam() {
        return pathParam;
    }

    public ResourceRequestInfo getResourceInfo() {
        return resourceInfo;
    }


}
