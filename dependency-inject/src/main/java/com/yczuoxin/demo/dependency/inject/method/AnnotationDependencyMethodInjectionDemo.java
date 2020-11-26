package com.yczuoxin.demo.dependency.inject.method;

import com.yczuoxin.demo.dependency.domain.UserHolder;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder1;

    private UserHolder userHolder;

    @Autowired
    public void initUserHolder1(UserHolder userHolder1) {
        this.userHolder1 = userHolder1;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder) {
        this.userHolder = userHolder;
    }


    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(AnnotationDependencyMethodInjectionDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        AnnotationDependencyMethodInjectionDemo demo = context.getBean(AnnotationDependencyMethodInjectionDemo.class);
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
