package com.jdon.mvc.rs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据Path注解的路径字符串构建Pattern对象
 *
 * @author oojdon
 */
public class ResourcePatternBuilder {

    private final Log LOG = LogFactory.getLog(getClass());

    private List<String> params = new ArrayList<String>();

    private Pattern pattern;

    public void build(String path) {

        //先提取出{}中的路径变量
        Matcher matcher = Pattern.compile("\\{([a-zA-Z_0-9.]+)\\}").matcher(path);
        while (matcher.find()) {
            String value = matcher.group(1);
            params.add(value);
        }

        //再把路径变换成一个正则表达式
        String patternString = path.replaceAll("\\{([a-zA-Z_0-9.]+)\\}", "([^/]+)");
        LOG.info("--->build regex in PatternBuilder:" + patternString);
        this.pattern = Pattern.compile(patternString);
    }

    public List<String> getParamList() {
        return params;
    }

    public Pattern getPattern() {
        return pattern;
    }

}
