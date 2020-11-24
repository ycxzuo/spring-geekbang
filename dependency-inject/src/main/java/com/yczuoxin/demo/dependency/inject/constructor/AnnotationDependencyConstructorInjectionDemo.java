package com.yczuoxin.demo.dependency.inject.constructor;

import com.yczuoxin.demo.dependency.domain.UserHolder;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(AnnotationDependencyConstructorInjectionDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        UserHolder userHolder = context.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder.getUser());

        // 关闭应用上下文
        context.close();
    }


    @Bean
    public UserHolder userHolder(User user) {
        // 构造器注入，类型是 SuperUser
        return new UserHolder(user);
    }
}
