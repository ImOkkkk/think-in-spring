package org.liuwy.dependency.injection;

import java.util.Collection;

import org.liuwy.dependency.injection.annotation.UserGroup;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier}注解依赖注入
 * 
 * @author ImOkkkk
 * @date 2021/9/17 22:27
 * @since 1.0
 */
public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user; // Admin ->primary = true
    @Autowired
    @Qualifier("user") // 指定Bean名称或ID
    private User namedUser;

    // 整体应用上下文存在4个User类型的Bean：
    // Admin
    // user
    // user1 ->@Qualifier
    // user2 ->@Qualifier

    @Autowired
    private Collection<User> users; // 2 Beans user、Admin

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // 2 Beans user1、user2 ->4 Beans user1、user2、user3、user4

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers; // 2 Beans user3、user4

    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
        return createUser(11L);
    }

    @Bean
    @Qualifier // 进行逻辑分组
    public User user2() {
        return createUser(22L);
    }

    @Bean
    @UserGroup // 进行逻辑分组
    public User user3() {
        return createUser(33L);
    }

    @Bean
    @UserGroup // 进行逻辑分组
    public User user4() {
        return createUser(44L);
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo =
            applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("user：" + demo.user);
        System.out.println("named：" + demo.namedUser);
        System.out.println("users：" + demo.users);
        System.out.println("qualifiedUsers：" + demo.qualifiedUsers);
        System.out.println("groupedUsers：" + demo.groupedUsers);
        applicationContext.close();
    }
}
