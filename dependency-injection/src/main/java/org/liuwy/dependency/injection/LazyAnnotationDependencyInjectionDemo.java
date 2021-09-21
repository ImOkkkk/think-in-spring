package org.liuwy.dependency.injection;

import java.util.Collection;
import java.util.Set;

import org.liuwy.dependency.injection.annotation.UserGroup;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.ObjectProvider}延迟注入
 * 
 * @author ImOkkkk
 * @date 2021/9/17 22:27
 * @since 1.0
 */
public class LazyAnnotationDependencyInjectionDemo {
    @Autowired
    private ObjectProvider<User> userObjectProvider;// 延迟注入
    @Autowired
    private User user;
    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo =
            applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println("demo.user：" + demo.user);
        System.out.println("demo.userObjectProvider：" + demo.userObjectProvider.getObject()); // ObjectProvider继承ObjectFactory
        demo.userObjectProvider.forEach(System.out::println); // 集合

        System.out.println("demo.usersObjectFactory：" + demo.usersObjectFactory.getObject());
        applicationContext.close();
    }
}
