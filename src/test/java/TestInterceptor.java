import com.jdon.mvc.annotations.Join;
import com.jdon.mvc.core.ResourceInterceptor;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.java.Handler;

import java.util.regex.Pattern;

/**
 * User: oojdon
 * Date: 13-6-22
 * Time: 上午10:45
 */
@Join
public class TestInterceptor extends ResourceInterceptor {

    @Override
    public boolean pre(Handler handler) {
        System.out.println("pre");
        return true;
    }

    @Override
    public void post(Handler handler, Represent represent) {
        System.out.println("post");
    }

    @Override
    public Pattern getUrlMatchPattern() {
        return Pattern.compile("^/blog.*");
    }
}
