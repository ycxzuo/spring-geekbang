package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.bean.factory.DefaultUserFactory;
import com.yczuoxin.demo.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry beanFactory = context.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        context.refresh();

        UserFactory userFactoryByLookup = context.getBean("userFactory", UserFactory.class);

        System.out.println("userFactoryByLookup == userFactory: " + (userFactoryByLookup == userFactory)); // true

        context.close();
    }

}
