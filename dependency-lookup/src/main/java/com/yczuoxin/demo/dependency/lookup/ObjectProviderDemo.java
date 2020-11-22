package com.yczuoxin.demo.dependency.lookup;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ObjectProviderDemo { // @Configuration 注解不是非必需的

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> stringProvider = applicationContext.getBeanProvider(String.class);
        String string = stringProvider.getObject(String.class);
        System.out.println("单独查找的 String bean: "+ string);
        stringProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userProvider = applicationContext.getBeanProvider(User.class);
        User user = userProvider.getIfAvailable(User::createUser);
        System.out.println("查找对象 User: " + user);
    }



    @Bean
    @Primary
    public String helloWorld() { // Bean 如果没有命名，就会以方法名命名
        return "Hello,World!";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        String object = beanProvider.getObject();
        System.out.println(object);
    }

}
