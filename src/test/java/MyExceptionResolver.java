import com.jdon.mvc.annotations.ExceptionHandler;
import com.jdon.mvc.converter.BindingException;
import com.jdon.mvc.core.ActionException;
import com.jdon.mvc.core.ExceptionResolver;
import com.jdon.mvc.flow.MaxUploadSizeException;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.rs.java.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: oojdon
 * Date: 13-6-21
 * Time: 下午9:52
 */
@ExceptionHandler
public class MyExceptionResolver implements ExceptionResolver {

    @Override
    public Represent resolveActionException(HttpServletRequest request, HttpServletResponse response, Handler handler, ActionException ex) {
        System.out.println("occur action exception");
        return null;
    }

    @Override
    public Represent resolveBindingException(HttpServletRequest request, HttpServletResponse response, Handler handler, BindingException ex) {
        System.out.println("occur binding exception");
        return null;
    }

    @Override
    public Represent resolveUploadException(HttpServletRequest request, HttpServletResponse response, Handler handler, MaxUploadSizeException ex) {
        System.out.println("occur maxupload exception");
        return null;
    }
}
