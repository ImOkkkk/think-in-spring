package org.imokkkk.bean.lifecycle;

import java.util.Map;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * BeanDefinition合并示例
 * 
 * @author ImOkkkk
 * @date 2021/12/19 21:48
 * @since 1.0
 */
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        Resource resource = new ClassPathResource(location);
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("已加载BeanDefinition数量：" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user：" + user);
        User admin = beanFactory.getBean("Admin", User.class);
        System.out.println("Admin：" + admin);
        Map<String, User> userMap = beanFactory.getBeansOfType(User.class);
        System.out.println(userMap);
    }
}
