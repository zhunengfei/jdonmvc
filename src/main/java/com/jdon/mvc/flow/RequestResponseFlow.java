package com.jdon.mvc.flow;

import com.jdon.mvc.core.ActionException;
import com.jdon.mvc.core.FlowContext;

import java.util.ArrayList;
import java.util.List;

/**
 * web框架，围绕请求应答模型的一个处理流
 * 这个流用户可以介入定制,定制类配置在web.xml里就可以了
 * @author oojdon
 *
 */


public class RequestResponseFlow {
	
	protected List<FlowUnit> flows = new ArrayList<FlowUnit>();
	
	public void begin(FlowContext context) throws ActionException {
		customFlow();
		if(flows.size() == 0) {
			flows.add(new FormFileFlowUnit());
			flows.add(new ResourceLookupFlowUnit());
			flows.add(new ParametersMappingFlowUnit());
			flows.add(new InstantiatedFlowUnit());
			flows.add(new MethodExecuteFlowUnit());
			flows.add(new ResourceResolveFlowUnit());		
			
		}
		for(FlowUnit flow : flows)
			flow.process(context);
	}
	
	//用户介入执行流，重写这个钩子
	protected void customFlow() {
		
	}
	

}
