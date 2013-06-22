package com.jdon.mvc.http;

import com.jdon.mvc.rs.ResourceRequestInfo;
import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.Assert.assertTrue;

/**
 * User: oojdon
 * Date: 13-6-22
 * Time: 上午10:52
 */
public class RequestTargetInfoTest {

    @Test
    public void testMatchPattern() throws Exception {
        ResourceRequestInfo info = new ResourceRequestInfo("/","","get");
        RequestTargetInfo targetInfo = new RequestTargetInfo(null,null,info);
        assertTrue(targetInfo.matchPattern(Pattern.compile(".*")));
        assertTrue(Pattern.compile("^/bl.*").matcher("/blog").matches());
    }
}
