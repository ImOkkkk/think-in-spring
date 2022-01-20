package org.imokkkk.bean.lifecycle;

import org.liuwy.ioc.overview.domain.Admin;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean初始化生命周期示例
 * 
 * @author ImOkkkk
 * @date 2022/1/7 20:45
 * @since 1.0
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加BeanPostProcessor实现MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加CommonAnnotationBeanPostProcessor解决@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations =
            {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载BeanDefinition数量：" + beanNumbers);
        // SmartInitializingSingleton通常在ApplicationContext场景下使用
        // 显式的执行preInstantiateSingletons()方法
        // preInstantiateSingletons()将已注册的BeanDefinition初始化成Spring Bean
        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        Admin admin = beanFactory.getBean("Admin", Admin.class);
        System.out.println(admin);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }
}
