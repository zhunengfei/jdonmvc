package com.jdon.mvc.core;

import com.jdon.mvc.http.RequestTargetInfo;
import com.jdon.mvc.rs.ResourceRequestInfo;

/**
 * 管理RESTFUL请求的资源
 *
 * @author oojdon
 */
public interface ResourceManager {

    void registerClass(Class<?> type);

    RequestTargetInfo translate(ResourceRequestInfo info);

}
