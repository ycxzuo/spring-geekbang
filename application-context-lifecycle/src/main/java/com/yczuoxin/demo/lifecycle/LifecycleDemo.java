package com.yczuoxin.demo.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;

public class LifecycleDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBeanDefinition("myLifecycle", BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition());

        context.refresh();

        context.start();

        context.stop();

        context.close();
    }

}
