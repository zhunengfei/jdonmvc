import com.jdon.mvc.Constant;
import com.jdon.mvc.RestFilter;
import com.jdon.mvc.annotations.In;
import com.jdon.mvc.http.RequestBody;
import com.jdon.mvc.represent.Html;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.annotations.Path;
import moc.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * User: Asion
 * Date: 13-6-4
 * Time: 下午2:26
 */
public class Controller {

    @In
    private HttpServletRequest request;

    @In
    private RequestBody body;

    @In("helloService")
    private Hello helloService;


    @Path("/")
    public Represent index(User user) {
        helloService.hello();
        return new Html("hello", "user", user);
    }


    @Path("/blog/:id")
    public void show(int id) {
        System.out.println(id);
    }

    @Path("/blog/:id/sub/:sub")
    public void show(int id, int sub) {
        System.out.println(id + sub);
    }

    @Path("/list")
    public void list(List<String> list) {
        System.out.println(list);
    }

    @Path("delete:/binding")
    public void testBinding(Code code, String name) {
        System.out.println(code);
        System.out.println(name);
    }


    public static void main(String[] args) throws ServletException, IOException {

        RestFilter filter = new RestFilter();
        MockFilterConfig cfg = new MockFilterConfig();
        MockServletContext servletContext = (MockServletContext) cfg.getServletContext();
        servletContext.addInitParameter(Constant.TEMPLATE_FACTORY, "com.jdon.mvc.template.velocity.VelocityTemplateFactory");

        filter.init(cfg);

        MockFilterChain chain = new MockFilterChain();
        MockRequest userRequest = new MockRequest(servletContext, "Get", "/");
        userRequest.addParam("user.name", "谢中生");
        filter.doFilter(userRequest, new MockResponse(), chain);
        filter.doFilter(new MockRequest(servletContext, "Get", "/blog/3x"), new MockResponse(), chain);
        filter.doFilter(new MockRequest(servletContext, "Get", "/blog/3/sub/4"), new MockResponse(), chain);

        MockRequest listRequest = new MockRequest(servletContext, "Get", "/list");
        listRequest.addMultiParam("list", new String[]{"111", "222"});
        filter.doFilter(listRequest, new MockResponse(), chain);

        MockRequest enumRequest = new MockRequest(servletContext, "delete", "/binding");
        enumRequest.addParam("code", "yes");
        enumRequest.addParam("name", "test");
        filter.doFilter(enumRequest, new MockResponse(), chain);


        //测试groovy
        filter.doFilter(new MockRequest(servletContext, "Get", "/forum/44444"), new MockResponse(), chain);

        filter.destroy();

    }

}

enum Code {
    yes,
    no
}
