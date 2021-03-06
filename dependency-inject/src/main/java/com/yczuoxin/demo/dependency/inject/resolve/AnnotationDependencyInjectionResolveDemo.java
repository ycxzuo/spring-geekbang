package com.yczuoxin.demo.dependency.inject.resolve;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

public class AnnotationDependencyInjectionResolveDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private User user;

    @Autowired
    private Collection<User> users;

    @Inject
    private User injectUser;

    @Autowired
    private Optional<User> optionalUser;

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(AnnotationDependencyInjectionResolveDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/lookup-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        AnnotationDependencyInjectionResolveDemo demo = context.getBean(AnnotationDependencyInjectionResolveDemo.class);

        System.out.println("lazyUser: " + demo.lazyUser);
        System.out.println("user: " + demo.user);
        System.out.println("users: " + demo.users);
        System.out.println("optionalUser: " + demo.optionalUser);
        System.out.println("injectUser: " + demo.injectUser);

        System.out.println("@injected == @autowired:" + (demo.user == demo.injectUser));


        // 关闭应用上下文
        context.close();
    }
}
