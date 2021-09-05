package org.liuwy.dependency.lookup;

import java.util.Map;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 * 
 * @author ImOkkkk
 * @date 2021/8/15 21:48
 * @since 1.0
 */

public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();
        // 1.获取HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory 组合模式
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的Parent BeanFactory：" + beanFactory.getParentBeanFactory());
        // 2.设置parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前BeanFactory的Parent BeanFactory：" + beanFactory.getParentBeanFactory());

        displayLocalBean(beanFactory, "user");
        displayLocalBean(parentBeanFactory, "user");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        Map<String, User> userMapFromBeanFactory =
            BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, User.class);
        Map<String, User> userMapFromParentBeanFactory =
            BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory)beanFactory, User.class);
        System.out.println(userMapFromBeanFactory);
        System.out.println(userMapFromParentBeanFactory);
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含Bean[name：%s]：%s\n", beanFactory, beanName,
            containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory =
                HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }

        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含LocalBean[name：%s]：%s\n", beanFactory, beanName,
            beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
