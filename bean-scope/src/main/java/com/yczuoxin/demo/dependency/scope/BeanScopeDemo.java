package com.yczuoxin.demo.dependency.scope;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * singleton Bean 无论是依赖查找还是依赖注入，都是唯一的 Bean
 * prototype Bean 无论是依赖查找还是依赖注入，都是新生成的 Bean
 *
 * 使用依赖注入集合类型，singleton Bean 和 prototype Bean 都会存在一个，并且 prototype Bean 是重新生成的一个
 *
 * 无论是 singleton Bean 还是 prototype Bean，都会执行初始化方法回调
 * singleton Bean 会执行销毁方法回调
 * prototype Bean 不会执行销毁方法回调
 *
 */
import java.util.Map;

public class BeanScopeDemo implements DisposableBean {

    @Bean
    public User singleton() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototype(){
        return createUser();
    }

    @Autowired
    @Qualifier("singleton")
    private User singleton1;

    @Autowired
    @Qualifier("singleton")
    private User singleton2;

    @Autowired
    @Qualifier("prototype")
    private User prototype1;

    @Autowired
    @Qualifier("prototype")
    private User prototype2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeDemo.class);
        context.addBeanFactoryPostProcessor(beanFactory1 -> {
            beanFactory1.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    System.out.println(beanName + " Bean，名称：" + bean.getClass().getName() + " 正在进行初始化回调...");
                    // 此处必须把 Bean 返回去，不然就没有这个 Bean 了
                    return bean;
                }
            });
        });
        context.refresh();

        lookupBeanByName(context);

        injectBeanByName(context);

        context.close();
    }

    private static void injectBeanByName(AnnotationConfigApplicationContext context) {
        BeanScopeDemo demo = context.getBean(BeanScopeDemo.class);
        System.out.println("singleton1 user: " + demo.singleton1);
        System.out.println("singleton2 user: " + demo.singleton2);

        System.out.println("prototype1 user: " + demo.prototype1);
        System.out.println("prototype2 user: " + demo.prototype2);

        System.out.println("users: " + demo.users);
    }

    private static void lookupBeanByName(AnnotationConfigApplicationContext context) {
        for (int i = 0; i < 3; i++) {
            User singleton = context.getBean("singleton", User.class);
            System.out.println("singleton user: " + singleton);

            User prototype = context.getBean("prototype", User.class);
            System.out.println("prototype user: " + prototype);
        }
    }

    @Override
    public void destroy() throws Exception {
        this.prototype1.destroy();
        this.prototype2.destroy();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition(beanName);
            if (userBeanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }
    }
}
