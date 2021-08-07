package org.liuwy.bean.definition;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化示例
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/8/7 17:36
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory =
            new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);
        System.out.println(userByInstanceMethod == userByStaticMethod);
        System.out.println(userByStaticMethod == userByFactoryBean);
    }
}
