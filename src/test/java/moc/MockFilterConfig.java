package moc;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * User: Asion
 * Date: 13-6-7
 * Time: 下午2:19
 */
public class MockFilterConfig implements FilterConfig {

    private MockServletContext servletContext = new MockServletContext();

    @Override
    public String getFilterName() {
        return null;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public String getInitParameter(String name) {
        return null;
    }

    @Override
    public Enumeration getInitParameterNames() {
        return null;
    }
}
