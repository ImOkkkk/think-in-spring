package org.liuwy.dependency.injection;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.liuwy.dependency.injection.annotation.InjectUser;
import org.liuwy.dependency.injection.annotation.MyAutowired;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 注解驱动的依赖注入处理过程
 * 
 * @author ImOkkkk
 * @date 2021/9/23 22:32
 * @since 1.0
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired // 依赖查找(处理)+延迟
    @Lazy
    private User lazyUser;

    @Autowired // 依赖查找(处理)
    private User user;// DependencyDescriptor->
                      // 必须(required=true)+实时注入(eager=true)+通过类型依赖查找(User.class)+字段名称(user)+是否首要(primary:true);

    @Autowired // 集合类型依赖注入
    private Collection<User> users;// [user，admin]

    @Autowired
    private Map<String, User> userMap;// key=user value=User，key=Admin value=Admin

    @MyAutowired
    private Optional<User> userOptional;// Admin

    @Inject
    private User injectUser;

    @InjectUser
    private User myInjectUser;

    /*@Bean(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME) //static会使@Bean优先注册
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // @Autowired+@Inject+新注解@InjectUser
        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
            new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }*/

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();
        AnnotationDependencyInjectionResolutionDemo demo =
            applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("demo.user：" + demo.user);
        System.out.println("demo.injectUser：" + demo.injectUser);
        System.out.println("demo.myInjectUser：" + demo.myInjectUser);
        System.out.println("demo.users：" + demo.users);
        System.out.println("demo.userMap：" + demo.userMap);
        System.out.println("demo.userOptional：" + demo.userOptional);
        applicationContext.close();
    }
}
