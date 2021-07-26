package org.liuwy.ioc.overview.dependency.lookup;

import java.util.Map;

import org.liuwy.ioc.overview.annotation.Super;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖查找示例 1.通过名称的方式查找
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/7/26 21:23
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory =
            new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        // 实时查找
        lookupRealTime(beanFactory);
        // 延迟查找
        lookupLazy(beanFactory);
        // 根据类型查找
        lookupByType(beanFactory);
        // 根据类型查找集合对象
        lookupByCollectionType(beanFactory);
        // 根据注解查找对象
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注@Super所有的User集合对象：" + users);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象：" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找：" + user);
    }

    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("根据名称延迟查找：" + user);
    }

    private static void lookupRealTime(BeanFactory beanFactory) {
        User user = (User)beanFactory.getBean("user");
        System.out.println("根据名称实时查找：" + user);
    }
}
