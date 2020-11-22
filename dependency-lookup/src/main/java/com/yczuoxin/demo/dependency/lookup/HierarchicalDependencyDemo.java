package com.yczuoxin.demo.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HierarchicalDependencyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        ConfigurableListableBeanFactory listableBeanFactory = applicationContext.getBeanFactory();
        System.out.println("beanFactory: " + listableBeanFactory);
        System.out.println("beanFactory 的 parentBeanFactory: " + listableBeanFactory.getParentBeanFactory());

        HierarchicalBeanFactory hierarchicalBeanFactory = createHierarchicalBeanFactory();
        listableBeanFactory.setParentBeanFactory(hierarchicalBeanFactory);

        System.out.println("beanFactory: " + listableBeanFactory);
        System.out.println("beanFactory 的 parentBeanFactory: " + listableBeanFactory.getParentBeanFactory());

        // containsLocalBean 中 listableBeanFactory 没有 user 这个 bean
        System.out.println("containsLocalBean 方法中 listableBeanFactory 中是否含有 user bean: " + listableBeanFactory.containsLocalBean("user"));
        System.out.println("containsLocalBean 方法中 hierarchicalBeanFactory 中是否含有 user bean: " + hierarchicalBeanFactory.containsLocalBean("user"));

        // containsBean 中 listableBeanFactory 有 user 这个 bean
        System.out.println("containsBean 方法中 listableBeanFactory 中是否含有 user bean: " + listableBeanFactory.containsBean("user"));
        System.out.println("containsBean 方法中 hierarchicalBeanFactory 中是否含有 user bean: " + hierarchicalBeanFactory.containsBean("user"));

        // myContainsBean 中 listableBeanFactory 有 user 这个 bean
        System.out.println("myContainsBean 方法中 listableBeanFactory 中是否含有 user bean: " + myContainsBean(listableBeanFactory, "user"));
        System.out.println("myContainsBean 方法中 hierarchicalBeanFactory 中是否含有 user bean: " + myContainsBean(hierarchicalBeanFactory, "user"));

        applicationContext.close();
    }

    public static boolean myContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            // 先从父容器开始寻找 bean
            if (myContainsBean(hierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    public static HierarchicalBeanFactory createHierarchicalBeanFactory() {
        return new ClassPathXmlApplicationContext("classpath:META-INF/lookup-content.xml");
    }

}
