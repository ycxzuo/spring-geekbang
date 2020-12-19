package com.yczuoxin.demo.bean.lifecycle.parse;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 元信息配置的阶段
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 配置 Properties 的 BeanDefinition
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        // 资源路径
        String location = "META-INF/user.properties";
        // 用 Classpath 的 Resource 装载 Properties 数据
        Resource resource = new ClassPathResource(location);
        // 将 ASCII 的编码方式改为 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 返回读取到的 Bean Definition 数量
        int beanNumbers = reader.loadBeanDefinitions(encodedResource);
        System.out.println("读取到的 Bean Definition 数量为：" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
