package com.yczuoxin.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncApplicationEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(MyApplicationListener.class);

        context.refresh();

        ApplicationEventMulticaster eventMulticaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);

        if (eventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster multicaster = (SimpleApplicationEventMulticaster) eventMulticaster;
            ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-async-thread-pool-"));
            multicaster.setTaskExecutor(executorService);
            // 添加关闭的事件监听来关闭线程池，否则无法正常关闭程序
            eventMulticaster.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
                if (!executorService.isShutdown()) {
                    executorService.shutdown();
                }
            });
            // 增加异常处理机制
            multicaster.setErrorHandler(e -> System.err.println("捕获到异常：" + e.getMessage()));
        }
        // 显式的抛出一个 RuntimeException 异常
        context.addApplicationListener((ApplicationListener<MyApplicationEvent>) event -> {
            throw new RuntimeException("异常测试");
        });

        context.publishEvent(new MyApplicationEvent("Hello, World!"));

        context.close();
    }

}
