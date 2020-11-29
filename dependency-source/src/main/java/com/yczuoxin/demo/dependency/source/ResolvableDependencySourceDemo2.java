package com.yczuoxin.demo.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencySourceDemo2 {

    @Autowired
    private String value;

    @PostConstruct
    public void printValue() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ResolvableDependencySourceDemo2.class);
        // 直接获取 BeanFactory 来进行注册
        ConfigurableListableBeanFactory beanFactory = annotationConfigApplicationContext.getBeanFactory();
        beanFactory.registerResolvableDependency(String.class, "Hello,World!");

        annotationConfigApplicationContext.refresh();



        annotationConfigApplicationContext.close();
    }

}
