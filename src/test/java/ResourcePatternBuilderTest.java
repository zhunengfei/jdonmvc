import com.jdon.mvc.rs.ResourcePatternBuilder;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class ResourcePatternBuilderTest {
	
	@Test
	public void testPatternResolve1() {
		ResourcePatternBuilder builder = new ResourcePatternBuilder();
		builder.build("/user/{id}");	
		assertEquals("/user/([^/]+)",builder.getPattern().toString());
		assertEquals("id",builder.getParamList().get(0));
	}
	
	@Test
	public void testPatternResolve2() {
		ResourcePatternBuilder builder = new ResourcePatternBuilder();
		builder.build("/user/{userId}/blogs/{blogId}");	
		assertEquals("/user/([^/]+)/blogs/([^/]+)",builder.getPattern().toString());
		assertEquals("userId",builder.getParamList().get(0));
		assertEquals("blogId",builder.getParamList().get(1));
	}

}
