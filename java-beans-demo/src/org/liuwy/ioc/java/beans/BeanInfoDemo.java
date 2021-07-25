package org.liuwy.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo} 示例
 *
 * @author liuwy
 * @version 1.0
 * @date 2021/7/25 16:03
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Persion.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            // PropertyDescriptor 允许添加属性编辑器-PropertyEditor
            // GUI->text(String)->PropertyType
            // age->Integer
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyName = propertyDescriptor.getName();
            if ("age".equals(propertyName)) {// 为"age"字段/属性增加PropertyEditor
                // String->Integer
                // Integer.valueOf("")
                propertyDescriptor.setPropertyEditorClass(String2IntegerPropertyEditor.class);
//                propertyDescriptor.createPropertyEditor()
            }
        });
    }

    static class String2IntegerPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
