package moc;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

/**
 * User: Asion
 * Date: 13-6-4
 * Time: 下午3:45
 */
public class MockServletContext implements ServletContext {

    @Override
    public String getContextPath() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ServletContext getContext(String uripath) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMajorVersion() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMinorVersion() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMimeType(String file) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set getResourcePaths(String path) {
        return Collections.EMPTY_SET;
    }

    @Override
    public URL getResource(String path) throws MalformedURLException {
        return null;
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Servlet getServlet(String name) throws ServletException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Enumeration getServlets() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Enumeration getServletNames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void log(String msg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void log(Exception exception, String msg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void log(String message, Throwable throwable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRealPath(String path) {
        return "/home/asion/jdonmvc/target/test-classes";
    }

    @Override
    public String getServerInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getInitParameter(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Enumeration getInitParameterNames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getAttribute(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAttribute(String name, Object object) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeAttribute(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getServletContextName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
