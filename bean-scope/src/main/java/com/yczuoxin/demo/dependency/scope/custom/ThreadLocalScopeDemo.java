package com.yczuoxin.demo.dependency.scope.custom;

import com.yczuoxin.demo.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return createUser();
    }

    public static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ThreadLocalScopeDemo.class);
        context.addBeanFactoryPostProcessor(beanFactory -> beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope()));
        context.refresh();

        lookupBeanByMultiThread(context);

        injectBeanBySingleThread(context);

        context.close();
    }

    private static void injectBeanBySingleThread(AnnotationConfigApplicationContext context) {
        for (int i = 0; i < 3; i++) {
            User user = context.getBean("user", User.class);
            System.out.println("user: " + user);
        }
    }

    private static void lookupBeanByMultiThread(AnnotationConfigApplicationContext context) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                User user = context.getBean("user", User.class);
                System.out.println("线程[" + Thread.currentThread().getName() + "]user: " + user);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
