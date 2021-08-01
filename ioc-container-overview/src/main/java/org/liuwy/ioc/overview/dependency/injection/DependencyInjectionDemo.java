package org.liuwy.ioc.overview.dependency.injection;

import org.liuwy.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/7/26 21:23
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        // 依赖来源1.自定义bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
        // System.out.println(userRepository.getUsers());
        // 依赖来源2.内建依赖
        System.out.println(userRepository.getBeanFactory());
        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());
        System.out.println(userFactory.getObject() == applicationContext);
        // 依赖查找(错误)
        // System.out.println(beanFactory.getBean(BeanFactory.class));
        // 依赖来源3.容器内建Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取Environment类型的Bean：" + environment);

    }

    private static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {
        //ConfigurableApplicationContext<-applicationContext<-BeanFactory
        //ConfigurableApplicationContext#getBeanFactory()
        System.out.println(userRepository.getBeanFactory() == applicationContext);// false

        //ApplicationContext就是BeanFactory？
        //Application组合了一个BeanFactory实现，是BeanFactory的超集  代理模式
    }

}
