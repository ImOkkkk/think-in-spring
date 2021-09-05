package org.liuwy.dependency.injection;

import org.liuwy.ioc.overview.domain.User;

/**
 * {@link User}的Holder对象
 * @author ImOkkkk
 * @date 2021/9/5 21:36
 * @since 1.0
 */
public class UserHolder {

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
