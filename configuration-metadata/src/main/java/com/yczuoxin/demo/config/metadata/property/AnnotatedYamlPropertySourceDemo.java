package com.yczuoxin.demo.config.metadata.property;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource(name = "user",
        value = "classpath:/META-INF/user-bean.yaml",
        factory = YamlPropertySourceFactory.class,
        encoding = "utf-8")
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public static User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedYamlPropertySourceDemo.class);
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();
    }

}
