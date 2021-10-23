package org.liuwy.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源
 * 
 * @author ImOkkkk
 * @date 2021/10/23 22:59
 * @since 1.0
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {
    @Value("${user.id:-1}")
    private Long id;
    @Value("${usr.name:imokkkk}")
    private String name;
    @Value("${user.resource}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        applicationContext.refresh();
        ExternalConfigurationDependencySourceDemo demo =
            applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("user.id：" + demo.id);
        System.out.println("user.name："+demo.name);
        System.out.println("user.resource：" + demo.resource);
        applicationContext.close();
    }
}
