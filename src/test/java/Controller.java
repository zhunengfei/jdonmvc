import com.jdon.mvc.RestFilter;
import com.jdon.mvc.annotations.In;
import com.jdon.mvc.ioc.BeanType;
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
import java.util.List;

/**
 * User: Asion
 * Date: 13-6-4
 * Time: 下午2:26
 */
public class Controller {

    @In
    private HttpServletRequest request;

    @In(value = "helloService",type = BeanType.SERVICE)
    private HelloService helloService;


    @Path("/")
    public Represent index(User user) {
        helloService.hello();
        return new Html("hello", "user", user);
    }


    @Path("/blog/:id")
    @GET
    public void show(int id) {
        System.out.println(id);
    }

    @Path("/blog/:id/sub/:sub")
    @GET
    public void show(int id, int sub) {
        System.out.println(id + sub);
    }

    @Path("/list")
    public void list(List<String> list) {
        System.out.println(list);
    }

    @Path("/binding")
    public void testBinding(Code code) {
        System.out.println(code);
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

        MockRequest listRequest = new MockRequest(cfg.getServletContext(), "Get", "/list");
        listRequest.addMultiParam("list",new String[]{"111","222"});
        filter.doFilter(listRequest, new MockResponse(), chain);

        MockRequest enumRequest =new MockRequest(cfg.getServletContext(),"Get","/binding");
        enumRequest.addParam("code","yes");
        filter.doFilter(enumRequest, new MockResponse(), chain);


        //测试groovy
        filter.doFilter(new MockRequest(cfg.getServletContext(), "Get", "/forum/44444"), new MockResponse(), chain);

        filter.destroy();

    }

}

enum Code {
    yes,
    no
}
