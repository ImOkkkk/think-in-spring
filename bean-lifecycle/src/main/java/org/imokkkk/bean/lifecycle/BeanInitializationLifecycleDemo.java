package org.imokkkk.bean.lifecycle;

import org.liuwy.ioc.overview.domain.Admin;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Bean初始化生命周期示例
 * 
 * @author ImOkkkk
 * @date 2022/1/7 20:45
 * @since 1.0
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        String[] locations =
            {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载BeanDefinition数量：" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        Admin admin = beanFactory.getBean("Admin", Admin.class);
        System.out.println(admin);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }
}
