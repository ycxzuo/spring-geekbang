package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDefinitionAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User myUser = beanFactory.getBean("myUser", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("myUser 是否与 user 相同：" + (myUser == user));
    }

}
