package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FrameWorkContext;

import java.util.HashMap;
import java.util.Map;


/**
 * web状态机以超文本为迁移引擎
 * 资源根据客户端的请求类型自举发送
 *
 * @author oojdon
 */

public class Html implements Represent {

    protected String path;

    protected Map<String, Object> model;


    public Html() {
    }

    public Html(String path) {
        this.path = path;
        this.model = new HashMap<String, Object>();
    }

    public Html(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public Html(String path, String modelKey, Object modelValue) {
        this.path = path;
        this.model = new HashMap<String, Object>();
        this.model.put(modelKey, modelValue);
    }

    @Override
    public void render(FrameWorkContext fc) throws RepresentationRenderException {
        try {
            fc.getTemplateManager().getConcreteTemplateFactory()
                    .loadTemplate(path)
                    .render(Env.req(), Env.res(), model);
        } catch (Exception e) {
            throw new RepresentationRenderException("can't render the resource to the format of html", e);
        }
    }


}
