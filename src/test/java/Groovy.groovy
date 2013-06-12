import com.jdon.mvc.rs.method.Path

/**
 * User: oojdon
 * Date: 13-6-12
 * Time: 下午11:59
 */
@Path("/forum/{id}")
def void show(int id) {
    println id
}
