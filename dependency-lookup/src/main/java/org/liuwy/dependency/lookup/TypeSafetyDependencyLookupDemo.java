package org.liuwy.dependency.lookup;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 * 
 * @author ImOkkkk
 * @date 2021/8/23 21:24
 * @since 1.0
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();
        // BeanFactory#getBean安全性
        displayBeanFactoryGetBean(applicationContext);
        // ObjectFactory#getObject安全性
        displayObjectFactoryGetObject(applicationContext);
        // ObjectProvider#getIfAvailable安全性
        displayObjectProviderIfAvailable(applicationContext);
        // ListableBeanFactory#getBeansOfType安全性
        disPlayListableBeanFactoryGetBeansOfType(applicationContext);
        // ObjectProvider Stream操作的安全性
        displayObjectProviderStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
    }

    private static void disPlayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
        printBeansException("disPlayListableBeanFactoryGetBeansOfType",
            () -> listableBeanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> userObjectProvider.getIfAvailable());
    }

    // NoSuchBeanDefinitionException
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetBean", () -> userObjectFactory.getObject());
    }

    // NoSuchBeanDefinitionException
    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("========================");
        System.err.println("Source from：" + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }
}
