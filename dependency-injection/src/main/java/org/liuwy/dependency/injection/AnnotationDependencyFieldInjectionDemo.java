package org.liuwy.dependency.injection;

import javax.annotation.Resource;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java 注解的依赖 字段注入示例
 * 
 * @author ImOkkkk
 * @date 2021/9/5 21:44
 * @since 1.0
 */
public class AnnotationDependencyFieldInjectionDemo {
    //@Autowired会忽略static字段
    @Autowired
    private UserHolder userHolder;
    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类) ->Spring Bean
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();
        // 依赖查找AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyFieldInjectionDemo demo =
            applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        // @Autowired字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);

        System.out.println(demo.userHolder2);
        //true
        System.out.println(userHolder == demo.userHolder2);
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
