package com.yczuoxin.demo.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    public Environment environment;

    @Autowired
    public Environment autowiredEnvironment;

    public ApplicationContext applicationContext;

    @Autowired
    public ApplicationContext autowiredApplicationContext;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectingEnvironmentDemo.class);

        context.refresh();

        InjectingEnvironmentDemo injectingEnvironmentDemo = context.getBean(InjectingEnvironmentDemo.class);

        System.out.println(injectingEnvironmentDemo.environment);

        System.out.println("injectingEnvironmentDemo.environment == injectingEnvironmentDemo.autowiredEnvironment: " + (injectingEnvironmentDemo.environment == injectingEnvironmentDemo.autowiredEnvironment));

        System.out.println("injectingEnvironmentDemo.environment == injectingEnvironmentDemo.applicationContext.getEnvironment: " + (injectingEnvironmentDemo.environment == injectingEnvironmentDemo.applicationContext.getEnvironment()));

        System.out.println("injectingEnvironmentDemo.applicationContext == injectingEnvironmentDemo.autowiredApplicationContext: " + (injectingEnvironmentDemo.applicationContext == injectingEnvironmentDemo.autowiredApplicationContext));

        context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
