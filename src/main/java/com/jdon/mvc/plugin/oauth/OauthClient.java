package com.jdon.mvc.plugin.oauth;

import com.jdon.mvc.annotations.Plugin;
import com.jdon.mvc.core.FrameWorkContext;
import com.jdon.mvc.plugin.JdonMvcPlugin;

/**
 * Oauth协议客户端，只支持Oauth2
 * User: oojdon
 * Date: 13-6-9
 * Time: 上午10:09
 */
@Plugin
public class OauthClient implements JdonMvcPlugin {

    @Override
    public void init(FrameWorkContext fc) {

    }

    @Override
    public void dispose() {

    }
}
