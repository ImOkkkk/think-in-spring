package org.liuwy.dependency.lookup;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.factory.BeanCreationException}示例
 * 
 * @author ImOkkkk
 * @date 2021/8/31 22:41
 * @since 1.0
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());
        applicationContext.refresh();
        applicationContext.register();
    }

    static class POJO implements InitializingBean {
        // CommonAnnotationBeanPostProcessor
        @PostConstruct
        public void init() throws Exception {
            throw new Exception("@PostConstruct：For purposes...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet()：For purposes...");
        }
    }
}
