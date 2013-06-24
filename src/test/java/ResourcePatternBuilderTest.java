import com.jdon.mvc.rs.ResourcePatternBuilder;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;


public class ResourcePatternBuilderTest {

    @Test
    public void testIdentify() {
        Pattern compile = Pattern.compile("[a-zA-Z_$][a-zA-Z_0-9_$]+");
        assertTrue(compile.matcher("name").matches());
        assertTrue(compile.matcher("_name").matches());
        assertTrue(compile.matcher("$gegwegwe").matches());
        assertTrue(compile.matcher("_$$$$").matches());
        assertTrue(compile.matcher("$$ewgwegw").matches());
        assertFalse(compile.matcher("333").matches());
        assertFalse(compile.matcher("3gwegwegwe").matches());
    }



    @Test
	public void testPatternResolve1() {
		ResourcePatternBuilder builder = new ResourcePatternBuilder();
		builder.build("/user/:id");
		assertEquals("/user/([^/]+)",builder.getPattern().toString());
		assertEquals("id",builder.getParamList().get(0));
	}
	
	@Test
	public void testPatternResolve2() {
		ResourcePatternBuilder builder = new ResourcePatternBuilder();
		builder.build("/user/:userId/blogs/:blogId");
		assertEquals("/user/([^/]+)/blogs/([^/]+)",builder.getPattern().toString());
		assertEquals("userId",builder.getParamList().get(0));
		assertEquals("blogId",builder.getParamList().get(1));
	}

}
