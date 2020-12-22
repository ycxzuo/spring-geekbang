package com.yczuoxin.demo.config.metadata.property;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class XmlBeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        // 创建 User 对象的 BeanDefinition 构造器
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 获取 User 对象的 BeanDefinition
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // 设置 BeanDefinition 的附加属性值（附加属性，对于 BeanDefinition 的使用没有任何影响）
        beanDefinition.setAttribute("name", "ycxzuo");
        // 设置 BeanDefinition 的来源（附加属性，对于 BeanDefinition 的使用没有任何影响）
        beanDefinition.setSource(XmlBeanConfigurationMetadataDemo.class);
        // 创建有 BeanDefinition 的注册功能的 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册 BeanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);
        // 在 Bean 的初始化阶段后期修改 Bean 的属性
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if ("user".equals(beanName) && bean instanceof User) {
                    BeanDefinition bd = beanFactory.getBeanDefinition("user");
                    // 获取 BeanDefinition 的 Source 属性
                    if (XmlBeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        User user = (User) bean;
                        // 获取 BeanDefinition 的 attribute 属性
                        String name = (String) bd.getAttribute("name");
                        ((User) bean).setName(name);
                        return user;
                    }
                }
                return bean;
            }
        });
        // 获取 user
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

}
