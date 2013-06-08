package com.jdon.mvc.engine;

import com.jdon.mvc.Constant;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.core.FrameWorkContext;
import com.jdon.mvc.http.RequestTargetInfo;

import java.util.HashMap;
import java.util.Map;


public class DefaultFlowContext implements FlowContext {

	private FrameWorkContext fc;

	private Map<String, Object> flashMap;

	public DefaultFlowContext(FrameWorkContext fc) {
		this.fc = fc;
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

	public FrameWorkContext fwContext() {
		return fc;
	}

	@Override
	public Map<String, Object> flashMap() {

		return flashMap;
	}

}
