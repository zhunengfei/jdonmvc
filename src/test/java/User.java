import java.util.*;

/**
 * User: Asion
 * Date: 13-6-7
 * Time: 下午3:50
 */
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        int a[] = {1,2,3};
        System.out.println(a.getClass().isArray());
        List<Integer> list = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        System.out.println(Collection.class.isAssignableFrom(list.getClass()));
        System.out.println(Collection.class.isAssignableFrom(set.getClass()));


    }
}
