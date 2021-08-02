package org.liuwy.bean.definition;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 *
 * @author liuwy
 * @version 1.0
 * @date 2021/8/2 22:07
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // 1.通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "liuwy");
        // 获取BeanDefinition示例
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition并非Bean终态，可以自定义修改

        // 2.通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过MutablePropertyValues批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        // propertyValues.addPropertyValue("id",1);
        // propertyValues.addPropertyValue("name","liuwy");
        propertyValues.add("id", 1).add("name", "liuwy");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
