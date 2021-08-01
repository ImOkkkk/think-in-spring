package org.liuwy.ioc.overview.container;

import java.util.Map;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * {@link BeanFactory}作为IoC容器示例
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/8/1 17:12
 */
public class BeanFactoryAsIoCContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML配置文件classPath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载的数量：" + beanDefinitionCount);
        // 依赖查找集合对象
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的User集合对象：" + users);
        }
    }
}
