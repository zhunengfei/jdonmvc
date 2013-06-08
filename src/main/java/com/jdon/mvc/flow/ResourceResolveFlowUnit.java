package com.jdon.mvc.flow;

import com.jdon.mvc.Constant;
import com.jdon.mvc.core.FlowContext;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.represent.RepresentationRenderException;
import com.jdon.mvc.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 资源表示的解析拦截器
 *
 * @author oojdon
 */

public class ResourceResolveFlowUnit implements FlowUnit {

    private final Log LOG = LogFactory.getLog(ResourceResolveFlowUnit.class);

    public void process(FlowContext context) {

        String extension = context.requestTargetInfo().getResourceInfo()
                .getExtension();

        Object represent = context.flashMap().get(
                Constant.RESULT_FOR_METHOD_EXECUTE);

        if (represent == null)
            return;
        else if (represent instanceof Represent) {
            Represent r = (Represent) represent;
            try {
                if (StringUtils.isEmpty(extension))
                    r.render(context.fwContext());
                else
                    throw new RuntimeException(
                            "Now JdonMVC can not support url-based negotiation for the type:"
                                    + extension);
            } catch (RepresentationRenderException e) {
                throw new RuntimeException(e);
            }
            LOG.info("the flow-unit of "
                    + ResourceResolveFlowUnit.class.getName() + " finish");
        } else

            throw new RuntimeException("Cannot handle result with type '"
                    + represent.getClass().getName() + "'.");

    }

}
