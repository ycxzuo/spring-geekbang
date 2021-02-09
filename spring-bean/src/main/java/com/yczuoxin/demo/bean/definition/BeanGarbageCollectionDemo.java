package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.bean.factory.DefaultUserFactory;
import com.yczuoxin.demo.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanLazyInitializationDemo.class);
        context.refresh();

        UserFactory defaultUserFactory = context.getBean(UserFactory.class);

        context.close();

        System.gc();
        DefaultUserFactory factory = (DefaultUserFactory) defaultUserFactory;
        while (!factory.flag) {
            Thread.sleep(10);
        }
        Thread.sleep(1000);
    }
}
