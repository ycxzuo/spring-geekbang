package com.yczuoxin.demo.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 实例
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册自己为配置类
        context.register(ApplicationListenerDemo.class);
        // 注册 ApplicationListener 的方式
        // 方法一：使用 ApplicationListener 接口注册
        //  方法 a：将 ApplicationListener 的实现类注册为一个 Spring Bean
        context.register(MyApplicationListener.class);
        //  方法 b：直接使用 AbstractApplicationContext.addApplicationListener 新增
        context.addApplicationListener(event -> printWithThreadName("ApplicationListener - 接收到事件：" + event));

        context.refresh();

        context.start();

        context.close();
    }

    public static void printWithThreadName(Object print) {
        System.out.printf("[线程 %s]：%s%n", Thread.currentThread().getName(), print);
    }

    /**
     * 方法二：使用 EventListener 注解
     * @param event
     */
    @EventListener
    @Async
    public void onApplicationEvent(ApplicationEvent event) {
        printWithThreadName("ApplicationEvent - 接收到事件：" + event);
    }

    @EventListener
    @Order(0)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        printWithThreadName("ContextRefreshedEvent - 接收到事件：" + event);
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        printWithThreadName("ContextRefreshedEvent1 - 接收到事件：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        printWithThreadName("ContextStartedEvent - 接收到事件：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        printWithThreadName("ContextClosedEvent - 接收到事件：" + event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello, World") {
        });

        applicationEventPublisher.publishEvent("Hello, World");
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            printWithThreadName("MyApplicationListener - 接收到事件：" + event);
        }
    }
}
