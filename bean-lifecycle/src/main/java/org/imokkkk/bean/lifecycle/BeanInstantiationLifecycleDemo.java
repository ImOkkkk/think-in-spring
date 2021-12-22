package org.imokkkk.bean.lifecycle;

import org.liuwy.ioc.overview.domain.Admin;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

/**
 * @author ImOkkkk
 * @date 2021/12/22 22:05
 * @since 1.0
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        Resource resource = new ClassPathResource(location);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("User：" + user);
        User admin = beanFactory.getBean("Admin", User.class);
        System.out.println("Admin：" + admin);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

            if (ObjectUtils.nullSafeEquals("Admin", beanName) && Admin.class.equals(beanClass)) {
                // 把配置完成Admin Bean覆盖
                return new Admin();
            }
            // 保持Spring IoC容器实例化操作
            return null;
        }
    }
}
