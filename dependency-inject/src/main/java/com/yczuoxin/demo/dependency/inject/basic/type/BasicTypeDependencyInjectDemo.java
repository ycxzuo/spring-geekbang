package com.yczuoxin.demo.dependency.inject.basic.type;

import com.yczuoxin.demo.dependency.domain.BasicTypeBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BasicTypeDependencyInjectDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/basic-type-dependency-inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 注册的是 User 而不是 SuperUser
        BasicTypeBean basicTypeBean = beanFactory.getBean(BasicTypeBean.class);
        System.out.println(basicTypeBean);
    }

}
