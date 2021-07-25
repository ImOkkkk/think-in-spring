package org.liuwy.ioc.java.beans;

/**
 * 描述人的pojo
 *
 * Setter/Getter方法 可写方法(Writable)/可读方法(Readable)
 *
 * @author liuwy
 * @version 1.0
 * @date 2021/7/25 15:58
 */
public class Persion {
    //String to String
    String name;// Property

    //String to Integer
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
