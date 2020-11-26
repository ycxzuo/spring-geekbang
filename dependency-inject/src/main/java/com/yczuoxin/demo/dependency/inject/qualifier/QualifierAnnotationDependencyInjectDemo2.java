package com.yczuoxin.demo.dependency.inject.qualifier;

import com.yczuoxin.demo.dependency.annotation.UserGroup;
import com.yczuoxin.demo.domain.SuperUser;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectDemo2 {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User nameUser;

    @Bean
    @Primary
    public User superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(0L);
        superUser.setName("yczuo");
        superUser.setAddress("sz");
        return superUser;
    }

    @Bean
    public User user(){
        return createUser(1L);
    }

    @Qualifier
    @Bean
    public User user1(){
        return createUser(3L);
    }

    @Qualifier // 用于分组
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
    private Collection<User> allUsers; // user, nameUser

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // user1, user2, user3, user4

    @Autowired
    @UserGroup
    private Collection<User> groupUsers; // user3, user4

    public static void main(String[] args) {
        // 创建注解的 BeanFactory 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类 Configuration Class
        context.register(QualifierAnnotationDependencyInjectDemo2.class);
        // 启动应用上下文
        context.refresh();

        QualifierAnnotationDependencyInjectDemo2 demo = context.getBean(QualifierAnnotationDependencyInjectDemo2.class);
        // 没有 @Qualifier("user") 注解标识的
        System.out.println("user:" + demo.user); // 根据类型找到 SuperUser
        // 有 @Qualifier("user") 注解标识的
        System.out.println("superUser:" + demo.nameUser); // 根据 user 标识找到 User
        System.out.println("allUsers:" + demo.allUsers); // 所有的 user
        System.out.println("qualifiedUsers:" + demo.qualifiedUsers); // @qualifier 注解标识了的 Bean 和 @GroupUser 标识了的 Bean
        System.out.println("groupUsers:" + demo.groupUsers); // @GroupUser 标识了的 Bean

        // 关闭应用上下文
        context.close();
    }
}
