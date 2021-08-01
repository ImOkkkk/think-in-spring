package org.liuwy.ioc.overview.container;

import java.util.Map;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解能力{@link org.springframework.context.ApplicationContext}作为IoC容器示例
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/8/1 17:12
 */
@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {
    public static void main(String[] args) {
        // 创建applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类(Configuration Class)
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupCollectionByType(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 通过Java注解的方式，定义了一个Bean
     * 
     * @return
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("liuwy");
        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的User集合对象：" + users);
        }
    }
}
