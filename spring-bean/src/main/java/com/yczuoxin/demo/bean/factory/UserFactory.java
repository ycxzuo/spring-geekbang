package com.yczuoxin.demo.bean.factory;

import com.yczuoxin.demo.domain.User;

public interface UserFactory {

    default User createUser(){
        System.out.println("createUser 调用了");
        User user = new User();
        user.setId(1L);
        user.setName("zx");
        return user;
    }

}
