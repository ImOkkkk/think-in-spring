package org.liuwy.bean.definition;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.liuwy.bean.factory.DefaultUserFactory;
import org.liuwy.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 特殊的Bean实例化示例
 * 
 * @author liuwy
 * @date 2021/8/7 19:06
 * @since 1.0
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        //通过ApplicationContext获取AutowireCapableBeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        // demoServiceLoader();
        ServiceLoader<UserFactory> serviceLoader =
            applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        //通过AutowireCapableBeanFactory创建
        UserFactory beanFactoryBean = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(beanFactoryBean.createUser());
    }

    /*    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader =
            ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        displayServiceLoader(serviceLoader);
    }*/

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
