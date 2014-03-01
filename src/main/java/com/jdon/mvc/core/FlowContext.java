package com.jdon.mvc.core;

import com.jdon.mvc.http.RequestTargetInfo;

import java.util.Map;

/**
 * 流程处理单元可获得的上下文
 * 各种资源
 * @author oojdon
 *
 */
public interface FlowContext {
	
	RequestTargetInfo requestTargetInfo();
	
	Object javaObject();
	
	ComponentHolder fwContext();
	
	Map<String, Object> flashMap();
	
}
