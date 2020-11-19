package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanLazyInitializationDemo.class);
        context.refresh();

        UserFactory defaultUserFactory = context.getBean(UserFactory.class);

        context.close();

        Thread.sleep(5000L);
        System.gc();
        Thread.sleep(5000L);
    }
}
