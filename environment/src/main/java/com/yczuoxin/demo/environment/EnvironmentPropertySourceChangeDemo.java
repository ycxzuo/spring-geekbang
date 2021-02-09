package com.yczuoxin.demo.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(EnvironmentPropertySourceChangeDemo.class);

        MutablePropertySources mutablePropertySources = context.getEnvironment().getPropertySources();

        Map<String, Object> source = new HashMap<>(16);

        source.put("user.name", "ycxzuo");

        MapPropertySource propertySource = new MapPropertySource("custom-property",source);

        mutablePropertySources.addFirst(propertySource);

        context.refresh();

        EnvironmentPropertySourceChangeDemo propertySourceChangeDemo = context.getBean(EnvironmentPropertySourceChangeDemo.class);

        System.out.println(propertySourceChangeDemo.userName);
        for (PropertySource<?> mutablePropertySource : mutablePropertySources) {
            System.out.println(mutablePropertySource.getName() + ": " + mutablePropertySource.getProperty("user.name"));
        }

        source.put("user.name", "test");

        propertySource = new MapPropertySource("custom-property",source);

        // 在这个类创建的时候，这个属性结果就已经赋值进去了，所以不会变
        System.out.println(propertySourceChangeDemo.userName);
        for (PropertySource<?> mutablePropertySource : mutablePropertySources) {
            System.out.println(mutablePropertySource.getName() + ": " + mutablePropertySource.getProperty("user.name"));
        }

        context.close();
    }

}
