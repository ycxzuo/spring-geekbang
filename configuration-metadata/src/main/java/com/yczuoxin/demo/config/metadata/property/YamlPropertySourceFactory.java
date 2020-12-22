package com.yczuoxin.demo.config.metadata.property;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 注册 {@Link PropertySourceFactory} 作为 Yaml 的解析工厂
 *
 * @author zuoxin
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        // 用于解析 Yaml 的 Factory Bean
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        // 注册资源
        factoryBean.setResources(resource.getResource());

        Properties yamlProperties = factoryBean.getObject();

        return new PropertiesPropertySource("yamlProperties", yamlProperties);
    }
}
