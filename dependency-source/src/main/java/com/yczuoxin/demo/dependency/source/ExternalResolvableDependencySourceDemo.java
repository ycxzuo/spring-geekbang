package com.yczuoxin.demo.dependency.source;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

@Configuration // 这个是必须的
@PropertySource(value = "classpath:/META-INF/default.properties")
public class ExternalResolvableDependencySourceDemo {

    @Value("${usr.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${usr.path}")
    private Resource resource;

    @PostConstruct
    public void printValue() {
        System.out.println(id);
        System.out.println(name);
        System.out.println(resource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ExternalResolvableDependencySourceDemo.class);

        annotationConfigApplicationContext.refresh();

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id",1);

        annotationConfigApplicationContext.registerBeanDefinition("hello",builder.getBeanDefinition());

        User hello = annotationConfigApplicationContext.getBean("hello", User.class);
        System.out.println(hello);

        annotationConfigApplicationContext.close();
    }

}
