package com.yczuoxin.demo.dependency.inject;

import com.yczuoxin.demo.dependency.domain.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(AnnotationDependencyInjectionDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        // 手动设置属性，跟输入的名称去查找，而不是类型
        //builder.addPropertyReference("user", "user");
        builder.addPropertyReference("user", "superUser");

        BeanDefinition beanDefinition = builder.getBeanDefinition();

        context.registerBeanDefinition("userHolder", beanDefinition);

        // 启动应用上下文
        context.refresh();

        UserHolder userHolder = context.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder.getUser());

        // 关闭应用上下文
        context.close();
    }

}
