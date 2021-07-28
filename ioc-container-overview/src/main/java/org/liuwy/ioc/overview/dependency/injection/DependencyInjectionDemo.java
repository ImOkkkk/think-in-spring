package org.liuwy.ioc.overview.dependency.injection;

import org.liuwy.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        BeanFactory beanFactory =
            new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        //依赖来源1，自定义bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());
        //依赖注入
        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory()==beanFactory);//false
        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());
        System.out.println(userFactory.getObject()==beanFactory);
        //依赖查找(错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));

    }

}
