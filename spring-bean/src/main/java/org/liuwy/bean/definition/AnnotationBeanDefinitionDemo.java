package org.liuwy.bean.definition;

import java.util.Map;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解BeanDefinition示例
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/8/7 16:08
 */

// 3.通过@Import方式导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        applicationContext.register(Config.class);
        // 通过BeanDefinition注册API实现
        registerBeanDefinition(applicationContext, "i2-user", User.class);
        registerBeanDefinition(applicationContext, User.class);
        applicationContext.refresh();
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        System.out.println("Config类型的所有Beans：" + configBeans);
        Map<String, User> userBeans = applicationContext.getBeansOfType(User.class);
        System.out.println("User类型的所有Beans：" + userBeans);

        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * 
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "ImOkkkk");
        if (StringUtils.hasText(beanName)) {
            // 注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

    /**
     * 非命名的注册方式
     * 
     * @param registry
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
        registerBeanDefinition(registry, null, beanClass);
    }

    // 2.通过@Component方式
    @Component // 定义当前类作为Spring Bean(组件)
    public static class Config {
        // 1.通过@Bean方式定义
        @Bean(name = {"user", "i-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("ImOkkkk");
            return user;
        }
    }
}
