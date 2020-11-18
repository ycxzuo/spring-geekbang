package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.bean.factory.DefaultUserFactory;
import com.yczuoxin.demo.bean.factory.UserFactory;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 要使用 ApplicationContext 来获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        // ServiceLoader 的单独使用 demo
        demoServiceLoader();

        ServiceLoader<UserFactory> userServiceLoader = beanFactory.getBean("serviceLoaderFactoryBean", ServiceLoader.class);
        displayUserByServiceLoader(userServiceLoader);

        DefaultUserFactory defaultUserFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(defaultUserFactory.createUser());

        User user = beanFactory.createBean(User.class);
        System.out.println(user);
    }

    private static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class);
        displayUserByServiceLoader(serviceLoader);
    }

    private static void displayUserByServiceLoader(ServiceLoader<UserFactory> userServiceLoader) {
        Iterator<UserFactory> iterator = userServiceLoader.iterator();
        while(iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }

}
