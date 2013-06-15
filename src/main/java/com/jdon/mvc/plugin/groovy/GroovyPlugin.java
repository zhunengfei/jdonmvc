package com.jdon.mvc.plugin.groovy;

import com.jdon.mvc.annotations.Plugin;
import com.jdon.mvc.core.FrameWorkContext;
import com.jdon.mvc.plugin.JdonMvcPlugin;
import com.jdon.mvc.scan.WarUrlFinder;
import groovy.lang.GroovyClassLoader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    private final static Log LOG = LogFactory.getLog(GroovyPlugin.class);

    private GroovyClassLoader loader = new GroovyClassLoader();

    @Override
    public void init(FrameWorkContext fc) {
        URL groovyURL = WarUrlFinder.findWebInfURL(fc.getServletContext(), "/WEB-INF/groovy");
        if (groovyURL != null) {
            File dir = new File(groovyURL.getPath());
            File[] files = dir.listFiles();
            for (File file : files) {
                if (!file.isDirectory() && file.getName().endsWith("groovy")) {
                    LOG.info("find groovy file:" + file.getName());
                    try {
                        String script = IOUtils.toString(new FileInputStream(file), "UTF-8");
                        Class type = loader.parseClass(script);
                        fc.getResourceManager().registerClass(type);
                    } catch (IOException e) {
                        LOG.error("can't load groovy file", e);
                    }
                }
            }
        }

    }

    @Override
    public void dispose() {

    }
}
