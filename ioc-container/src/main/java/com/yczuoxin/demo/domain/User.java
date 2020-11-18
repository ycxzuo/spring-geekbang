package com.yczuoxin.demo.domain;


import lombok.Data;

@Data
public class User {

    private Long id;

    private String name;

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("zx");
        return user;
    }

}
