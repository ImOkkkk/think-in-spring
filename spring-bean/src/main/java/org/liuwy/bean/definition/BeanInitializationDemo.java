package org.liuwy.bean.definition;

import org.liuwy.bean.factory.DefaultUserFactory;
import org.liuwy.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean初始化示例
 * 
 * @author ImOkkkk
 * @date 2021/8/8 9:28
 * @since 1.0
 */
@Configuration  //代表是配置Bean
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(BeanInitializationDemo.class);
        //启动Spring应用上下文
        applicationContext.refresh();
        //非延迟初始化在Spring应用上下文启动完成后，被初始化
        System.out.println("Spring应用上下文已启动...");
        //依赖查找UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring应用上下文准备关闭...");
        //关闭Spring应用上下文
        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");
    }
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
//    @Lazy
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
