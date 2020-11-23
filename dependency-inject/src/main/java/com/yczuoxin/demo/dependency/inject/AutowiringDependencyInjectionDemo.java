package com.yczuoxin.demo.dependency.inject;

import com.yczuoxin.demo.dependency.domain.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class AutowiringDependencyInjectionDemo {

    public static void main(String[] args) {
        // 创建一个 具有 BeanDefinitionRegistry 功能的 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/autowiring-dependency-setter-inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        // byType 是 SuperUser，byName 是 User
        System.out.println(userHolder.getUser());
    }

}
