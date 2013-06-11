package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FrameWorkContext;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 重构为不依赖xstream todo
 */
public class Xml implements Represent {

    private static final String TYPE = MediaType.APPLICATION_XML;

    private final XStream stream;

    private Object o;

    public Xml(Object o) {
        stream = new XStream(new DomDriver()) {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {

                    @SuppressWarnings("unchecked")
                    @Override
                    public String serializedClass(Class type) {
                        String value = super.serializedClass(type);
                        if (type.getName().replace('$', '-').equals(value)) {
                            return type.getSimpleName();
                        }
                        return value;
                    }
                };
            }
        };
        stream.setMode(XStream.NO_REFERENCES);
        this.o = o;

    }

    @Override
    public void render(FrameWorkContext fc) throws RepresentationRenderException {
        Env.res().setContentType(TYPE);
        try {
            PrintWriter pw = Env.res().getWriter();
            pw.write(stream.toXML(o));
            pw.flush();
            pw.close();
        } catch (IOException e) {
            throw new RepresentationRenderException(
                    "can't render the resource to the formant of xml", e);
        }

    }

}
