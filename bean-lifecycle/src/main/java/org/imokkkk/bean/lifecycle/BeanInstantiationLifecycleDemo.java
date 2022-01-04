package org.imokkkk.bean.lifecycle;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ImOkkkk
 * @date 2021/12/22 22:05
 * @since 1.0
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("---------------------------");
        executeApplicationContext();
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations =
            {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println("User：" + user);
        User admin = applicationContext.getBean("Admin", User.class);
        System.out.println("Admin：" + admin);
        // 构造器注入按照类型注入(User.class Admin primary="true")，resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println("UserHolder：" + userHolder);

        applicationContext.close();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加BeanPostProcessor实现
        // beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二：将MyInstantiationAwareBeanPostProcessor作为Bean注册
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations =
            {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        xmlBeanDefinitionReader.loadBeanDefinitions(locations);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("User：" + user);
        User admin = beanFactory.getBean("Admin", User.class);
        System.out.println("Admin：" + admin);
        // 构造器注入按照类型注入(User.class Admin primary="true")，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("UserHolder：" + userHolder);
    }
}
