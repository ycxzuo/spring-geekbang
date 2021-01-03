package com.yczuoxin.demo;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@EnableAutoConfiguration
public class CustomizedMessageSourceDemo {

    /**
     *   Spring Boot 启动类的资源加载时 primary 的，优先级高，根据 BeanDefinition 的先来先加载的原则，
     *   所以注册了 ReloadableResourceBundleMessageSource 为 MessageSource
     */
    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceDemo.class).web(WebApplicationType.NONE).run(args);
        if (applicationContext.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            System.out.println(applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class));

            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
            // 如果是默认的 empty messageSource 的话，是不会有 BeanDefinition 存在的
            // org.springframework.beans.factory.config.SingletonBeanRegistry.registerSingleton 注册的 Bean 没有 BeanDefinition
            // 其生命周期不由 Spring 管理
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
        }

        applicationContext.close();
    }
}
