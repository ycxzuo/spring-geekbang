package com.yczuoxin.demo.dependency.lookup;

import com.yczuoxin.demo.annotation.Super;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLockupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/lookup-content.xml");
        lockupInRealTime(beanFactory);
        lockupInLazy(beanFactory);
        lockupByType(beanFactory);
        lockupCollectionByType(beanFactory);
        lockUpByAnnotation(beanFactory);
    }

    private static void lockUpByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansWithAnnotation = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("---------------------------------------------");
            for (String s : beansWithAnnotation.keySet()) {
                System.out.println(s + ": " + beansWithAnnotation.get(s));
            }
            System.out.println("---------------------------------------------");
        }
    }

    public static void lockupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("---------------------------------------------");
            for (String s : beansOfType.keySet()) {
                System.out.println(s + ": " + beansOfType.get(s));
            }
            System.out.println("---------------------------------------------");
        }
    }

    private static void lockupByType(BeanFactory beanFactory) {
        User user =  beanFactory.getBean(User.class);
        System.out.println("实时查找" + user);
    }

    private static void lockupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找" + user);
    }

    private static void lockupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找" + user);
    }

}
