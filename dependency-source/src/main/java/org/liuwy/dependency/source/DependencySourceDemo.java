package org.liuwy.dependency.source;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * 依赖来源实例
 * 
 * @author ImOkkkk
 * @date 2021/10/18 22:22
 * @since 1.0
 */
public class DependencySourceDemo {
    // 注入在postProcessProperties方法执行，早于setter注入，也早于@PostConstruct
    // 非Spring容器管理对象：BeanFactory、ResourceLoader、ApplicationContext、ApplicationEventPublisher(后3个相等)
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext：" + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory："
            + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext：" + (resourceLoader == applicationContext));
        System.out.println(
            "applicationContext == applicationEventPublisher：" + (applicationContext == applicationEventPublisher));
    }

    @PostConstruct
    public void initByInjection() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在BeanFactory中查找！");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();

        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

        applicationContext.close();
    }
}
