package com.yczuoxin.demo.annotation;

import com.yczuoxin.demo.custom.TestComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@MyComponentScan2(basePackages = "com.yczuoxin.demo")
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ComponentScanDemo.class);

        context.refresh();

        TestComponent test = context.getBean("testComponent", TestComponent.class);
        System.out.println(test);

        context.close();
    }

}
