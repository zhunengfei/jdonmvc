package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.ComponentHolder;
import com.jdon.mvc.template.TemplateFactory;
import com.jdon.mvc.util.TypeUtil;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * web状态机以超文本为迁移引擎
 * 资源根据客户端的请求类型自举发送
 *
 * @author oojdon
 */

public class Html implements Represent {

    //是否导入请求中设置的属性
    public static final String EXPORTREQUEST = "exportRequest?";

    //是否导入session中设置的属性
    public static final String EXPORTSESSION = "exportSession?";

    protected String path;

    protected Map<String, Object> model;


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

    public Html addModel(String key, Object model) {
        this.model.put(key, model);
        return this;
    }

    @Override
    public void render(ComponentHolder holder) throws RepresentationRenderException {
        try {
            TemplateFactory concreteTemplateFactory = holder.getTemplateFactory();
            if (path.lastIndexOf(".") == -1) {
                path = path + "." + concreteTemplateFactory.getSuffix();
            }

            if (TypeUtil.boolTrue(holder.getConfigItem(EXPORTREQUEST))) {
                for (Enumeration en = Env.req().getAttributeNames(); en.hasMoreElements(); ) {
                    String attribute = (String) en.nextElement();
                    Object attributeValue = Env.req().getAttribute(attribute);
                    model.put(attribute, attributeValue);
                }
            }

            if (TypeUtil.boolTrue(holder.getConfigItem(EXPORTSESSION))) {
                HttpSession session = Env.req().getSession(false);
                if (session != null) {
                    for (Enumeration en = session.getAttributeNames(); en.hasMoreElements(); ) {
                        String attribute = (String) en.nextElement();
                        Object attributeValue = session.getAttribute(attribute);
                        model.put(attribute, attributeValue);
                    }
                }
            }

            concreteTemplateFactory
                    .loadTemplate(path)
                    .render(Env.req(), Env.res(), model);
        } catch (Exception e) {
            throw new RepresentationRenderException("can't render the resource to the format of html", e);
        }
    }


}
