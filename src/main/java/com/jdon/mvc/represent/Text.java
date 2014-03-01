package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.ComponentHolder;

import java.io.IOException;
import java.io.PrintWriter;


public class Text implements Represent {

    private static final String TYPE = MediaType.TEXT_PLAIN;

    private String text;

    public Text(String text) {
        this.text = text;
    }

    public Text create(String text) {
        return new Text(text);
    }

    @Override
    public void render(ComponentHolder holder) throws RepresentationRenderException {
        Env.res().setContentType(TYPE + ";charset=UTF-8");
        try {
            PrintWriter pw = Env.res().getWriter();
            pw.write(text);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            throw new RepresentationRenderException(
                    "can't render the resource to the formant of text", e);
        }
    }

}
