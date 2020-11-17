package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Import(AnnotationBeanDefinitionDemo.Config.class) // 3. 通过 @Import 进行配置
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册配置类 Configuration Class
        context.register(AnnotationBeanDefinitionDemo.class);

        // 命名式注册 user
        registerUserBeanDefinition(context, "yczuoxin-user");
        // 非命名式注册 user
        registerUserBeanDefinition(context);

        // 启动 Spring 应用上下文
        context.refresh();
        System.out.println("类型为 User 的 bean 有：" + context.getBeansOfType(User.class));
        System.out.println("类型为 Config 的 bean 有：" + context.getBeansOfType(Config.class));
    }

    @Component // 2. 通过 @Component 进行配置
    public static class Config {
        @Bean(name = {"user", "myUser"}) // 1. 通过 @Bean 进行配置
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("yczuoxin");
            return user;
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1L).addPropertyValue("name", "yczuoxin");
        if (StringUtils.hasText(beanName)) {
            // 命名方式配置元信息
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

}
