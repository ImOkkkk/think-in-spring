package org.liuwy.dependency.source;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ResolvableDependency作为依赖来源
 * 
 * @author ImOkkkk
 * @date 2021/10/23 22:13
 * @since 1.0
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 回调 注册ResolvableDependency
            beanFactory.registerResolvableDependency(String.class, "Hello World!");
        });

        applicationContext.refresh();
        applicationContext.close();
    }
}
