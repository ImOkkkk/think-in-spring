package org.liuwy.dependency.injection;

import javax.annotation.Resource;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java 注解的依赖 方法注入示例
 * 
 * @author ImOkkkk
 * @date 2021/9/5 21:44
 * @since 1.0
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类) ->Spring Bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();
        // 依赖查找AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo demo =
            applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
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
