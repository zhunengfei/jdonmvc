package com.jdon.mvc.plugin;

import com.jdon.mvc.core.FrameWorkContext;

/**
 * User: oojdon
 * Date: 13-6-12
 * Time: 上午12:05
 */
public interface JdonMvcPlugin {

    void init(FrameWorkContext fc);

    void dispose();
}
