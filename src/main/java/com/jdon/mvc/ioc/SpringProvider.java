package com.jdon.mvc.ioc;

import com.jdon.mvc.config.ConfigException;
import com.jdon.mvc.core.IocProvider;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;


/**
 * Spring的业务bean提供实现，如果要回到那个传统的SSH，可以从这里开始
 *
 * <context-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>classpath:/SpringContext.xml</param-value>
 * </context-param>
 *
 *
 * <listener>
 * <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 * </listener>
 *
 * @author oojdon
 */
public class SpringProvider implements IocProvider {

    @Override
    public Object getBean(String key, BeanType beanType, ServletContext sc) {
        Object attribute = sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
        if (attribute == null) {
            throw new ConfigException("can not find spring applicationContext");
        }
        ApplicationContext context = (ApplicationContext) attribute;
        return context.getBean(key);
    }


}
