package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FrameWorkContext;

import java.io.IOException;
import java.io.OutputStream;


public class Image implements Represent {

    private byte[] data;
    private String type;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data,String type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public void render(FrameWorkContext fc) throws RepresentationRenderException {
    	Env.res().setContentType(type);
		long d = System.currentTimeMillis();
		Env.res().addDateHeader("Last-Modified", d);
		Env.res().addDateHeader("Expires", d + (5 * 24 * 60 * 60 * 1000));
		OutputStream toClient = null;
		try {
			toClient = Env.res().getOutputStream();
			toClient.write(data);
		} catch (IOException ex) {
			throw new RepresentationRenderException(ex);
		} finally {
			try {
				toClient.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepresentationRenderException(e);
			}
		}
    }

}
