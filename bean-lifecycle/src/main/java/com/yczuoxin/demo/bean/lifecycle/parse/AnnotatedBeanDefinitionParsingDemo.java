package com.yczuoxin.demo.bean.lifecycle.parse;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 基于注解的 Bean Definition 解析
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // AnnotatedBeanDefinitionReader 没有实现 BeanDefinitionReader 接口，因为它不是面向资源的
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        reader.register(AnnotatedBeanDefinitionParsingDemo.class);

        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        System.out.println("读取到的 Bean Definition 数量为：" + (beanDefinitionCountAfter - beanDefinitionCountBefore));

        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",
                AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }

}
