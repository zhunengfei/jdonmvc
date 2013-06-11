package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.FrameWorkContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.PrintWriter;

/**
 * 采用jaxb api，需要转换的对象有jaxb注解
 * <p/>
 * 你可以自己实现自己的用一些开源类库，比如xstram
 */
public class Xml implements Represent {

    private static final String TYPE = MediaType.APPLICATION_XML;

    private Object marshalObject;

    private Class<?> type;

    public Xml(Object o, Class<?> type) {

        this.marshalObject = o;
        this.type = type;

    }

    @Override
    public void render(FrameWorkContext fc) throws RepresentationRenderException {
        Env.res().setContentType(TYPE);
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            //下面代码演示将对象转变为xml
            Marshaller marshaller = context.createMarshaller();

            PrintWriter pw = Env.res().getWriter();

            marshaller.marshal(marshalObject, pw);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            throw new RepresentationRenderException(
                    "can't render the resource to the formant of xml", e);
        }

    }

}
