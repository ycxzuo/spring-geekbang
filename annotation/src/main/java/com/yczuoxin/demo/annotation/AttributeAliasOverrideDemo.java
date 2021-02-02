package com.yczuoxin.demo.annotation;

import com.yczuoxin.demo.custom.TestComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

@MyComponentScan2(packages = "com.yczuoxin.demo.custom")
public class AttributeAliasOverrideDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AttributeAliasOverrideDemo.class);

        context.refresh();



        TestComponent test = context.getBean("testComponent", TestComponent.class);
        System.out.println(test);

        context.close();
    }

}
