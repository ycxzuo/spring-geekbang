package com.yczuoxin.demo.config.metadata.property;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@ImportResource("classpath:/META-INF/lookup-content.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/bean-config-definitions.properties")
public class AnnotatedConfigurationIoCContainerMetadataDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedConfigurationIoCContainerMetadataDemo.class);
        context.refresh();
        // 注解注册的 Bean，BeanName 就是类的全限定名
        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        context.close();
    }
}
