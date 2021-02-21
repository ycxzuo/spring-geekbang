package com.yczuoxin.demo.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

public class MBeanLifecycleDemo {

    public static void main(String[] args) throws IOException {
        System.setProperty(LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME, "yczuoxin");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.setDisplayName("test");

        context.register(MBeanLifecycleDemo.class);

        context.refresh();

        System.in.read();

        context.close();
    }

}
