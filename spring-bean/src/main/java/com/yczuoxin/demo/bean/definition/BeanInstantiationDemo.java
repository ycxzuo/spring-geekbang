package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User staticUser = beanFactory.getBean("static-method", User.class);
        System.out.println(staticUser);
        User factoryUser = beanFactory.getBean("factory-method", User.class);
        System.out.println(factoryUser);
        System.out.println("静态工厂和 bean 工厂生产的对象：" + (factoryUser == staticUser));

        User factoryBeanUser = beanFactory.getBean("factory-bean-method", User.class);
        System.out.println(factoryBeanUser);
    }

}
