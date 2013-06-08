package com.jdon.mvc.flow;

import com.jdon.mvc.core.ActionException;
import com.jdon.mvc.core.FlowContext;

/**
 * 请求应答流中每个执行单元的行为抽象
 * @author oojdon
 *
 */

public interface FlowUnit {
	
	void process(FlowContext context) throws ActionException;

}
