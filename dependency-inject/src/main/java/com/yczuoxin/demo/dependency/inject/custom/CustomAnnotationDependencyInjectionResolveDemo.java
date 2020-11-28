package com.yczuoxin.demo.dependency.inject.custom;

import com.yczuoxin.demo.dependency.annotation.MyAutowired;
import com.yczuoxin.demo.dependency.annotation.MyInject;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

public class CustomAnnotationDependencyInjectionResolveDemo {


    @Autowired
    private User user;

    @Inject
    private User injectUser;

    @MyAutowired
    private User myAutowiredUser;

    @MyInject
    private User MyInjectUser;

//    /**
//     * 会使所有的注解都失效, 原因如下：
//     * 初始化 Spring 上下文（refresh 方法调用）后，先会注册所有的 beanPostProcessors，再去初始化各个 Bean；
//     * 如果这里加了 AutowiredAnnotationBeanPostProcessor，那么就会先在注册所有 beanPostProcessors 的同时初始化这个 Bean
//     * 此时被注入的 Bean 还没有生成，所以就全部都是 null
//     *
//     * 结果，所有的 Bean 都是 null
//     *
//     * @return
//     */
//    @Bean(name = "myBeanPostProcessor")
//    public AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(MyInject.class);
//        return autowiredAnnotationBeanPostProcessor;
//    }

//    /**
//     * static 可以让 Bean 的注册提前注册，因为在注册所有的 beanPostProcessors 时，
//     * 不需要初始化 CustomAnnotationDependencyInjectionResolveDemo 这个 Bean，
//     * 所以会在 注册所有的 beanPostProcessors 之后来初始化这个 Bean，此时被注入的 Bean 已经生成
//     *
//     * 结果会拿到所有的 Bean
//     *
//     * @return
//     */
//    @Bean(name = "myBeanPostProcessor")
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(MyInject.class);
//        return autowiredAnnotationBeanPostProcessor;
//    }

//    /**
//     * beanPostProcessor 注入在原本的 AutowiredAnnotationBeanPostProcessor 之后，所以导致先初始化了 AutowiredAnnotationBeanPostProcessor，
//     * 然后我们的 AutowiredAnnotationBeanPostProcessor 覆盖了之前原本的 AutowiredAnnotationBeanPostProcessor
//     *
//     * 结果只有 @MyInject 标注的 Bean 被注入，解决方法可以将所有的用到的注入的注解也一起加上
//     *
//     * @return
//     */
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(MyInject.class);
////        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
////        annotations.addAll(Arrays.asList(Inject.class, Autowired.class, MyInject.class));
////        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(annotations);
//        return autowiredAnnotationBeanPostProcessor;
//    }

    /**
     * 在注册原本的 AutowiredAnnotationBeanPostProcessor 之前先把这个 AutowiredAnnotationBeanPostProcessor 注册进去
     * 等于有两个 AutowiredAnnotationBeanPostProcessor 在 beanPostProcessors 之中
     *
     * 结果所有的 Bean 都不注入
     *
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3) // AutowiredAnnotationBeanPostProcessor 的 Order 是 Ordered.LOWEST_PRECEDENCE - 2
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(MyInject.class);
        return autowiredAnnotationBeanPostProcessor;
    }

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(CustomAnnotationDependencyInjectionResolveDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/lookup-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        CustomAnnotationDependencyInjectionResolveDemo demo = context.getBean(CustomAnnotationDependencyInjectionResolveDemo.class);

        System.out.println("user: " + demo.user);

        System.out.println("injectUser: " + demo.injectUser);

        System.out.println("@injected == @autowired: " + (demo.user == demo.injectUser));

        System.out.println("myAutowiredUser: " + demo.myAutowiredUser);

        System.out.println("MyInjectUser: " + demo.MyInjectUser);

        // 关闭应用上下文
        context.close();
    }
}
