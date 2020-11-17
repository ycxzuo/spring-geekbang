package com.yczuoxin.demo.container;

import com.yczuoxin.demo.dependency.lookup.DependencyLockupDemo;
import com.yczuoxin.demo.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ApplicationContextAsIoCContainer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationContextAsIoCContainer.class);
        applicationContext.refresh();
        DependencyLockupDemo.lockupCollectionByType(applicationContext);
    }

    @Bean
    public User getUser() {
        User user = new User();
        user.setId(3L);
        user.setName("zxc");
        return user;
    }

}
