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

    @Override
    public String getFilterName() {
        return null;
    }

    @Override
    public ServletContext getServletContext() {
        return new MockServletContext();
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
