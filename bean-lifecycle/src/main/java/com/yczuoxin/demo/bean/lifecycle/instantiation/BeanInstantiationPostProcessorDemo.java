package com.yczuoxin.demo.bean.lifecycle.instantiation;

import com.yczuoxin.demo.bean.domian.UserHolder;
import com.yczuoxin.demo.domain.SuperUser;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationPostProcessorDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("----------------------------");
        //executeApplicationContext();
    }

    /**
     * XmlBeanDefinitionReader#loadBeanDefinitions
     *  将 XML 中定义的 Bean 对应的 将 BeanDefinition 注册到 BeanFactory 中，其底层是调用 BeanDefinitionReaderUtils#registerBeanDefinition 方法注册
     *  该阶段只是注册 BeanDefinition，没有像 ConfigurableApplicationContext#refresh 方法内部 ConfigurableApplicationContext#registerBeanPostProcessors 阶段
     *      将 BeanPostProcessor 注册到 AbstractBeanFactory#beanPostProcessors 字段中
     *
     */
    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new MyInstantiationBeanPostProcessor());
        // 执行 Bean 销毁前的操作
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 使 BeanFactory 具备处理 @PostConstruct 注解的能力
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"META-INF/lookup-content.xml", "META-INF/bean-instantiation-content.xml"};

        reader.loadBeanDefinitions(locations);

        // 该方法会触发所有的非延迟加载的 Bean 按照 BeanDefinition 注册的顺序初始化
        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);
    }

    /**
     * ClassPathXmlApplicationContext 带参数的构造方法会执行 ConfigurableApplicationContext#refresh 方法，
     *  将 BeanPostProcessor 注册到 AbstractBeanFactory#beanPostProcessors 字段中
     *
     */
    private static void executeApplicationContext() {
        String[] locations = {"META-INF/lookup-content.xml", "META-INF/bean-instantiation-content.xml"};
        // 带参数的 ClassPathXmlApplicationContext 初始化会自动调用 refresh 方法
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 初始化两次
        System.out.println(".......................");
        // 重新把值刷回去
        applicationContext.refresh();

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = applicationContext.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        // 如果用 applicationContext 调用 getBean，就不会有 MyInstantiationBeanPostProcessor 的过程 V4,V5,V7
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        System.out.println("-------------------------------------");

        // 如果用 beanFactory 调用 getBean，就只会有 MyInstantiationBeanPostProcessor 的过程 V1,V2,V3,V4,V5,V6
//        beanFactory.preInstantiateSingletons(); //加上这一行就会输出 V7
        UserHolder userHolder2 = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder2);
        // 此方法只会清空缓存，不会调用 destroyBean 的方法
        applicationContext.close();
    }

    static class MyInstantiationBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        /**
         * populateBean 方法中执行
         * @param beanClass
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            // 拦截 superUser 的实例化，并返回一个代理对象，就不会走 Bean 的实例化
            if ("superUser".equals(beanName) && SuperUser.class.equals(beanClass)) {
                SuperUser superUser = new SuperUser();
                superUser.setName("proxy");
                return superUser;
            }
            // 返回 null 代表不作任何的处理
            return null;
        }

        /**
         * populateBean 方法中执行
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if ("user".equals(beanName) && bean instanceof User) {
                User user = (User) bean;
                user.setName("ycxzuo");
                System.out.println("postProcessAfterInstantiation update user name");
                return false;
            }
            return true;
        }

        /**
         * populateBean 方法中执行
         * @param pvs
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            MutablePropertyValues mutablePropertyValues = (MutablePropertyValues) pvs;
            if ("userHolder".equals(beanName) && mutablePropertyValues.contains("desc")) {
                mutablePropertyValues.removePropertyValue("desc");
                mutablePropertyValues.add("desc", "this is recovered user holder V1");
                System.out.println("postProcessProperties update user desc...... this is recovered user holder V1");
            }
            return mutablePropertyValues;
        }

        /**
         * initializeBean 方法中执行
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if ("userHolder".equals(beanName)) {
                UserHolder userHolder = (UserHolder) bean;
                userHolder.setDesc("this is recovered user holder V2");
                System.out.println("postProcessBeforeInitialization update user desc...... this is recovered user holder V2");
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if ("userHolder".equals(beanName)) {
                UserHolder userHolder = (UserHolder) bean;
                userHolder.setDesc("this is recovered user holder V6");
                System.out.println("postProcessAfterInitialization update user desc...... this is recovered user holder V6");
            }
            return bean;
        }
    }

    static class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
        @Override
        public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
            if ("userHolder".equals(beanName)) {
                UserHolder userHolder = (UserHolder) bean;
                userHolder.setDesc("this is recovered user holder V8");
                System.out.println("postProcessBeforeDestruction update user desc...... this is recovered user holder V8");
            }
        }
    }
}
