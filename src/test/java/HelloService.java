import com.jdon.annotation.Service;

/**
 * User: Asion
 * Date: 13-6-17
 * Time: 下午5:18
 */
@Service("helloService")
public class HelloService implements Hello {

    public void hello() {
        System.out.println("hello ioc");
    }
}
