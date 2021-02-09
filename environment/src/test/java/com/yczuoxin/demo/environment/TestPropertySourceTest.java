package com.yczuoxin.demo.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@TestPropertySource(
        locations = "/META-INF/test.properties",
        properties = "user.name = ycxzuo"
        )
@ContextConfiguration(classes = TestPropertySourceTest.class) // Spring 注解驱动测试注解
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        Assert.assertEquals("ycxzuo", userName);
        for (PropertySource<?> mutablePropertySource : environment.getPropertySources()) {
            System.out.println(mutablePropertySource.getName() + ": " + mutablePropertySource.getProperty("user.name"));
        }
    }

}
