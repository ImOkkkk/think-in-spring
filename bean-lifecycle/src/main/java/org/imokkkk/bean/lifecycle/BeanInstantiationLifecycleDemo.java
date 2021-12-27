package org.imokkkk.bean.lifecycle;

import org.liuwy.ioc.overview.domain.Admin;
import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * @author ImOkkkk
 * @date 2021/12/22 22:05
 * @since 1.0
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constructor-dependency-injection.xml"};
        xmlBeanDefinitionReader.loadBeanDefinitions(locations);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("User：" + user);
        User admin = beanFactory.getBean("Admin", User.class);
        System.out.println("Admin：" + admin);
        // 构造器注入按照类型注入(User.class Admin primary="true")，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("UserHolder：" + userHolder);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

            if (ObjectUtils.nullSafeEquals("Admin", beanName) && Admin.class.equals(beanClass)) {
                // 把配置完成Admin Bean覆盖
                return new Admin();
            }
            // 保持Spring IoC容器实例化操作
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                // 手动植入
                User user = (User) bean;
                user.setName("手动赋值");
                // user对象不允许属性赋值(配置元信息->属性值)
                return false;
            }
            return true;
        }
        // user跳过Bean属性赋值
        // Admin也是完全跳过Bean实例化
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
            // 对userHolder Bean拦截
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                // 假设配置<property name="number" value="1"/>，那么在PropertyValues就包含一个PropertyValues(number=1)
                final MutablePropertyValues propertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues)pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }
                // 等价于<property name="number" value="1"/>
                propertyValues.add("number", "1");
                // 原始配置<property name="description" value="The user holder"/>
                if (propertyValues.contains("description")) {
                    // PropertyValue value是不可变的(final)
                    // PropertyValue propertyValue = propertyValues.getPropertyValue("description");
                    propertyValues.removePropertyValue("description");
                    propertyValues.add("description", "The user holder V2！");
                }
                return propertyValues;
            }
            return null;
        }
    }
}
