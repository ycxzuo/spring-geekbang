package com.yczuoxin.demo.environment;

import com.yczuoxin.demo.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyPlaceholderDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/property-placeholder.xml");

        User user = context.getBean("user", User.class);

        System.out.println(user);

        context.close();
    }

}
