package org.liuwy.bean.factory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 默认{@link UserFactory}实现
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/8/7 18:05
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    // 1.基于@PostConstruct注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct：UserFactory初始化中...");
    }

    // 2.自定义初始化方法
    public void initUserFactory() {
        System.out.println("自定义初始化方法initUserFactory()：UserFactory初始化中...");
    }

    // 3.实现InitializingBean接口的afterPropertiesSet()方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet：UserFactory初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy：UserFactory销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy：UserFactory销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法doDestroy()：UserFactory销毁中...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前DefaultUserFactory对象正在被垃圾回收...");
    }
}
