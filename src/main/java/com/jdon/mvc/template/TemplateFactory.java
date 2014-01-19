package com.jdon.mvc.template;

import javax.servlet.ServletContext;

/**
 * @author xmuzyq 
 * @author oojdon 
 * 模板工厂，用于生产html模板，利用抽象工厂设计模式，需要真正的模板引擎提供商
 * 继承并覆盖init和loadTemplate
 * 用户可以具体化这个抽象工厂，定制自己的模板显示
 * 不过本框架提供现成的jsp,velocity,freemarker供用户使用
 */

public interface TemplateFactory {
  
    void init(ServletContext servletContext);

    String getSuffix();

    Template loadTemplate(String path) throws Exception;

}
