package org.liuwy.dependecy.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过{@link org.springframework.beans.factory.ObjectProvider}进行依赖查找
 * 
 * @author ImOkkkk
 * @date 2021/8/11 22:46
 * @since 1.0
 */
// @Configuration
public class ObjectProviderDemo { /// @Configuration是非必须的
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
        applicationContext.close();
    }

    @Bean
    public String helloWord() { // 方法名就是Bean名称
        return "Hello World!";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
