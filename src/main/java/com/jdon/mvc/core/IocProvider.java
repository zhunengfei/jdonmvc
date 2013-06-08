package com.jdon.mvc.core;

import com.jdon.mvc.ioc.BeanType;

import javax.servlet.ServletContext;

/**
 * IOC的抽象借口，目前有Jdon和Spring两种实现，默认使用Jdon
 * Action + Spring/Jdon + Dao，不注意就过程化代码
 * 分层是否在某个侧面就是对过程化代码的催化?
 * 也许这样会好些：Action(Context)+AOP+Domain(DomainEvent)+CQRS+Asyn+KeyValueStore(SQL--->Report(Query))
 *
 * @author oojdon (vsmysee@gmail.com)
 */
public interface IocProvider {

    Object getBean(String key, BeanType beanType, ServletContext sc);

}
