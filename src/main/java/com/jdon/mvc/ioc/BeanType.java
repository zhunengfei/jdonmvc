package com.jdon.mvc.ioc;

/**
 * Bean类型，组件还是服务，一般服务会被AOP包装
 * 在Spring的当前版本，组件和服务两个注解似乎没有区别
 * 但是在Jdon中，service会经过AOP
 * User: oojdon
 * Date: 11-9-17
 * Time: 下午3:40
 */
public enum BeanType {

    service,

    component

}
