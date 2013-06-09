package com.jdon.mvc.ioc;

import com.jdon.controller.WebAppUtil;
import com.jdon.mvc.core.IocProvider;

import javax.servlet.ServletContext;


/**
 * 本框架默认使用Jdon作为领域模型管理框架
 * Jdon特有的Domain Event编程模式可以更好的DDD
 *
 * @author oojdon
 */
public class JdonProvider implements IocProvider {

    @Override
    public Object getBean(String key, BeanType beanType, ServletContext sc) {
        if (beanType == BeanType.SERVICE) {
            return WebAppUtil.getService(key, sc);
        } else {
            return WebAppUtil.getComponentInstance(key, sc);
        }
    }


}