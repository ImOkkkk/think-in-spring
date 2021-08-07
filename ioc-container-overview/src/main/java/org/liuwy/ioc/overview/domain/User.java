package org.liuwy.ioc.overview.domain;

/**
 * 用户类
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/7/26 21:32
 */
public class User {
    private Long id;
    private String name;

    public static User createUser() {
        User user = new User();
        user.setName("i3-ImOkkkk");
        user.setId(1L);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
