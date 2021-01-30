package com.yczuoxin.demo.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link MyApplicationListener} 监听 {@link MyApplicationEvent} 示例
 */
public class MyApplicationEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(MyApplicationEventDemo.class);
        // 注册 MyApplicationListener 到 Spring Bean
        context.register(MyApplicationListener.class);

        context.refresh();

        context.publishEvent(new MyApplicationEvent("Hello, World"));

        context.close();
    }

}
