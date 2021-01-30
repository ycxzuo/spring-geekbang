package com.yczuoxin.demo.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAsync
public class AnnotatedAsyncApplicationEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedAsyncApplicationEventDemo.class);

        context.refresh();

        context.publishEvent(new MyApplicationEvent("Hello, World!"));

        context.close();
    }

    @EventListener
    @Async
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.printf("[线程 %s]：接收到 MyApplicationEvent：%s%n", Thread.currentThread().getName(), event.getSource());
    }

    @Bean
    public Executor taskExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-async-annotation-thread-pool-"));
        return executorService;
    }

}
