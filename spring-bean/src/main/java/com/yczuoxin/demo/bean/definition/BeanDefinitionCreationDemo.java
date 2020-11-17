package com.yczuoxin.demo.bean.definition;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreationDemo {


    public static void main(String[] args) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 3L).addPropertyValue("name", "jack");
        BeanDefinition beanDefinition = builder.getBeanDefinition();

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues values = new MutablePropertyValues();
        values.add("id", 3L).add("name", "jack");
        genericBeanDefinition.setPropertyValues(values);
        genericBeanDefinition.getOriginatingBeanDefinition();
    }

}
