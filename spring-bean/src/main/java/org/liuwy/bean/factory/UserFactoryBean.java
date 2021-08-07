package org.liuwy.bean.factory;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link org.liuwy.ioc.overview.domain.User}Bean的{@link org.springframework.beans.factory.FactoryBean}的实现
 * 
 * @author liuwy
 * @date 2021/8/7 18:17
 * @since 1.0
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
