package com.yczuoxin.demo.dependency.inject;

import com.yczuoxin.demo.domain.User;
import com.yczuoxin.demo.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/inject-content.xml");

        // bean 的来源1：自定义的 bean
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);

        System.out.println(userRepository.getUsers());

        // 依赖注入
        // bean 的来源2：依赖注入的 bean（内建依赖）
        System.out.println("依赖注入 BeanFactory：" + userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == userRepository); // false

        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == beanFactory); // false

        ObjectFactory<ApplicationContext> contextObjectFactory = userRepository.getContextObjectFactory();
        System.out.println(contextObjectFactory.getObject() == beanFactory); // true

        // bean 的来源3：容器内自建 bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("依赖注入 Environment：" + environment);
    }

    /**
     * BeanFactory 和 ApplicationContext 谁才是真正的 IoC 容器
     */
    private static void whoIsIocContainer(UserRepository userRepository, BeanFactory beanFactory) {
        // 两者都是，但是 BeanFactory 是基本的 IoC 容器，提供基本功能
        // 依赖查找 （拿不到 BeanFactory）
        // System.out.println(beanFactory.getBean(BeanFactory.class));

        // ApplicationContext 就是 BeanFactory
        // BeanFactory 提供了基本的 IoC 的能力
        // ApplicationContext 是 BeanFactory 的一个扩展，具有更多的企业级的能力，并且组合了 BeanFactory (org.springframework.context.support.AbstractRefreshableApplicationContext)
        // System.out.println(userRepository.getBeanFactory() == userRepository); // false
        // 因为是组合关系，所以不等同

        // 所以探究底层的时候用 getBeanFactory 而非直接替换
    }
}