package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.bean.factory.DefaultUserFactory;
import com.yczuoxin.demo.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanLazyInitializationDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanLazyInitializationDemo.class);
        context.refresh();

        System.out.println("spring 应用上下文已启动。。。");

        UserFactory defaultUserFactory = context.getBean(UserFactory.class);
        System.out.println(defaultUserFactory);
        context.close();
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyMethod")
    //@Bean(initMethod = "createUser")
    @Lazy(value = false)
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
