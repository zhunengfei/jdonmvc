package com.jdon.mvc.template.jsp;

import com.jdon.mvc.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class JspTemplate implements Template {

    private String path;

    public JspTemplate(String path) {
        this.path = path;
    }

    public void render(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception {
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

}
