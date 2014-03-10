package com.jdon.mvc.flow;

import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.core.ActionException;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.http.MultiProcessor;
import com.jdon.mvc.util.ClassUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * web框架，围绕请求应答模型的一个处理流
 * 这个流用户可以介入定制,定制类配置在web.xml里就可以了
 *
 * @author oojdon
 */


public class RequestResponseFlow {
    private final Log LOG = LogFactory.getLog(RequestResponseFlow.class);


    protected List<FlowUnit> flows = new ArrayList<FlowUnit>();

    public void begin(FlowContext context) throws ActionException, BindingException {

        String multiProcessorClass = context.fwContext().getConfigItem("multiProcessor");
        if (multiProcessorClass != null) {
            MultiProcessor multiProcessor = (MultiProcessor) ClassUtil.instance(multiProcessorClass);
            multiProcessor.parse(context);
        } else {
            LOG.warn("can't find multi processor");
        }

        if (flows.size() == 0) {
            flows.add(new ResourceLookupFlowUnit());
            flows.add(new ParametersMappingFlowUnit());
            flows.add(new InstantiatedFlowUnit());
            flows.add(new MethodExecuteFlowUnit());

        }
        for (FlowUnit flow : flows)
            flow.process(context);
    }


}
