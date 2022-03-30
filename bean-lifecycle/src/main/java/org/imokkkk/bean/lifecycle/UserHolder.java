package org.imokkkk.bean.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author ImOkkkk
 * @date 2021/12/24 22:28
 * @since 1.0
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,
    InitializingBean, SmartInitializingSingleton, DisposableBean {
    private final User user;

    private Integer number;

    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getNumber() {
        return number;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;

    private Environment environment;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 依赖于注解驱动
     * 当前场景：BeanFactory
     */
    @PostConstruct
    public void initPostConstruct() {
        // postProcessBeforeInitialization V3->v4
        this.description = "The user holder V4!";
        System.out.println("PostConstruct() = "+ description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "The user holder V5!";
        System.out.println("afterPropertiesSet() = "+ description);
    }

    public void init(){
        this.description = "The user holder V6!";
        System.out.println("init() = "+ description);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = "The user holder V8!";
        System.out.println("afterSingletonsInstantiated() = " + description);
    }

    @PreDestroy
    public void preDestroy() {
        this.description = "The user holder V10";
        System.out.println("preDestroy() : " + description);
    }

    @Override
    public void destroy() throws Exception {
        this.description = "The user holder V11";
        System.out.println("DisposableBean.destroy() : " + description);
    }

    public void doDestroy(){
        this.description = "The user holder V12";
        System.out.println("doDestroy() : " + description);
    }

    public void finalize() throws Throwable{
        System.out.println("UserHolder is finalized...");
    }
}
