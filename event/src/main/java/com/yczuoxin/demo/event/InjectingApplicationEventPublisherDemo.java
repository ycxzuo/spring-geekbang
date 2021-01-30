package com.yczuoxin.demo.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware, BeanPostProcessor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        // 调用顺序决定了执行顺序
        // 3
        applicationEventPublisher.publishEvent(new MyApplicationEvent("event from applicationEventPublisher"));
        // 4
        applicationContext.publishEvent(new MyApplicationEvent("event from applicationContext"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectingApplicationEventPublisherDemo.class);

        context.addApplicationListener(new MyApplicationListener());

        context.refresh();

        context.close();
    }

    // ApplicationContextAwareProcessor 的 invokeAwareInterfaces 方法决定了执行顺序
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 2
        applicationContext.publishEvent(new MyApplicationEvent("event from ApplicationContextAware"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // 1
        applicationEventPublisher.publishEvent(new MyApplicationEvent("event from ApplicationEventPublisherAware"));
    }
}
