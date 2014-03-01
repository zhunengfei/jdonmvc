package com.jdon.mvc.engine;

import com.jdon.mvc.Constant;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.core.ComponentHolder;
import com.jdon.mvc.http.RequestTargetInfo;

import java.util.HashMap;
import java.util.Map;


public class DefaultFlowContext implements FlowContext {

	private ComponentHolder holder;

	private Map<String, Object> flashMap;

	public DefaultFlowContext(ComponentHolder holder) {
		this.holder = holder;
		this.flashMap = new HashMap<String, Object>();
	}


	public RequestTargetInfo requestTargetInfo() {

		Object obj = flashMap.get(Constant.RESOURCE);

		if (obj instanceof RequestTargetInfo)
			return (RequestTargetInfo) obj;

		return null;
	}

	public Object javaObject() {

		return flashMap.get(Constant.RESOURCE_INSTANCE);
	}

	public ComponentHolder fwContext() {
		return holder;
	}

	@Override
	public Map<String, Object> flashMap() {

		return flashMap;
	}

}
