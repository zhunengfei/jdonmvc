import com.jdon.mvc.RestFilter;
import com.jdon.mvc.annotations.In;
import com.jdon.mvc.represent.Html;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.method.GET;
import com.jdon.mvc.rs.method.Path;
import moc.MockFilterChain;
import moc.MockFilterConfig;
import moc.MockRequest;
import moc.MockResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: Asion
 * Date: 13-6-4
 * Time: 下午2:26
 */
public class Controller {

    @In
    private HttpServletRequest request;


    @Path("/")
    public Represent index(User user) {
        return new Html("/hello.vm", "user", user);
    }


    @Path("/blog/{id}")
    @GET
    public void show(int id) {
        System.out.println(id);
    }

    @Path("/blog/{id}/sub/{sub}")
    @GET
    public void show(int id, int sub) {
        System.out.println(id + sub);
    }


    public static void main(String[] args) throws ServletException, IOException {

        RestFilter filter = new RestFilter();
        MockFilterConfig cfg = new MockFilterConfig();
        filter.init(cfg);

        MockFilterChain chain = new MockFilterChain();
        MockRequest userRequest = new MockRequest(cfg.getServletContext(), "Get", "/");
        userRequest.addParam("user.name", "谢中生");
        filter.doFilter(userRequest, new MockResponse(), chain);
        filter.doFilter(new MockRequest(cfg.getServletContext(), "Get", "/blog/3"), new MockResponse(), chain);
        filter.doFilter(new MockRequest(cfg.getServletContext(), "Get", "/blog/3/sub/4"), new MockResponse(), chain);
        filter.destroy();

    }

}
