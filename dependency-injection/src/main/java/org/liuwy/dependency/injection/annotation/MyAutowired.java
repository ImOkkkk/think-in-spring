package org.liuwy.dependency.injection.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Autowired注解(元标注)
 * 
 * @author ImOkkkk
 * @date 2021/10/12 22:37
 * @since 1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {
    boolean required() default true;
}
