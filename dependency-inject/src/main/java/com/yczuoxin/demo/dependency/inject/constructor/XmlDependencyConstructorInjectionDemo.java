package com.yczuoxin.demo.dependency.inject.constructor;

import com.yczuoxin.demo.dependency.domain.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        // 创建一个 具有 BeanDefinitionRegistry 功能的 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/xml-dependency-constructor-inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 注册的是 User 而不是 SuperUser
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder.getUser());
    }

}
