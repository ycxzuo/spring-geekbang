package com.yczuoxin.demo.dependency.inject.field;

import com.yczuoxin.demo.dependency.domain.UserHolder;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private UserHolder userHolder1;

    @Resource
    private UserHolder userHolder;


    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(AnnotationDependencyFieldInjectionDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        AnnotationDependencyFieldInjectionDemo demo = context.getBean(AnnotationDependencyFieldInjectionDemo.class);
        System.out.println(demo.userHolder);

        System.out.println(demo.userHolder1);

        UserHolder userHolder = context.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder.getUser());

        System.out.println(userHolder == demo.userHolder);

        // 关闭应用上下文
        context.close();
    }


    @Bean
    public UserHolder userHolder(User user) {
        // 构造器注入，类型是 SuperUser
        return new UserHolder(user);
    }
}
