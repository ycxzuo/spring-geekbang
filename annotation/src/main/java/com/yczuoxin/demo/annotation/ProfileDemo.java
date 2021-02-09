package com.yczuoxin.demo.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 获取可配置的 Environment
        ConfigurableEnvironment environment = context.getEnvironment();
        // 设置默认的兜底 Profile，如果没有设置活跃的 Profile 就会以 兜底方案进行，默认是 default
        environment.setDefaultProfiles("test1");
        // 设置活跃的 Profile，会覆盖兜底的 Profile
        // VM 参数中 -Dspring.profiles.active=test2 也可以设置 Profile
        //environment.addActiveProfile("test2");

        context.register(ProfileDemo.class);

        context.refresh();

        System.out.println(context.getBean("echo", String.class));

        context.close();
    }

    @Bean(value = "echo")
    @Profile("test1")
    public String test1() {
        return "test1";
    }

    @Bean(value = "echo")
    //@Profile("test2")
    @Conditional(Test2Condition.class) // 利用 @Conditional 代替 @Profile
    public String test2() {
        return "test2";
    }

}
