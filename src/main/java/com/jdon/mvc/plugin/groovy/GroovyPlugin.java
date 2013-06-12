package com.jdon.mvc.plugin.groovy;

import com.jdon.mvc.annotations.Plugin;
import com.jdon.mvc.core.FrameWorkContext;
import com.jdon.mvc.plugin.JdonMvcPlugin;
import com.jdon.mvc.scan.WarUrlFinder;
import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.net.URL;

/**
 * 可以用groovy来写controller的插件
 * 但是在IDEA开发环境中，默认就可以编译groovy到classpath
 * User: oojdon
 * Date: 13-6-12
 * Time: 上午12:09
 */
@Plugin
public class GroovyPlugin implements JdonMvcPlugin {

    private GroovyClassLoader loader = new GroovyClassLoader();

    @Override
    public void init(FrameWorkContext fc) {
        URL groovyURL = WarUrlFinder.findWebInfURL(fc.getServletContext(), "/WEB-INF/groovy");
        if (groovyURL != null) {
            File dir = new File(groovyURL.getPath());
            File[] files = dir.listFiles();
        }

    }

    @Override
    public void dispose() {

    }
}
