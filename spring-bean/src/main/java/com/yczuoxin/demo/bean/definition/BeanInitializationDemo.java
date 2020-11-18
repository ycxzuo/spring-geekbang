package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.bean.factory.DefaultUserFactory;
import com.yczuoxin.demo.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);
        context.refresh();
        DefaultUserFactory defaultUserFactory = context.getBean(DefaultUserFactory.class);
        context.close();
    }

    //@Bean(initMethod = "initUserFactory")
    @Bean(initMethod = "createUser")
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
