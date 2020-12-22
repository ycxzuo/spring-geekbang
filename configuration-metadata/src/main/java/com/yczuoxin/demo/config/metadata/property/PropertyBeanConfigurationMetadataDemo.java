package com.yczuoxin.demo.config.metadata.property;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class PropertyBeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        // 创建具有 BeanDefinitionRegister 能力的 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建解析 properties 的解析器
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        // 资源路径
        String location = "META-INF/bean-config-definitions.properties";
        // 创建 Resource
        Resource resource = new ClassPathResource(location);
        // 进行 properties 的编码修改，由 ISO-8859-1 更换为 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 加载资源
        reader.loadBeanDefinitions(encodedResource);
        // BeanFactory 依赖查找 User 这个 Bean
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

    }

}
