package com.yczuoxin.demo;

import com.yczuoxin.demo.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;

public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-content.xml");

        Validator validator = context.getBean(Validator.class);

        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor bean = context.getBean(UserProcessor.class);
        bean.processor(new User());
    }

    @Component
    @Validated
    static class UserProcessor{

        public void processor(@Valid User user) {
            System.out.println(user);
        }

    }

}
