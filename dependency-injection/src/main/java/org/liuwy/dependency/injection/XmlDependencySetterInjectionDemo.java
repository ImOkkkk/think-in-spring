package org.liuwy.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML资源的依赖Setter方法注入示例
 * 
 * @author ImOkkkk
 * @date 2021/9/5 21:28
 * @since 1.0
 */
public class XmlDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:/META-INF/dependency-setter-injection.xml";
        // 加载XML资源，解析并生成BeanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        // 依赖查找并创建bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
