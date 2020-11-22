package com.yczuoxin.demo.dependency.lookup;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafetyDependencyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyDemo.class);
        applicationContext.refresh();

        // 获取单一 bean: BeanFactory 的 getBean 方法
        displayBeanFactoryGetBean(applicationContext);
        // 获取单一 bean: ObjectFactory 的 getObject 方法
        displayObjectFactoryGetObject(applicationContext);
        // 获取单一 bean: ObjectProvider 的 getIfAvailable 方法
        displayObjectProviderGetIfAvailable(applicationContext);

        // 获取集合 bean: ListableBeanFactory 的 getBeansOfType 方法
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 获取集合 bean: ObjectProvider 的 Stream 方法
        displayObjectProviderStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(BeanFactory beanFactory) {
        ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
        printErrorMessage(() -> beanProvider.forEach(System.out::println), "displayObjectProviderStreamOps");
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
        printErrorMessage(() -> listableBeanFactory.getBeansOfType(User.class), "displayListableBeanFactoryGetBeansOfType");
    }

    private static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
        ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
        printErrorMessage(objectProvider::getIfAvailable, "displayObjectProviderGetIfAvailable");
    }

    private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printErrorMessage(objectFactory::getObject, "displayObjectFactoryGetObject");
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printErrorMessage(() -> beanFactory.getBean("user"), "displayBeanFactoryGetBean");
    }

    private static void printErrorMessage(Runnable runnable, String message) {
        System.out.println(message);
        System.out.println("---------------------------------");
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
