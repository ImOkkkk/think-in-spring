package org.liuwy.bean.definition;

import org.liuwy.bean.factory.DefaultUserFactory;
import org.liuwy.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean注册示例
 * 
 * @author ImOkkkk
 * @date 2021/8/8 21:20
 * @since 1.0
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();
        // 创建一个外部UserFactory对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册外部单例对象
        beanFactory.registerSingleton("userFactory", userFactory);
        // 通过依赖查找的方式来获取UserFactory
        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println(userFactory == userFactoryByLookup); //true
        applicationContext.close();
    }
}
