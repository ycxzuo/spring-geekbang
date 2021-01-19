package com.yczuoxin.demo;

import com.yczuoxin.demo.domain.SuperUser;
import com.yczuoxin.demo.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomPropertyEditorDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/property-editors-context.xml");
        // AbstractApplicationContext#refresh ->
        // AbstractApplicationContext#finishBeanFactoryInitialization(beanFactory.setConversionService) 找到名为 conversionService 的 bean ->
        // AbstractBeanFactory.getConversionService ->
        // AbstractAutowireCapableBeanFactory.createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[]) ->
        // AbstractAutowireCapableBeanFactory.doCreateBean ->
        // AbstractAutowireCapableBeanFactory.createBeanInstance ->
        // AbstractBeanFactory.initBeanWrapper(需要 BeanDefinition) ->
        // 属性转换（数据来源：PropertyValues） ->
        // AbstractPropertyAccessor.setPropertyValues(org.springframework.beans.PropertyValues) ->
        // AbstractAutowireCapableBeanFactory.convertForProperty ->
        // AbstractNestablePropertyAccessor.convertIfNecessary ->
        // TypeConverterDelegate.convertIfNecessary(java.lang.String, java.lang.Object, java.lang.Object, java.lang.Class<T>) ->
        // PropertyEditor or ConversionService
        User user = context.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = context.getBean("superUser", SuperUser.class);
        System.out.println(superUser);
        context.close();
    }
}
