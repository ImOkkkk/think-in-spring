package org.liuwy.dependency.lookup;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

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
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        /*Iterable<String> stringIterable = objectProvider;
        for (String string : stringIterable) {
            System.out.println(string);
        }*/
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        // 如果不存在，默认创建user
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前User对象：" + user.toString());
    }

    @Bean
    @Primary
    public String helloWord() { // 方法名就是Bean名称
        return "Hello World!";
    }

    @Bean
    public String message() {
        return "message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
