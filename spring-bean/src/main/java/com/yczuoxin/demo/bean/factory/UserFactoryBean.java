package com.yczuoxin.demo.bean.factory;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<User> getObjectType() {
        return User.class;
    }
}
