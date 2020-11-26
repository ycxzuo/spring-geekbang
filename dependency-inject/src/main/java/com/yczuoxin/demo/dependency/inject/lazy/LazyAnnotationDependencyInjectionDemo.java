package com.yczuoxin.demo.dependency.inject.lazy;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private ObjectProvider<User> objectProvider;

    @Autowired
    private ObjectFactory<User> objectFactory;

    @Autowired
    private ObjectFactory<Collection<User>> collectionObjectFactory;


    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(LazyAnnotationDependencyInjectionDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/lookup-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        LazyAnnotationDependencyInjectionDemo demo = context.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.objectFactory.getObject());

        System.out.println(demo.collectionObjectFactory.getObject());

        System.out.println(demo.objectProvider.getObject());

        demo.objectProvider.forEach(System.out::println);

        // 关闭应用上下文
        context.close();
    }

}
