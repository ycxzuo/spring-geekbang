package com.yczuoxin.demo.container;

import com.yczuoxin.demo.dependency.lookup.DependencyLockupDemo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryAsIoCContainer {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/lookup-content.xml";
        int number = reader.loadBeanDefinitions(location);
        System.out.println("发现注册到的 bean 数量为：" + number);

        DependencyLockupDemo.lockupCollectionByType(beanFactory);
    }

}
