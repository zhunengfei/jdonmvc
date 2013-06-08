package moc;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * User: Asion
 * Date: 13-6-7
 * Time: 下午2:20
 */
public class MockFilterChain implements FilterChain {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {

    }
}
