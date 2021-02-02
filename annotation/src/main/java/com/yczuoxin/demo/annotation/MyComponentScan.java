package com.yczuoxin.demo.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {

    @AliasFor(value = "basePackages", annotation = ComponentScan.class)
    String[] scanBasePackages() default {"#"};

    @AliasFor(value = "basePackages", annotation = ComponentScan.class)
    String[] packages() default {"#"};

}
