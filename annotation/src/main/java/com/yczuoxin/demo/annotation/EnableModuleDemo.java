package com.yczuoxin.demo.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(EnableModuleDemo.class);

        context.refresh();

        System.out.println(context.getBean("helloWorld", String.class));

        context.close();
    }

}
