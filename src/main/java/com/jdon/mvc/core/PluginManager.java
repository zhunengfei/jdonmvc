package com.jdon.mvc.core;

import com.jdon.mvc.plugin.JdonMvcPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * User: oojdon
 * Date: 13-6-12
 * Time: 下午10:50
 */
public class PluginManager {

    private List<JdonMvcPlugin> plugins = new ArrayList<JdonMvcPlugin>();

    public void register(JdonMvcPlugin pluginClass) {
        plugins.add(pluginClass);
    }

    public List<JdonMvcPlugin> getPlugins() {
        return plugins;
    }

    public void init(FrameWorkContext fc) {
        for (JdonMvcPlugin plugin : this.plugins) {
            plugin.init(fc);
        }
    }

    public void dispose() {
        for (JdonMvcPlugin plugin : this.plugins) {
            plugin.dispose();
        }
    }

}
