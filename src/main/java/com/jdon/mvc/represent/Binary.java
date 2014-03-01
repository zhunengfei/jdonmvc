package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.ComponentHolder;

import java.io.IOException;
import java.io.OutputStream;

public class Binary implements Represent {

    private static final String TYPE = MediaType.APPLICATION_OCTET_STREAM;

    private byte[] data;

    public Binary(byte[] data) {
        this.data = data;
    }


    @Override
    public void render(ComponentHolder holder) throws RepresentationRenderException {
        Env.res().setContentType(TYPE);
        Env.res().setContentLength(data.length);
        try {
            OutputStream output = Env.res().getOutputStream();
            output.write(data);
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new RepresentationRenderException("can't render the resource to the formant of binary", e);
        }
    }

}
