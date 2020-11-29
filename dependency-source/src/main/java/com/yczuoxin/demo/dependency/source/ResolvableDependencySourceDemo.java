package com.yczuoxin.demo.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void printValue() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DependencySourceDemo.class);

        // 这个方法会在 invokeBeanFactoryPostProcessors 方法中回调
        annotationConfigApplicationContext.addBeanFactoryPostProcessor(beanFactory ->
                beanFactory.registerResolvableDependency(String.class, "Hello, World!"));

        annotationConfigApplicationContext.refresh();

        annotationConfigApplicationContext.close();
    }

}
