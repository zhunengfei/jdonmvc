package com.jdon.mvc.template.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

/**
 * 集成Freemarker
 * @author oojdon
 *
 */
public class FreemarkerTemplate implements com.jdon.mvc.template.Template {

	private Template t;

	public FreemarkerTemplate(Template temper) {
		t = temper;
	}

	public void render(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model)
			throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		Writer out = response.getWriter();
		try {
			t.process(model, out);
		} catch (TemplateException e) {
			throw new ServletException(
					"Error while processing FreeMarker template", e);
		}

	}

}
