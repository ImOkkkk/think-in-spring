package org.liuwy.bean.scope;

import java.util.Map;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Bean 的作用域实例
 * 
 * @author ImOkkkk
 * @date 2021/10/31 18:18
 * @since 1.0
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    // 默认scope就是"singleton"
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; // Resolvable Dependency

    @Autowired
    private Map<String, User> users;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称：%s在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });
        applicationContext.refresh();
        // 依赖查找
        scopedBeansByLookup(applicationContext);
        // 依赖注入
        // 结论1：
        // Singleton Bean无论依赖注入还是依赖查找，均为同一个对象
        // Prototype Bean无论依赖注入还是依赖查找，均为新生成对象

        // 结论2：
        // 如果依赖注入集合类型对象，Singleton Bean和Prototype Bean均只会存在一个
        // Prototype Bean有别于其他地方的依赖注入，新生成

        // 结论3：
        // 无论是Singleton还是Prototype Bean均会执行初始化方法回调
        // 但仅Singleton Bean会执行销毁方法回调
        scopedBeansByInjection(applicationContext);

        applicationContext.close();

        /* DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);
        Map<String, User> userMap = beanFactory.getBeansOfType(User.class);
        System.out.println(userMap.toString());*/

    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser：" + beanScopeDemo.singletonUser);
        System.out.println("singletonUser1：" + beanScopeDemo.singletonUser1);
        System.out.println("prototypeUser：" + beanScopeDemo.prototypeUser);
        System.out.println("prototypeUser1：" + beanScopeDemo.prototypeUser1);
        System.out.println("prototypeUser2：" + beanScopeDemo.prototypeUser2);
        System.out.println("users：" + beanScopeDemo.users);

    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            // singletonUser是共享Bean对象
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser：" + singletonUser);
            // prototypeUser是每次依赖查找均生成了新的Bean对象
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser：" + prototypeUser);
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanScopeDemo Bean开始销毁...");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        // 获取BeanDefinition
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            // 如当前Bean是prototype scope
            if (beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.destroy();
            }
        }
        System.out.println("BeanScopeDemo Bean销毁完成...");
    }
}
