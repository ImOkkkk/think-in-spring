package org.imokkkk.bean.configuration.metadata;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author ImOkkkk
 * @date 2022/4/17 21:58
 * @since 1.0
 */
public class BeanConfigurationMetadataDemo {

  public static void main(String[] args) {
    // BeanDefinition定义(声明)
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(
        User.class);
    beanDefinitionBuilder.addPropertyValue("name", "张三");
    // 获取AbstractBeanDefinition
    AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    // 附件属性(不影响Bean实例化、属性赋值、初始化)
    beanDefinition.setAttribute("name", "李四");
    // 当前BeanDefinition来自于何方(辅助作用)
    beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName)
          throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
          BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
          // 通过source来判断
          if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
            // 李四
            String name = (String) bd.getAttribute("name");
            User user = (User) bean;
            user.setName(name);
          }
        }
        return bean;
      }
    });
    beanFactory.registerBeanDefinition("user", beanDefinition);
    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
  }
}
