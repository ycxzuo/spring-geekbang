package com.yczuoxin.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.HashSet;
import java.util.Set;

public class HierarchicalSpringEventDemo {
    public static void main(String[] args) {
        // 父容器
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        // 子容器
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);

        // 设置父容器
        currentContext.setParent(parentContext);

        // 启动父容器
        parentContext.refresh();
        // 启动子容器
        currentContext.refresh();

        // 关闭子容器
        currentContext.close();
        // 关闭父容器
        parentContext.close();

    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {
        // 因为父容器和子容器拿到的是两个 MyListener，所以要用静态变量
        private static Set<ApplicationContextEvent> processed = new HashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            // 防止事件多次触发
            if (processed.add(event)) {
                System.out.printf("Spring 容器 [id：%s] 接收到事件：%s%n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
            }
        }
    }
}
