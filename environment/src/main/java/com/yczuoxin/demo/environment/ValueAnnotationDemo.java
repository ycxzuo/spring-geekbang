package com.yczuoxin.demo.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ValueAnnotationDemo.class);

        context.refresh();

        ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);
        // org.springframework.beans.factory.support.AutowireCandidateResolver.getSuggestedValue 抽取 @Value 的内容
        // org.springframework.beans.factory.support.AbstractBeanFactory.resolveEmbeddedValue 解析 @Value 中对应的数据
        System.out.println(valueAnnotationDemo.userName);

        context.close();
    }

}
