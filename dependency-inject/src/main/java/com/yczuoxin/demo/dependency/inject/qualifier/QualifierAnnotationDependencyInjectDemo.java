package com.yczuoxin.demo.dependency.inject.qualifier;

import com.yczuoxin.demo.dependency.annotation.UserGroup;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * 这个方法中 allUsers 拿不到所有的 bean 是因为在 在DefaultListableBeanFactory#findAutowireCandidates这个方法中
 * isSelfReference(beanName, candidate) 判断了是否是自引用
 *
 * 出现的原因
 * 通常 XML 会优先加载
 *
 * 解决方案
 * 1. 示例代码基础上：当前 Demo 类作配置类 register 到容器，不依赖 XML，完全由配置类注入
 * 2. 示例代码基础上：依赖 XML，将当前 Demo 类中 @Bean 的声明都外移到另一配置类（如 MyConfig）中，再 register(MyConfig.class) 到容器
 * 3. 示例代码基础上：将 Demo 类中所有 @Bean 的方法都改为 static，因为 static 会提早初始化Bean
 *
 */
public class QualifierAnnotationDependencyInjectDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User nameUser;

    @Qualifier
    @Bean
    public User user1(){
        return createUser(3L);
    }

    @Qualifier
    @Bean
    public User user2(){
        return createUser(4L);
    }

    @UserGroup
    @Bean
    public User user3(){
        return createUser(5L);
    }

    @UserGroup
    @Bean
    public User user4(){
        return createUser(6L);
    }

    private static User createUser(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @Qualifier
    private Collection<User> groupUser;

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(QualifierAnnotationDependencyInjectDemo.class);
        // XML 的解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        // XML 的路径
        String resourcePath = "classpath:/META-INF/lookup-content.xml";
        // 加载 XML 文件并注册 Bean
        reader.loadBeanDefinitions(resourcePath);
        // 启动应用上下文
        context.refresh();

        QualifierAnnotationDependencyInjectDemo demo = context.getBean(QualifierAnnotationDependencyInjectDemo.class);
        // 没有 @Qualifier("user") 注解标识的
        System.out.println(demo.user);
        // 有 @Qualifier("user") 注解标识的
        System.out.println(demo.nameUser);

        System.out.println(demo.allUsers);
        System.out.println(demo.qualifiedUsers);
        System.out.println(demo.groupUser);

        // 关闭应用上下文
        context.close();
    }
}
