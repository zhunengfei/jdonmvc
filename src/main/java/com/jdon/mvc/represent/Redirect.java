package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.ComponentHolder;

import java.io.IOException;

/**
 * Author:oojdon
 */
public class Redirect implements Represent {

    private String url;

    public Redirect(String url) {
        this.url = url;
    }

    @Override
    public void render(ComponentHolder holder) throws RepresentationRenderException {

        try {
            Env.res().sendRedirect(Env.req().getContextPath() + url);
        } catch (IOException e) {
            throw new RepresentationRenderException(
                    "can't transfer the resource", e);
        }
    }
}
