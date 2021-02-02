package com.yczuoxin.demo.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) // 方式一：import Configuration Class
//@Import(HelloWorldImportSelector.class) // 方式二：import ImportSelector 接口的实现
@Import(HelloWorldImportBeanDefinitionRegistrar.class) // 方式三：import ImportBeanDefinitionRegistrar 接口的实现
public @interface EnableHelloWorld {

}
