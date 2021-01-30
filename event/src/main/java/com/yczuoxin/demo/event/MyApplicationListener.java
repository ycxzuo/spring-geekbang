package com.yczuoxin.demo.event;

import org.springframework.context.ApplicationListener;

/**
 * {@link ApplicationListener} 示例
 */
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.printf("[线程 %s]：接收到 MyApplicationEvent：%s%n", Thread.currentThread().getName(), event.getSource());
    }
}
