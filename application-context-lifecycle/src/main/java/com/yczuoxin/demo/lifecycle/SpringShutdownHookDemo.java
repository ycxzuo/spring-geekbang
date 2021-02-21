package com.yczuoxin.demo.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class SpringShutdownHookDemo {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> System.out.printf("[线程 %s] 处理事件 ContextClosedEvent%n", Thread.currentThread().getName()));

        context.refresh();

        context.registerShutdownHook();

        System.in.read();

        context.close();
    }

}
