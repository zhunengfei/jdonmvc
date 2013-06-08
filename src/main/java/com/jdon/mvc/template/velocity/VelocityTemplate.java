package com.jdon.mvc.template.velocity;

import com.jdon.mvc.template.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.io.VelocityWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class VelocityTemplate implements Template {

    private org.apache.velocity.Template template;

    public VelocityTemplate(org.apache.velocity.Template template) {
        this.template = template;
    }

    public void render(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        Context context = new VelocityContext(model);
        VelocityWriter vw = new VelocityWriter(response.getWriter());
        try {
            template.merge(context, vw);
            vw.flush();
        } finally {
            vw.recycle(null);
        }
    }
}
