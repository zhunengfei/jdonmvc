package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FrameWorkContext;

import java.io.IOException;
import java.io.PrintWriter;


public class JavaScript implements Represent {

    private static final String TYPE = "application/x-javascript;charset=UTF-8";

    private String text;

    public JavaScript(String text) {
        this.text = text;
    }

    public void render(FrameWorkContext fc) throws RepresentationRenderException {
        Env.res().setContentType(TYPE);
        try {
            PrintWriter pw = Env.res().getWriter();
            pw.write(text);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            throw new RepresentationRenderException("can't render JavaScript to browser", e);
        }
    }

}
