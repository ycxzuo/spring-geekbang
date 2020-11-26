package com.yczuoxin.demo.dependency.inject.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareInterfaceDependencyInjectDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(AwareInterfaceDependencyInjectDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/inject-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory()); // ture
        System.out.println(applicationContext == context); // ture

        // 关闭应用上下文
        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectDemo.applicationContext = applicationContext;
    }
}
