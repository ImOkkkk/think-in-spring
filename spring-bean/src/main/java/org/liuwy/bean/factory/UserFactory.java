package org.liuwy.bean.factory;

import org.liuwy.ioc.overview.domain.User;

/**
 * {@link User}工厂类
 * @author liuwy
 * @version 1.0
 * @date 2021/8/7 18:03
 */
public interface UserFactory {
    default User createUser(){
        return User.createUser();
    }
}
