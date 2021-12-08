package org.imokkkk.bean.lifecycle;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 * 
 * @author ImOkkkk
 * @date 2021/12/8 22:26
 * @since 1.0
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于Properties资源BeanDefinitionReader
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        // 基于classpath加载properties资源
        Resource resource = new ClassPathResource(location);
        // 指定字符编码
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载BeanDefinition数量：" + beanNumbers);
        // 通过Bean Id和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println("User：" + user);
    }
}
