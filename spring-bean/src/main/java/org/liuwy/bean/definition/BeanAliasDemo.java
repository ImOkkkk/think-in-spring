package org.liuwy.bean.definition;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean别名实例
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/8/3 21:40
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        //通过别名获取bean
        User liuwyUser = beanFactory.getBean("liuwy-user", User.class);
        System.out.println(liuwyUser);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        System.out.println(liuwyUser==user);    //true
    }
}
